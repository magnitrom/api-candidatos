## Api Candidatos
***

# 🚀 Descripción 
***
La API Candidates proporciona funcionalidades para gestionar información de candidatos. Permite realizar operaciones de CRUD (crear, leer, actualizar y eliminar) sobre los registros de candidatos almacenados en una base de datos.

# 🛅 Tecnologías
***

- **Java 17** (o superior) como lenguaje de programación.
- **Spring Boot** Versión 3.1.0 para la creación de la API RESTful.
- **JPA (Java Persistence API)** y **Hibernate** para la gestión de la base de datos.
- **Swagger** Para documentación de API
- **MySQL** Version 8.0.40 como base de datos.

# 📋 Endpoints
***
| **Método HTTP**    | **Endpoint** |
| -------- | ------- |
| **GET**  | `/api/auth/candidates`    |
| **GET** | `/api/auth/candidates/{idCandidate}`     |
| **POST**    | `/api/auth/candidates`    |
| **POST**    | `/api/candidates/login?username=admin&password=xxxx`    |
| **PUT**    | `/api/candidates`    |
| **DELETE**    | `/api/candidates/{idCandidate}`    |

# 🔧 Instalación 
***

1. Crear un carpeta local donde sera el area de trabajo del proyecto.

2. Ubicarse en la carpeta local

3. Clonar el repositorio en rama main del proyecto

git clone --branch main https://github.com/magnitrom/api-candidatos

4. Importar carpeta del proyecto en Spring Tool Suite

5. Configuración de las Siguientes Variables de Entorno

| **Nombre**    | **valor** | **Descripción** |
| -------- | ------- | ------- |
| **ACCESS_USERNAME** | `admin`     |Usuario de acceso a la aplicación|
| **ACCESS_PASSWORD**  | `xxxx`    |Contraseña de acceso a la aplicación|
| **DATASOURCE_URL**    | `jdbc:mysql://localhost:3306/reto`    | Cadena de Conexión a la Base de Datos    |
| **DATASOURCE_USER**    | `root`    |Usuario de acceso a la Base de Datos|
| **DATASOURCE_PASS**    | `xxxx`    |Contraseña de acceso a la Basse de Datos|

6. Seleccionar el aplicativo y dar click derecho y ejecutar el aplicativo como Spring Boot App

7. Ingresar a la siguiente url: http://{server_ip:port}

# 📖 Documentación 
***

**Documentación de API:** https://studio-ws.apicur.io/sharing/a6b50c13-77f8-4943-8b55-ae157842595e
**Documentación de Swagger:** http://{server_ip:port}/swagger-ui/index.html

# 📖 Autor 
***

**Bryan Francisco Núñez**  
**bryan_nupi@hotmail.com**