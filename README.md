# project-karma-api

API REST que, a partir de la fecha de nacimiento y el nombre completo de una persona, calcula su perfil de reencarnación anterior (vida pasada) y su deuda kármica actual.

## Tecnologías

- Java 17
- Spring Boot 3.2.4
- Spring Data JPA / Hibernate 6
- MySQL 8+ / H2 (desarrollo)
- Flyway (migraciones)
- SpringDoc OpenAPI 2.5.0 (Swagger)
- Maven 3.9+
- Docker

---

## Estructura del proyecto

```
src/
├── main/
│   ├── java/com/karma/
│   │   ├── controller/
│   │   │   ├── KarmaController.java        # Endpoints de karma (por nombre)
│   │   │   └── PastLifeController.java     # Endpoint de vida pasada (por fecha)
│   │   ├── service/
│   │   │   ├── KarmaService.java           # Lógica tabla pitagórica + karma
│   │   │   └── PastLifeService.java        # Lógica encadenada de 7 pasos
│   │   ├── persistance/
│   │   │   ├── entity/                     # Entidades JPA
│   │   │   └── repository/                 # Repositorios Spring Data
│   │   ├── service/mapper/dto/             # DTOs de respuesta
│   │   └── util/
│   │       ├── enums/
│   │       │   ├── Month.java              # ENERO … DICIEMBRE
│   │       │   └── Sex.java                # M, F
│   │       └── Constants.java
│   └── resources/
│       ├── application.yml
│       └── db/migration/
│           ├── V1__schema.sql              # CREATE TABLE (todas las tablas)
│           ├── V2__karma_data.sql          # Datos: karma, birthyear, birthmounth, birthday, encarnationdate
│           └── V3__personality_lookup_data.sql  # Datos: encarnationlocation, profession_lookup, personality_lookup
```

---

## Base de datos

### Tablas

| Tabla | Descripción |
|---|---|
| `karma` | Deuda kármica por número ausente (1–9) |
| `birthyear` | Código de letra según año de nacimiento y sexo actual |
| `birthmounth` | Profesión, símbolo y códigos según mes y sexo |
| `birthday` | Ubicación según día, símbolo y sexo |
| `encarnationdate` | Año aproximado de la encarnación anterior |
| `encarnationlocation` | País o región de la encarnación anterior (74 entradas) |
| `profession_lookup` | Descripción de profesión anterior por código y género (28 entradas) |
| `personality_lookup` | Descripción de personalidad por símbolo y paridad del día (7 entradas) |

### Migraciones Flyway

Las migraciones se ejecutan automáticamente al iniciar la aplicación.
El esquema completo está en `V1__schema.sql`. Los datos están separados en `V2` y `V3`.

---

## API Endpoints

### Karma (deuda kármica por nombre)

| Método | URL | Descripción |
|---|---|---|
| `GET` | `/karma-api/v1/karmas/` | Lista todos los registros de karma |
| `GET` | `/karma-api/v1/karmas/{number}` | Karma por número (1–9) |
| `GET` | `/karma-api/v1/karmas/study?name={name}` | Calcula los números kármicos ausentes de un nombre |

**Ejemplo:**
```
GET /karma-api/v1/karmas/study?name=Juan
```

**Respuesta:**
```json
{
  "data": [
    {
      "number": 3,
      "missing": "Se reusó a mostrar sus talentos...",
      "situation": "Se hace necesario mostrar optimismo...",
      "improve": "Debe aprender a expresar sus sentimientos..."
    }
  ],
  "code": "200",
  "message": "OK"
}
```

---

### Vida Pasada (perfil de reencarnación por fecha de nacimiento)

| Método | URL | Descripción |
|---|---|---|
| `GET` | `/karma-api/v1/past-life` | Perfil completo de la vida anterior |

**Parámetros:**

| Parámetro | Tipo | Valores válidos | Ejemplo |
|---|---|---|---|
| `day` | `int` | 1 – 31 | `11` |
| `month` | `Month` (enum) | `ENERO` … `DICIEMBRE` | `SEPTIEMBRE` |
| `year` | `int` | 1900 – 2100 | `1984` |
| `sex` | `Sex` (enum) | `M`, `F` | `M` |

**Ejemplo:**
```
GET /karma-api/v1/past-life?day=11&month=SEPTIEMBRE&year=1984&sex=M
```

**Respuesta:**
```json
{
  "data": {
    "sexInPastLife": "Masculino",
    "country": "Nueva Zelanda",
    "yearApprox": "1650",
    "profession": "Abogado, bibliotecario, cronista, historiador o escribano.",
    "personalitySymbol": "Triángulo",
    "personality": "Con su sed de conocimientos y búsqueda de la verdad, fue un místico bastante respetado. Pocos tenían tanta seguridad de una vida futura como usted."
  },
  "code": "200",
  "message": "OK"
}
```

**Lógica interna (7 pasos encadenados):**

```
1. BIRTHYEAR   (año + sexo)           → letter_code
2. BIRTHMOUNTH (mes + letter_code)    → profesion, simbol_type, orientation_type, sex_in_past
3. BIRTHDAY    (día + simbol_type)    → location
4. ENCARNATIONDATE (orientation_type + letter_code) → year_encarnation
5. ENCARNATIONLOCATION (location)     → location_name
6. PROFESSION_LOOKUP (profesion code) → descripción según sexo pasado
7. PERSONALITY_LOOKUP (simbol_type)   → descripción según paridad del día
```

---

## Configuración

### Variables de entorno

| Variable | Descripción | Valor por defecto |
|---|---|---|
| `SERVER_PORT` | Puerto del servidor | `8080` |
| `SERVER_CONTEXT_PATH` | Context path | `/karma-api` |
| `DB_DRIVER` | Driver JDBC | `com.mysql.cj.jdbc.Driver` |
| `DB_URL` | URL de conexión | `jdbc:mysql://localhost:3306/karma_db` |
| `DB_USERNAME` | Usuario de base de datos | `root` |
| `DB_PASSWORD` | Contraseña de base de datos | `changeme` |
| `JPA_DIALECT` | Dialecto Hibernate | `org.hibernate.dialect.MySQLDialect` |
| `JPA_SHOW_SQL` | Mostrar SQL en logs | `false` |
| `H2_CONSOLE_ENABLED` | Activar consola H2 | `false` |

### Archivo `.env` (desarrollo local)

Crea un archivo `.env` en la raíz del proyecto:

```env
DB_URL=jdbc:mysql://localhost:3306/karma_db?autoReconnect=true&useSSL=false
DB_USERNAME=root
DB_PASSWORD=tu_password
```

---

## Ejecución local

```bash
# Compilar y ejecutar
mvn spring-boot:run

# Solo compilar
mvn clean package

# Ejecutar el JAR
java -jar target/project-karma-api-0.0.1-SNAPSHOT.jar
```

**Swagger UI:** http://localhost:8080/swagger-ui.html
**OpenAPI JSON:** http://localhost:8080/api-docs

---

## Despliegue con Docker / Coolify

El proyecto incluye un `Dockerfile` optimizado. Para desplegar en Coolify:

1. Conectar el repositorio `git@github.com:basshamut/project-karma-api.git`
2. Configurar las variables de entorno en la interfaz de Coolify
3. Coolify construirá y desplegará automáticamente

---

## Actuator

| Endpoint | Descripción |
|---|---|
| `GET /karma-api/actuator/health` | Estado de la aplicación |
| `GET /karma-api/actuator/info` | Información general |
