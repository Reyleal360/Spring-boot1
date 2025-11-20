#!/bin/bash

# Detener servicios anteriores si existen
pkill -f venues-service
pkill -f events-service

echo "Iniciando Venues Service..."
nohup java -jar venues-service/target/venues-service-1.0.0.jar > venues.log 2>&1 &
echo "Venues Service iniciado (PID $!)"

echo "Iniciando Events Service..."
nohup java -jar events-service/target/events-service-1.0.0.jar > events.log 2>&1 &
echo "Events Service iniciado (PID $!)"

echo "Servicios corriendo en background. Logs en venues.log y events.log"
