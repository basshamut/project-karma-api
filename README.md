# project-karma-api

API that processes names and brings a karma study using names.

## 🚀 Quick Start

### Desarrollo Local

1. **Ejecuta la aplicación:**
   ```bash
   mvn spring-boot:run
   ```

2. **Accede a la documentación API:**
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - OpenAPI JSON: http://localhost:8080/v3/api-docs

### Despliegue en Coolify

El proyecto incluye un Dockerfile optimizado para Coolify. Solo necesitas:

1. Conectar tu repositorio en Coolify
2. Configurar las variables de entorno en la interfaz de Coolify
3. Coolify construirá y desplegará automáticamente

## 🔧 Tecnologías

- Java 17
- Spring Boot 3.2.4
- Spring Data JPA
- MySQL 8+ / H2
- Hibernate 6.4.4
- SpringDoc OpenAPI 2.5.0 (Swagger)
- Maven 3.9+
- Docker

## 📚 API Endpoints

La API está documentada con OpenAPI 3.0 (Swagger). Una vez iniciada la aplicación, puedes explorar todos los endpoints en:

**http://localhost:8080/swagger-ui.html**

### Endpoints principales:

- `GET /karma-api/v1/karmas/` - Obtener todos los karmas
- `GET /karma-api/v1/karmas/study?name={name}` - Estudio de karma por nombre

## 🛠️ Requisitos

- JDK 17 o superior
- Maven 3.9+
- MySQL 8+ (opcional, se puede usar H2)
- Docker (para despliegue)
