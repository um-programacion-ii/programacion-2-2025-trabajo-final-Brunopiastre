# E2E – Test de sesiones múltiples y retomables

## Objetivo
Verificar que el usuario pueda:
1. Iniciar una sesión.
2. Abandonar la app o expirar la sesión.
3. Volver luego a la app.
4. Retomar el flujo de compra sin perder estado crítico.

## Flujo esperado
1. Mobile hace login → recibe token.
2. Mobile selecciona evento y asientos.
3. Mobile abandona o se cierra la sesión.
4. Mobile reabre → el sistema debe:
   - validar token o pedir re-login
   - reconstruir estado desde backend o cache interno
5. Continuar compra sin errores.

## Resultado esperado
- La compra se completa sin re-seleccionar todo desde cero.
- El backend maneja correctamente múltiples sesiones.
