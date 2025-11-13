# ADR 0001 – Estrategia de notificación del Proxy al Backend

## Contexto
El Proxy recibe eventos de Kafka provenientes de la cátedra.
Debe notificar al Backend de que hubo cambios para actualizar caches y estados.

## Decisión
El Proxy notificará al Backend mediante un POST HTTP a:

`/api/backend/internal/notificaciones/kafka`

Incluyendo el mensaje Kafka completo como body.

## Justificación
- HTTP es simple y estable.
- WebClient permite reintentos.
- El Backend puede centralizar la transformación de los mensajes.

## Consecuencias
- El Backend debe proteger este endpoint con autenticación interna (Issue 14).
