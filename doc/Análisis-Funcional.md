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
	Incluir un fichero README con información sobre la estructura del proyecto, dependencias, funcionalidades, alcance del servicio,
    procedimientos de compilación y ejecución, y ejemplos de llamadas a la API.