# Cheat-Sheet Payloads (3–9)

## Payload 3 – Eventos resumidos
GET /api/endpoints/v1/eventos-resumidos  
Retorna lista de:
- titulo
- resumen
- fecha
- precio
- tipo
- id

## Payload 4 – Eventos completos
GET /api/endpoints/v1/eventos  
Retorna:
- titulo, resumen, descripcion
- fecha, direccion, imagen
- filas/columnas
- precio
- integrantes[]
- id

## Payload 5 – Evento por ID
GET /api/endpoints/v1/evento/{id}

## Payload 6 – Bloquear asientos
POST /api/endpoints/v1/bloquear-asientos  
Body:
{
  "eventoId": 1,
  "asientos": [{ "fila": 2, "columna": 1 }]
}

## Payload 7 – Venta
POST /api/endpoints/v1/realizar-venta

## Payload 8 – Listado de ventas por alumno
GET /api/endpoints/v1/listar-ventas

## Payload 9 – Datos de venta por ID
GET /api/endpoints/v1/listar-venta/{id}
