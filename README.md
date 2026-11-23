# 🚲 Backend Bicis: API REST con Spring Boot

API REST para gestionar el catálogo de una tienda de bicicletas. Es el backend del frontend [FRONTEND-bicis-admin](https://github.com/Truquitos212/FRONTEND-bicis-admin).

Proyecto de la asignatura **Desarrollo Cloud Native I (DSY1107)**, DuocUC.

## Stack

- Java 17
- Spring Boot 4 (Web MVC + Validation)
- Maven

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/bicicletas` | Lista todas las bicicletas |
| GET | `/api/bicicletas/{id}` | Obtiene una bicicleta |
| POST | `/api/bicicletas` | Crea una bicicleta |
| PUT | `/api/bicicletas/{id}` | Actualiza una bicicleta |
| DELETE | `/api/bicicletas/{id}` | Elimina una bicicleta |

Modelo `Bicicleta`: `id`, `modelo`, `marca`, `talla`, `stock`.

Incluye validaciones, manejo global de errores (`404` cuando la bicicleta no existe) y configuración de CORS para el frontend.

## Cómo correrlo

```bash
./mvnw spring-boot:run
```

La API queda en `http://localhost:8080`.
