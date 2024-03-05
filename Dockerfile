
# Seleccionar imagen
FROM openjdk:17-alpine

# Refer to Maven build -> finalName
ARG JAR_FILE=target/project-karma-api-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar el archivo JAR generado
CMD ["java", "-jar", "app.jar"]
