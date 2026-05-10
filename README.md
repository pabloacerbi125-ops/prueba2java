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

## Ejecución local

1. Abrir el proyecto en el directorio raíz `d:\proyectos\java 2prueba`.
2. Ejecutar el servidor Eureka:

```powershell
.\mvnw.cmd -pl eureka-server spring-boot:run
```

3. En otra terminal ejecutar el Gateway:

```powershell
.\mvnw.cmd -pl api-gateway spring-boot:run
```

4. Ejecutar los microservicios en terminales distintas:

```powershell
.\mvnw.cmd -pl auth-service spring-boot:run
.\mvnw.cmd -pl project-service spring-boot:run
.\mvnw.cmd -pl resource-service spring-boot:run
.\mvnw.cmd -pl collab-service spring-boot:run
```

Si `mvn` está en el PATH, puede usarse simplemente `mvn` en lugar de la ruta completa.

## Endpoints clave

- `http://localhost:8761/` -> Interfaz de Eureka
- `http://localhost:8080/auth/login` -> Login a través del Gateway
- `http://localhost:8080/auth/register` -> Registro de usuario
- `http://localhost:8080/projects` -> Proyecto protegido a través del Gateway
- `http://localhost:8080/resources` -> Recursos protegidos a través del Gateway
- `http://localhost:8080/collabs` -> Colaboraciones protegidas a través del Gateway

## Prueba de flujo esencial

1. Registrar un usuario:

```bash
curl -X POST http://localhost:8080/auth/register -H "Content-Type: application/json" -d '{"username":"user1","password":"pass"}'
```

2. Iniciar sesión y obtener token:

```bash
curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{"username":"user1","password":"pass"}'
```

3. Usar el token en headers para acceder a servicios protegidos:

```bash
curl http://localhost:8080/projects -H "Authorization: Bearer <TOKEN>"
```

## Notas finales

- El proyecto usa `H2` como base de datos en memoria para pruebas locales.
- Para producción se puede cambiar a `MySQL`, `PostgreSQL` o `AWS RDS` en los `application.yml` de los servicios.
- Las rutas del gateway están configuradas para usar el nombre de servicio registrado en Eureka.
