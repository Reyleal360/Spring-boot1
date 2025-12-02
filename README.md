# Historia de Usuario: Refactor Hexagonal con Equivalencia Funcional (Ports & Adapters)

## Objetivo de la HU
Reestructurar el proyecto actual hacia una arquitectura hexagonal, separando el núcleo de negocio de los frameworks (Spring, JPA, etc.) para lograr independencia tecnológica, mayor mantenibilidad y facilidad de pruebas unitarias sin base de datos.

## Tareas

### TASK 1: Análisis y Reorganización de Paquetes
1. Crear una estructura base con los siguientes paquetes:
    - `dominio/` → entidades y lógica de negocio pura
    - `aplicacion/usecase/` → casos de uso
    - `infraestructura/adapters/in/web/` → controladores REST
    - `infraestructura/adapters/out/jpa/` → adaptadores de persistencia
    - `infraestructura/config/` → beans de configuración
2. Eliminar dependencias cruzadas entre capas (el dominio no debe importar de infraestructura).
3. Mantener la equivalencia funcional con el CRUD existente.

### TASK 2: Creación de Puertos (Ports)
1. Crear interfaces en `dominio/ports/in/` y `dominio/ports/out/`.
    - **Puertos de entrada**: definen los casos de uso que el sistema expone (por ejemplo: `CrearUsuarioUseCase`).
    - **Puertos de salida**: definen las dependencias necesarias (por ejemplo: `UsuarioRepositoryPort`).
2. Inyectar dependencias en los casos de uso mediante interfaces, no implementaciones.
3. Implementar los casos de uso en `aplicacion/usecase/` siguiendo principios SOLID.

### TASK 3: Adaptadores y Mapeo con MapStruct
1. Implementar adaptadores concretos para los puertos:
    - `UsuarioJpaAdapter` (implementa `UsuarioRepositoryPort`)
    - `UsuarioRestAdapter` (controlador REST)
2. Integrar MapStruct para convertir entre entidad (persistencia) y modelo de dominio.
3. El dominio no debe tener anotaciones de JPA ni dependencias de Spring.

## Criterios de Aceptación
- La aplicación mantiene el mismo comportamiento funcional que antes del refactor.
- El dominio está completamente desacoplado de frameworks o tecnología de persistencia.
- Se evidencia el uso correcto de puertos y adaptadores.
- MapStruct realiza la conversión entre entidad y dominio.
- La API REST continúa funcionando sin ruptura de endpoints.
- La documentación del proyecto refleja la nueva arquitectura.

## Story Points: 10

## Cierre de la actividad
El proyecto debe demostrar independencia tecnológica, manteniendo la funcionalidad existente.
El dominio queda completamente desacoplado de frameworks externos, validando la correcta separación entre lógica de negocio, aplicación e infraestructura.
La entrega debe incluir el código refactorizado y la documentación actualizada que evidencie la transición hacia la arquitectura hexagonal (Ports & Adapters).