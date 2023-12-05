Historias de Usuario:

	Microservicio Calculadora:
	Como usuario, puedo realizar operaciones de suma y resta a través de la API.
	Espero recibir mensajes claros y descriptivos cuando ocurran errores durante las operaciones.

Planificación y Tareas:

	Historia: Como analista programador, quiero implementar un microservicio de calculadora para realizar operaciones aritméticas básicas a través de una API RESTful.
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

	4. Exposición de la API RESTful:
	   - Subtarea 1: Desarrollar pruebas para `CalculatorController`
	   - Subtarea 2: Desarrollar un controlador con OpenAPI (`CalculatorController`) para manejar las solicitudes HTTP. 
	   - Subtarea 3: Exponer un endpoint para realizar operaciones aritméticas a través de la API RESTful. 
	   - Subtarea 4: Validar y procesar los parámetros de entrada en el controlador.
	   - Subtarea 5: Definir configuración de la API y

	5. Manejo de Excepciones y Traceo:
	   - Subtarea 1: Implementar la clase `InvalidOperationException` para representar operaciones no válidas. 
	   - Subtarea 2: Configurar un manejo global de excepciones (`@RestControllerAdvice`) para registrar trazas de excepciones. 
	   - Subtarea 3: Implementar la librería de traceo (`TracerAPI`) para registrar resultados y excepciones. 
	   - Subtarea 4: Añadir más métodos para capturar todas las excepciones con la librería de traceo (`TracerAPI`).  
	   - Subtarea 5: Añadir respuesta de error personalizada

	7. Pruebas Unitarias y Cobertura:
	   - Subtarea 1: Desarrollar pruebas unitarias para cada clase utilizando JUnit5 y TDD. 
	   - Subtarea 2: Superar un 80% de cobertura de código mediante pruebas unitarias.  
	   
	6. Documentación del Proyecto:
	   - Subtarea 1: Crear un archivo README detallado que incluya la estructura del proyecto, dependencias utilizadas, funcionalidades implementadas,
	   alcance del servicio, procedimientos de compilación y ejecución, ejemplos de llamadas de la API, y decisiones de implementación.
