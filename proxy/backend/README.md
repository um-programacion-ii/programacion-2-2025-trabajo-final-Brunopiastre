# Ticketflow Backend

Aplicación backend (Spring Boot 3, Java 17) para gestión de usuarios, eventos, tickets y pagos siguiendo una arquitectura hexagonal simple.

## Requisitos
- Java 17
- Maven 3.9+
- Docker y Docker Compose (opcional, para bases de datos locales)

## Perfiles de Spring
- `dev` (por defecto): H2 en memoria, útil para desarrollo y tests.
- `mysql`: se conecta a MySQL (ver Docker Compose más abajo).
- `postgres`: se conecta a PostgreSQL (ver Docker Compose más abajo).

Archivos de configuración relevantes en `src/main/resources/`:
- `application.yml` (activa `dev` por defecto)
- `application-dev.yml` (H2)
- `application-mysql.yml` (MySQL)
- `application-postgres.yml` (PostgreSQL)

Ejecutar con perfiles:
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

## Compilar y testear
```
mvn clean install
```

## Docker Compose (infra)
Se provee `infra/docker-compose.yml` con Redis, Kafka/ZooKeeper, MySQL y PostgreSQL.

Levantar servicios:
```
cd infra
docker compose up -d
```

Credenciales alineadas con perfiles:
- MySQL: `jdbc:mysql://localhost:3306/ticketflow` user: `ticketflow`, pass: `ticketflow`
- PostgreSQL: `jdbc:postgresql://localhost:5432/ticketflow` user: `ticketflow`, pass: `ticketflow`

## Endpoints (ejemplos)
- Usuarios `/api/users`
  - POST: registrar `{ "email": "john@doe.com", "fullName": "John Doe", "role": "USER" }`
  - GET: listado
- Tickets `/api/tickets`
  - POST: crear `{ "eventId": 1, "buyerEmail": "john@doe.com" }`
  - GET: listado
- Pagos `/api/payments`
  - POST: crear `{ "ticketId": 1, "amount": 1200.0 }`
  - GET: listado

## Flujo básico de compra
1. Registrar usuario
2. Consultar eventos y asientos (módulo `event`)
3. Crear venta/ticket
4. Registrar pago del ticket
5. Consultar resumen

## Notas de arquitectura
- Módulos: `event`, `user`, `ticket`, `payment` con capas dominio → aplicación → infraestructura.
- Persistencia vía Spring Data JPA.
- WebClient disponible para integraciones HTTP salientes.
