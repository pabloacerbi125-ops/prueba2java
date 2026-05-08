# Microservices Platform

Proyecto Java Spring Boot con arquitectura de microservicios y gateway.

## Qué hace cada módulo

- `eureka-server`: servidor de descubrimiento que registra microservicios y permite que el gateway y servicios entre sí encuentren direcciones dinámicas.
- `api-gateway`: puerta de entrada que recibe solicitudes del cliente y las enruta hacia los microservicios registrados en Eureka.
- `auth-service`: servicio de autenticación que crea usuarios, valida credenciales y emite tokens JWT.
- `project-service`: servicio protegido que guarda y lista proyectos mediante una base de datos en memoria.
- `resource-service`: servicio de ejemplo protegido que devuelve recursos de información.
- `collab-service`: servicio de ejemplo protegido que devuelve colaboraciones de trabajo.

## Qué contiene la solución

- Registro de servicios con Eureka
- Gateway con enrutamiento dinámico y descubrimiento de servicios
- Autenticación JWT para usuarios
- Microservicios independientes que consumen el token JWT
- Configuración local con H2 para pruebas rápidas
- Estructura multi-módulo Maven para separar cada componente

## Ejecución local y pruebas

### Paso 1: Compilar todo el proyecto

Abre una terminal en la raíz del proyecto `d:\proyectos\java 2prueba` y ejecuta:

```powershell
mvn clean compile
```

### Paso 2: Iniciar Eureka Server

Abre una nueva terminal y ejecuta:

```powershell
cd eureka-server
mvn spring-boot:run
```

Eureka estará disponible en `http://localhost:8761`.

### Paso 3: Iniciar API Gateway

Abre otra terminal y ejecuta:

```powershell
cd api-gateway
mvn spring-boot:run
```

El Gateway se conectará automáticamente a Eureka y quedará disponible en `http://localhost:8080`.

### Paso 4: Iniciar Auth Service

Abre otra terminal y ejecuta:

```powershell
cd auth-service
mvn spring-boot:run
```

**Correcciones aplicadas**:
- La entidad `User` se mapea a la tabla `app_user` para evitar que H2 rechace el nombre reservado `user`.
- Se habilitó `spring.main.allow-circular-references: true` para permitir la configuración de seguridad actual.
- Se desactivó la consola H2 para evitar conflictos con el servlet HTTP.

Auth Service quedará disponible en `http://localhost:9001`.

### Paso 5: Iniciar Project Service

Abre otra terminal y ejecuta:

```powershell
cd project-service
mvn spring-boot:run
```

### Paso 6: Iniciar Resource Service

Abre otra terminal y ejecuta:

```powershell
cd resource-service
mvn spring-boot:run
```

### Paso 7: Iniciar Collab Service

Abre otra terminal y ejecuta:

```powershell
cd collab-service
mvn spring-boot:run
```

## Verificación

### 1. Verificar Eureka Dashboard

Abre el navegador en:

```text
http://localhost:8761
```

Debes ver registrados los servicios:
- EUREKA-SERVER
- API-GATEWAY
- AUTH-SERVICE
- PROJECT-SERVICE
- RESOURCE-SERVICE
- COLLAB-SERVICE

### 2. Verificar registro desde la API de Eureka

```powershell
curl -X GET http://localhost:8761/eureka/apps
```

### 3. Prueba de health de Auth Service directo

```powershell
curl -X GET http://localhost:9001/actuator/health
```

### 4. Prueba de health de Auth Service vía Gateway

```powershell
curl -X GET http://localhost:8080/auth/actuator/health
```

### 5. Prueba de otros servicios vía Gateway

```powershell
curl -X GET http://localhost:8080/projects/actuator/health
curl -X GET http://localhost:8080/resources/actuator/health
curl -X GET http://localhost:8080/collab/actuator/health
```

### 6. Prueba de flujo básico de Auth

Registrar usuario:

```powershell
curl -X POST http://localhost:8080/auth/register -H "Content-Type: application/json" -d '{"username":"user1","password":"pass"}'
```

Iniciar sesión:

```powershell
curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{"username":"user1","password":"pass"}'
```

Usar token para acceder a un servicio protegido:

```powershell
curl http://localhost:8080/projects -H "Authorization: Bearer <TOKEN>"
```

## Endpoints clave

- `http://localhost:8761/` -> Interfaz de Eureka
- `http://localhost:8080/` -> API Gateway
- `http://localhost:8080/auth/login` -> Login a través del Gateway
- `http://localhost:8080/auth/register` -> Registro de usuario
- `http://localhost:8080/projects` -> Project Service a través del Gateway
- `http://localhost:8080/resources` -> Resource Service a través del Gateway
- `http://localhost:8080/collab` -> Collab Service a través del Gateway

## Notas finales

- El proyecto usa `H2` como base de datos en memoria para pruebas locales.
- Para producción se puede cambiar a `MySQL`, `PostgreSQL` o `AWS RDS` en los `application.yml`.
- El gateway enruta mediante nombres de servicio registrados en Eureka.
