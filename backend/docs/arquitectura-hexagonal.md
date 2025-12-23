# Arquitectura Hexagonal - Trabajo Final Programación 2 (Brunopiastre)

## Capas

- **domain**
  - Entidades de dominio (modelos de negocio)
  - Value Objects
  - Puertos (interfaces) de dominio
  - Servicios de dominio (sin Spring)

- **application**
  - Casos de uso
  - DTOs de entrada/salida
  - Mappers entre DTO y dominio

- **infrastructure**
  - Adapters de entrada: controllers REST, consumers de Kafka, schedulers
  - Adapters de salida: repositorios JPA, Redis, Kafka, API cátedra
  - Configuración (Spring, seguridad, profiles)

## Reglas de dependencia

- `domain` **no depende de nadie** (ni de Spring, ni de JPA, ni de Kafka).
- `application` **depende sólo de `domain`**.
- `infrastructure` puede depender de `application` y `domain`.

## Microservicios (completar)

- ticket-service: (describir rol)
- user-service: (describir rol)
- blocking-service: (describir rol)
- notification-service: (describir rol)
- gateway/api: (describir rol)

(Completar después con entidades principales, puertos y adapters de cada uno.)
