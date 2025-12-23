# TicketFlow – Proyecto Final Programación 2 (2025)

## Arquitectura
- **Backend (Spring Boot)** – Lógica de compra, asientos, ventas, cache.
- **Proxy (Spring Boot)** – Comunicación con cátedra, Kafka, Redis.
- **Mobile (Kotlin Multiplatform)** – App cliente.
- **Infra (Docker)** – Redis, Kafka, Zookeeper.

## Cómo correr el proyecto

docker compose up -d
mvn spring-boot:run -pl backend
mvn spring-boot:run -pl proxy

## Estructura
- `/backend`
- `/proxy`
- `/mobile`
- `/infra`
- `/docs`
- `/tests/e2e`

## Integraciones
- Cátedra: endpoints de eventos, bloqueo, ventas.
- Kafka: cambios de eventos.
- Redis: sesiones y cache.

## Autores
- **Bruno Piastrellini**
