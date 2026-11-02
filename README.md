# Tavern EventBus Simulation

Este proyecto demuestra un sistema de eventos desacoplado (Publish-Subscribe) en Java sin uso de frameworks externos.

## Requisitos
- Java 17 o superior.

## Cómo compilar y ejecutar (Terminal pura)

1. Abre tu terminal y colócate en la carpeta raíz (`tavern/..`, un nivel arriba de `tavern`).
2. Compila todos los archivos .java:
   ```bash
   javac tavern/eventbus/*.java tavern/events/*.java tavern/publishers/*.java tavern/subscribers/*.java tavern/main/*.java
