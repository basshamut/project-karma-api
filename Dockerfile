
# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /build

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Install wget for healthcheck
RUN apk add --no-cache wget

# Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy JAR from build stage
COPY --from=build /build/target/project-karma-api-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Coolify uses this)
EXPOSE 8080

# Health check for Coolify
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/karma-api/actuator/health || exit 1

# Environment variables configuration:
# Configure in Coolify UI or pass via --env-file:
# - DB_URL, DB_USERNAME, DB_PASSWORD (required)
# - JPA_DDL_AUTO (recommended: validate for production)
# - SERVER_PORT (default: 8080)
# See .env.example for all available variables

# Run with optimal JVM settings for containers
ENTRYPOINT ["java", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-Djava.security.egd=file:/dev/./urandom", \
  "-jar", \
  "app.jar"]
