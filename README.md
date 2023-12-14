
# microservicio-calculadora

## 1. Estructura del Proyecto.

   El proyecto sigue la siguiente estructura de directorios:
<pre>
    |-- doc
    |   |-- diagrams
    |       |-- diagrama-clases.mmd
    |   |-- images
    |       |-- operación de suma con éxito.png
    |       |-- operación de resta con éxito.png
    |       |-- operación inválida.png
    |       |-- JaCoCo reporte cobertura superior al 80%.png
    |   |-- Análisis-Funcional.md
    |   |-- Ingeniería-Requisitos.md
    |   |-- Planificación-Gestión.md
    |-- lib
    |   |-- tracer-1.0.0.jar
    |   |-- tracer-1.0.0-javadoc.jar
    |   |-- tracer-1.0.0-sources.jar
    |-- src
    |   |-- main
    |       |-- java
    |           |-- com
    |               |-- sanitas
    |                   |-- test4
    |                       |-- calculator
    |                           |-- configuration
    |                               |-- OpenAPIConfig.java
    |                               |-- OperationConfig.java
    |                           |-- controller
    |                               |-- CalculatorController.java
    |                           |-- exception
    |                               |-- CalculatorException.java
    |                               |-- ExceptionMessages.java
    |                               |-- GlobalExceptionHandler.java
    |                               |-- InvalidOperationException.java
    |                           |-- factory
    |                               |-- OperationFactory.java
    |                           |-- model
    |                               |-- ApiErrorResponse.java
    |                               |-- OperationResponse.java
    |                               |-- OperationProvider.java
    |                               |-- BasicOperationRequest.java
    |                           |-- service
    |                               |-- AdditionService.java
    |                               |-- OperationService.java
    |                               |-- SubtractionService.java
    |                               |-- TracerService.java
    |                           |-- CalculatorApplication.java
    |       |-- resources
    |           |-- application.yml
    |           |-- calculator-api.yml
    |   |-- test
    |       |-- java
    |           |-- com
    |               |-- sanitas
    |                   |-- test4
    |                       |-- calculator
    |                           |-- controller
    |                               |-- CalculatorControllerTest.java
    |                           |-- exception
    |                               |-- ExceptionMessagesTest.java
    |                               |-- GlobalExceptionHandlerTest.java
    |                               |-- InvalidOperationExceptionTest.java
    |                           |-- factory
    |                               |-- OperationFactoryTest.java
    |                           |-- model
    |                               |-- ApiErrorResponseTest.java
    |                               |-- BasicOperationRequestTest.java
    |                               |-- OperationResponseTest.java
    |                           |-- service
    |                               |-- AdditionServiceTest.java
    |                               |-- OperationServiceTest.java
    |                               |-- SubtractionServiceTest.java
    |                           |-- CalculatorApplicationIntegrationTests.java
    |                           |-- CalculatorApplicationTests.java
    |-- pom.xml
    |-- README.md
</pre>

Descripción directorios:

* configuration: Contiene las configuraciones de la aplicación.
  - OpenAPIConfig.java: Configuración de OpenAPI.
  - OperationConfig.java: Configuración de operaciones.

* controller: Contiene los controladores de la aplicación.
    - CalculatorController.java: Controlador API principal.

* exception: Contiene las clases relacionadas con excepciones y manejo de errores.
    - CalculatorException.java: Excepción personalizada.
    - ExceptionMessages.java: Configuración de mensajes de excepción.
    - GlobalExceptionHandler.java: Manejador global de excepciones.
    - InvalidOperationException.java: Excepción para operaciones no válidas.

* factory: Contiene la fábrica de operaciones.
    - OperationFactory.java: Fábrica para obtener operaciones.

* model: Contiene las clases de modelos.
    - ApiErrorResponse.java: Respuesta de error estándar.
    - BasicOperationRequest.java: Solicitud básica de operación.
    - OperationResponse.java: Respuesta de la operación aritmética.

* service: Contiene los servicios de la aplicación.
    - AdditionService.java: Servicio de adición.
    - OperationService.java: Interfaz para los servicios de operaciones.
    - SubtractionService.java: Servicio de sustracción.
    - TracerService.java: Servicio para rastreo.

* calculator: Contiene la aplicación principal.
    - CalculatorApplication.java: Punto de entrada de la aplicación

* resources: Contiene ficheros de propiedades y configuración.

## 2. Dependencias Utilizadas

* Spring Boot Starter Web: Proporciona un conjunto de dependencias para desarrollar aplicaciones web con Spring Boot, incluyendo Spring MVC.
* Spring Boot DevTools: Ofrece herramientas de desarrollo para mejorar la productividad, como la recarga automática.
* Project Lombok: Reduce la necesidad de escribir código boilerplate en Java, generando automáticamente métodos, constructores, etc.
* Calculator (io.corp): Dependencia para tracear los resultados y excepciones de la calculadora.
* JUnit Jupiter: Framework para escribir y ejecutar pruebas unitarias en Java.
* Spring Boot Starter Test: Proporciona dependencias para pruebas de integración y soporte de Spring Boot para pruebas.
* JaCoCo Maven Plugin: Plugin para analizar la cobertura de código y generar informes.
* Springdoc OpenAPI: Genera automáticamente la documentación OpenAPI (anteriormente conocida como Swagger) para las API de Spring Boot.


## 3. Decisiones de implementación y Alcance del Servicio

### Descripción General
        
La aplicación que se ha desarrollado para la prueba técnica, es un microservicio de calculadora que permite realizar operaciones de suma y resta.
A continuación, se detallan las funcionalidades implementadas, el alcance del servicio y otros aspectos relevantes.

### Arquitectura

La arquitectura del proyecto, está diseñada para permitir la fácil incorporación de operaciones adicionales en el futuro.
Microservicio Spring Boot: La aplicación sigue el paradigma de microservicios utilizando el framework Spring Boot, facilitando el desarrollo y despliegue independiente.
Controlador (CalculatorController): Implementa los puntos finales REST para recibir las solicitudes de operaciones y gestionar las respuestas.
Servicios de Operación (AdditionService y SubtractionService): Implementan las lógicas de negocio para realizar operaciones de suma y resta, respectivamente.
Factory (OperationFactory): Encargado de proporcionar la implementación correcta del servicio de operación según la solicitud.
Manejo de Excepciones (GlobalExceptionHandler): Gestiona las excepciones personalizadas, proporciona respuestas adecuadas y tracea las excepciones con libreria proporcionada TracerAPI.
Configuración (OpenAPIConfig y OperationConfig): Configuraciones relacionadas con la documentación OpenAPI y la configuración de operaciones soportadas.

Principios SOLID

    1. Principio de Responsabilidad Única (SRP):
       Ejemplo: Cada clase tiene una única razón para cambiar.
       CalculatorController: Se encarga de gestionar las solicitudes HTTP y dirigirlas al servicio correspondiente.
       OpenAPIConfig: Se encarga de la configuración de OpenAPI.
    2. Principio de Abierto/Cerrado (OCP):
       Ejemplo: Las clases están diseñadas para ser extendidas, pero no modificadas.
       GlobalExceptionHandler: Maneja diferentes tipos de excepciones sin modificar su código.
    3. Principio de Sustitución de Liskov (LSP):
       Ejemplo: Las clases derivadas pueden sustituir a sus clases base sin cambiar el comportamiento del programa.
       OperationException: Las clases AdditionService y SubtractionService implementan la interfaz OperationService sin cambiar su comportamiento.
    4. Principio de Segregación de Interfaces (ISP):
       Ejemplo: Las interfaces deben ser específicas para los clientes que las utilizan.
       OperationService: Interfaz que define un único método (performOperation) específico para operaciones matemáticas.
    5. Principio de Inversión de Dependencia (DIP):
       Ejemplo: Las clases dependen de abstracciones y no de implementaciones concretas.
       OperationFactory: Utiliza la interfaz OperationProvider para obtener las operaciones sin depender directamente de las implementaciones concretas.
       Patrón Factory: Implementado en OperationFactory para la creación de servicios de operación.

Patrones de Diseño

    1. Patrón Factory:
    Ejemplo: OperationFactory actúa como una fábrica que proporciona la implementación correcta del servicio de operación según la solicitud.
    2. Patrón Singleton:
    Ejemplo: TracerService sigue una implementación singleton al usar la anotacion @Service y tener una única instancia final de TracerImpl,
    segurando que tracerImpl sea inmutable y constante en la instancia de TracerService.
    3. Patrón de Inversión de Control (IoC):
    Ejemplo: Concepto fundamental en el contexto del contenedor de inversión de control (IoC) proporcionado por Spring Framework.
    4. Patrón Adapter:
    Ejemplo: TracerService actúa como un adaptador para hacer que TracerImpl sea compatible con TracerAPI.

### Alcance del Servicio

El servicio permite realizar operaciones aritméticas básicas de suma y resta, entre dos o más números.
- Suma:

![successful addition operation.png](doc%2Fimages%2Fsuccessful%20addition%20operation.png)
- Resta:

![successful subtraction operation.png](doc%2Fimages%2Fsuccessful%20subtraction%20operation.png)
- Operación no soportada:

![invalid operation.png](doc%2Fimages%2Finvalid%20operation.png)
### Consideraciones Adicionales

* Inyección de Dependencias: Uso de la inyección de dependencias de Spring para facilitar la gestión de componentes y servicio
* Documentación Automática: Se genera automáticamente la documentación OpenAPI para facilitar la comprensión y el consumo de la API.
* Pruebas Unitarias e Integración: Se han implementado pruebas unitarias y de integración utilizando JUnit 5 y Spring Boot Starter Test.
* Gestión de Errores y Excepciones: El servicio gestiona adecuadamente errores y excepciones proporcionando respuestas claras.
* Cobertura de Código: Se ha integrado JaCoCo para evaluar la cobertura del código, de esta forma nos aseguramos una cobertura de más del %80.

## 4. Procedimiento de ejecución y compilación

1) Instalar y configurar en tu entorno:
   1) JDK 17 o asegurate de tener cualquier versión compatible
   2) Maven

2) Descargar el Repositorio.
   Clonar el repositorio utilizando el comando de git o bajándolo de github:
   `git clone https://github.com/DVdiego/calculadora-sanitas.git`

3) Instalar librería Tracer en el repositorio local de maven.
   Ejecutar el siguiente comando:
   `mvn validate`

4) Compilar el API
   Para compilar el API se debe ejecutar el siguiente comando en la consola de comandos de Windows en el directorio raíz del proyecto.
   `mvn clean install`
5) Ejecutar el API
   Para ejecutar el API se debe ejecutar el siguiente comando en el directorio raíz del proyecto:
   `mvn spring-boot:run`
   o de forma alternativa, ejecuta el comando: 
   `java -jar target\calculator-0.0.1-SNAPSHOT.jar`

6) Consumo de la API
   La documentación OpenAPI del API está disponible en la siguiente ruta (también se puede consumir la API desde aquí):
   http://localhost:8080/swagger-ui/index.html#/
7) Para generar un informe de cobertura utilizando JaCoCo
   `mvn clean test jacoco:report`
   Después de ejecutar este comando, el informe de cobertura estará en siguiente ubicación del proyecto:
   [target/site/jacoco/index.html](target%2Fsite%2Fjacoco%2Findex.html)

![JaCoCo reporte cobertura.png](doc%2Fimages%2FJaCoCo%20reporte%20cobertura.png)

   A continuación, se presentan ejemplos de llamadas de la API utilizando la herramienta curl o importarlos en Postman:

      Suma 
      `curl -X POST -H "Content-Type: application/json" -d '{"operation": "+", "numbers": [2, 3, 5]}' http://localhost:8080/calculator/v1/perform`

      Resta
      `curl -X POST -H "Content-Type: application/json" -d '{"operation": "-", "numbers": [5, 3]}' http://localhost:8080/calculator/v1/perform`

      Operación Inválida
      `curl -X POST -H "Content-Type: application/json" -d '{"operation": "invalidOperation", "numbers": [2, 3]}' http://localhost:8080/calculator/v1/perform`

   Hay que ajustar la URL según la configuración del entorno de desarrollo. Además, estos ejemplos se pueden adaptar según tus necesidades y preferencias.


## 5. Documentación

### Ingeniería de Requisitos:

Requisitos Funcionales:

	El sistema debe ser capaz de realizar operaciones de suma y resta.
	Se prevé la capacidad de incorporar operaciones adicionales en futuras versiones.
	La aplicación debe ser una API RESTful que acepte parámetros de entrada para las operaciones aritméticas.
	Se debe implementar una funcionalidad que permita la trazabilidad de las operaciones utilizando una librería externa (tracer).
	
Requisitos No Funcionales:

	La aplicación debe mantenerse bajo control de versiones en Git.
	Utilizar Maven como manejador de dependencias.
	Incluir la dependencia del jar de la librería de traceo mediante el plugin maven-install-plugin.
	Desarrollar la aplicación como un proyecto Spring Boot.
	Realizar pruebas unitarias con JUnit5, alcanzando al menos un 80% de cobertura.
	Utilizar una versión mínima de Java JDK11.
	Construir la API RESTful siguiendo el estándar de OpenAPI.
	Aplicar principios SOLID en el diseño y desarrollo.
	Implementar un manejo de excepciones para operaciones no válidas.
	Asegurar que la aplicación compile sin errores en una instalación de Maven limpia.
	Utilizar el tipo de dato BigDecimal para los operandos.
	Incluir un fichero README con información sobre la estructura del proyecto, dependencias, funcionalidades, alcance del servicio, procedimientos de compilación y ejecución, y ejemplos de llamadas a la API.

### Análisis Funcional:

	Operaciones Soportadas:
	La aplicación debe admitir operaciones de suma (addition) y resta (subtraction).
	Se prevé la facilidad de incorporar nuevas operaciones en futuras versiones.
	
	Parámetros de Entrada:
	Los parámetros de entrada deben incluir la operación a realizar (operation) y los operandos (numbers).
	
	Método Genérico:
	Se debe explorar la posibilidad de crear un método genérico que pueda realizar diferentes operaciones según el operador proporcionado.
	
	Trazabilidad con Tracer:
	La librería de traceo (tracer) debe inyectarse y configurarse como un bean en la aplicación para registrar el resultado de las operaciones y posibles excepciones.
	La traza debe incluir información relevante sobre las operaciones realizadas.

Historias de Usuario:

	Microservicio Calculadora:
	Como usuario, puedo realizar operaciones de suma y resta a través de la API.
	Espero recibir mensajes claros y descriptivos cuando ocurran errores durante las operaciones.

### Planificación y Tareas:

Historia:

    Como analista programador, quiero implementar un microservicio de calculadora para realizar operaciones aritméticas básicas a través de una API RESTful.
	Esto permitirá a los usuarios realizar sumas y restas de dos números, sentando las bases para incorporar operaciones adicionales en el futuro.

Tareas y Subtareas:

	1. Desarrollo del Microservicio:
	   - Subtarea 1: Configurar un nuevo proyecto en el repositorio de código (p.ej., GitHub).
	   - Subtarea 2: Inicializar un proyecto Spring Boot utilizando Spring Initializr.
	   - Subtarea 3: Configurar Maven como manejador de dependencias. 
	   - Subtarea 4: Añadir dependencias para test, cobertura en pruebas y para generar reporte, etc 
	   - Subtarea 5: Añadir liberia TracerAPI 

	2. Desarrollo Dirigido por Pruebas (TDD) - Operaciones Aritméticas:

	   - Subtarea 1: Escribir una prueba para `AdditionService` que sume dos números. 
	   - Subtarea 2: Implementar `AdditionService` para que pase la prueba. 
	   - Subtarea 3: Desarrollar pruebas para `SubtractionService` que reste dos números. 
	   - Subtarea 4: Implementar `SubtractionService` para que pase la prueba. 
	   - Subtarea 5: Escribir pruebas adicionales para cubrir casos específicos. 

	3. Desarrollo Dirigido por Pruebas (TDD) - OperationFactory:
	   - Subtarea 1: Desarrollar pruebas para `OperationFactory` que verifiquen su capacidad de crear instancias para los servicios que implementan `OperationService.  
	   - Subtarea 2: Implementar `OperationFactory` para que pase las pruebas. 
	   - Subtarea 3: Desarrollar pruebas para `BasicOperationRequest` 
	   - Subtarea 4: Implementar `BasicOperationRequest` para que pase las pruebas. 
	   - Subtarea 5: Escribir pruebas adicionales para cubrir casos específicos.
       - Subtarea 6: Implementar `OperationResponse` para que pase las pruebas.

	4. Exposición de la API RESTful:
	   - Subtarea 1: Desarrollar pruebas para `CalculatorController`
	   - Subtarea 2: Desarrollar un controlador con OpenAPI (`CalculatorController`) para manejar las solicitudes HTTP. 
	   - Subtarea 3: Exponer un endpoint para realizar operaciones aritméticas a través de la API RESTful. 
	   - Subtarea 4: Validar y procesar los parámetros de entrada en el controlador.
	   - Subtarea 5: Definir configuración de la API
       - Subtarea 6: Añadir test de integración para suma, resta y operaciones no soportadas

	5. Manejo de Excepciones y Traceo:
	   - Subtarea 1: Implementar la clase `InvalidOperationException` para representar operaciones no válidas. 
	   - Subtarea 2: Configurar un manejo global de excepciones (`@RestControllerAdvice`) para registrar trazas de excepciones. 
	   - Subtarea 3: Implementar la librería de traceo (`TracerAPI`) para registrar resultados y excepciones. 
	   - Subtarea 4: Añadir más métodos para capturar todas las excepciones con la librería de traceo (`TracerAPI`).  
	   - Subtarea 5: Añadir respuesta de error personalizada

	6. Documentación del Proyecto:
	   - Subtarea 1: Crear un archivo README detallado que incluya la estructura del proyecto, dependencias utilizadas, funcionalidades implementadas,
	   alcance del servicio, procedimientos de compilación y ejecución, ejemplos de llamadas de la API, y decisiones de implementación.
	
    7. Pruebas Unitarias y Cobertura:
	   - Subtarea 1: Desarrollar pruebas unitarias para cada clase utilizando JUnit5 y TDD. 
	   - Subtarea 2: Superar un 80% de cobertura de código mediante pruebas unitarias.  
       - Subtarea 3: Añadir test para comprobar que las propiedades para las excepciones se leen correctamente



Definición de Hecho (DoD):
- Todas las tareas y subtareas están completas.
- Las pruebas unitarias han pasado y la cobertura de código es del 80% o superior.
- Las pruebas de integración del API para las operaciones de suma y resta han sido ejecutadas con éxito.
- La prueba de integración del API para las operaciones no soportadas devuelven un mensaje de error apropiado.
- La documentación del proyecto está actualizada y completa.
- El código ha sido almacenado en un servidor de hosting de código.
- La prueba de concepto ha sido validada por el equipo.