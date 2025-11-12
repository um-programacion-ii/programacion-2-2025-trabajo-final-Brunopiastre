#!/bin/bash
echo "Levantando infraestructura local..."
docker compose up -d
echo "Listo. Redis y Kafka están levantados."
