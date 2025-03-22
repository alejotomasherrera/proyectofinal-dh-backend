# Digital Money House - Proyecto de Certificación Especialista Backend

## Descripción General

Este proyecto, Digital Money House, es el trabajo final para mi Certificación de Especialista Backend. Es una aplicación bancaria distribuida, diseñada con una arquitectura de microservicios para gestionar diversas operaciones financieras.

La aplicación demuestra principios clave de backend, incluyendo:

*   Arquitectura de microservicios
*   Desarrollo de API
*   Interacción con bases de datos
*   Autenticación y Autorización
*   Descubrimiento de servicios
*   Gestión de configuración
*   **Patrones de diseño de microservicios**

## Microservicios

El proyecto se divide en los siguientes microservicios, cada uno responsable de una funcionalidad específica:

*   **auth-service:**
    *   Gestiona la autenticación y autorización de usuarios (inicio de sesión, cierre de sesión, generación de tokens).
*   **config-server:**
    *   Gestiona la configuración de todos los demás microservicios.
*   **cuenta-service:**
    *   Gestiona las cuentas de usuario (creación, actualizaciones, gestión de saldo).
*   **service-api-gateway:**
    *   Actúa como el punto de entrada para todas las solicitudes del cliente, enrutando las solicitudes al microservicio apropiado.
*   **servicio-eurekaServer:**
    *   Servidor de descubrimiento de servicios que permite a los microservicios localizarse y comunicarse entre sí.
*   **servicio-tarjetas:**
    *   Gestiona la administración de tarjetas de usuario (creación, recuperación, eliminación).
*   **servicio-transacciones:**
    *   Gestiona las transacciones financieras.
*   **servicio-usuarios:**
    *   Gestiona los datos del usuario (registro, recuperación, actualizaciones).

## Tecnologías Utilizadas

*   **Lenguaje:** Java 17
*   **Frameworks:** Spring Boot, Spring Cloud
*   **Base de datos:** MySQL (Base de datos relacional)
*   **Caché/Sesiones:** Redis
*   **Descubrimiento de servicios:** Eureka
*   **Contenedorización:** Docker
*   **Logging:** Logstash, Elasticsearch, Kibana (ELK Stack)

## Arquitectura

*   **Microservicios:** Arquitectura basada en microservicios.
*   **Persistencia:** Uso de base de datos relacional MySQL para el almacenamiento persistente.
*   **Rendimiento:** Uso de Redis para almacenamiento en caché y sesiones.
*   **Diseño:** Patrones de diseño para microservicios aplicados para robustez y escalabilidad.

## Frontend

*   El frontend de la aplicación está disponible en este repositorio de GitHub:
    *   \[[https://github.com/alejotomasherrera/frontend-digitalmoneyhouse](https://github.com/alejotomasherrera/frontend-digitalmoneyhouse)\](https://github.com/alejotomasherrera/frontend-digitalmoneyhouse)

## Documentación de la API y Pruebas Manuales

*   **API:** La documentación de la API está disponible en el directorio `Documentacion` como una colección de Postman.
*   **Pruebas:** Todos los casos de prueba manuales se encuentran en un archivo de Excel dentro de la carpeta `Documents`.

## Docker Compose

El proyecto utiliza Docker Compose para simplificar la configuración y el despliegue de todos los microservicios y dependencias.

## Gestión de Logs

El proyecto implementa la gestión de registros utilizando Logstash, Elasticsearch y Kibana (el stack ELK) para el registro y análisis centralizado.

## Cómo Empezar

1.  Clona el repositorio.
2.  Asegúrate de tener Docker y Docker Compose instalados.
3.  Navega hasta el directorio raíz del proyecto.
4.  Ejecuta `docker-compose up redis mysql elasticsearch kibana logstash` para inicializar los servicios.
5.  Luego ejecuta `docker-compose up` para iniciar todos los microservicios restantes una vez que se encuentren listos los servicios previamente iniciados.

## Notas Importantes

*   Este proyecto está diseñado con fines de demostración y educativos.
*   Las mejores prácticas de seguridad pueden necesitar un mayor refinamiento para el uso en producción.

## Contacto

*   **Nombre:** Alejo Tomas Herrera
*   **LinkedIn:** \[[https://www.linkedin.com/in/alejo-tomas-herrera/](https://www.linkedin.com/in/alejo-tomas-herrera/)]