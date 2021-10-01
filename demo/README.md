Proyecto Spring: Fase inicial terminada

Controladores: persona, calculadora y hola mundo
Servicios: persona, calculadora y hola mundo
Repositorio: persona

Los controladores de persona son los siguientes:

"Registrador" que se encarga de registrar una persona en la base de datos,
en caso de que el nombre de la persona (entiendase por nombre un nombre de usuario)
o el correo de la persona ya estén registrados en la base de datos lanza la
excepción "Nombre ocupado" y "Correo ocupado" respectivamente.

Para esto tiene una relación de asociación con ServicioRegistrador,
el cual valida que la persona no se encuentre repetida en la base de datos
y llama al repositorio para guardar a la nueva persona.

"Buscador" que se encarga de buscar una o más personas en la base de datos,
en caso de no encontrarla lanza la excepción "Persona no encontrada".

Buscador, por su parte, tiene una relación de asociación con ServicioBuscador,
utilizando los métodos "Get Persona por ID", "Get Persona por Correo" y "Get todos".

"Borrador" que se encarga de borrar a la persona solicitada en la base de datos,
en caso de no encontrarla lanza la excepción "Persona no encontrada".

Borrador borra al usuario utilizando los métodos de ServicioBorrador, quien tiene el
método "Delete Persona".

Los controladores de calculadora son:

"Sumar", "Restar", "Multiplicar" y "Dividir". En caso de que no se pueda dividir
porque el divisor es 0, entonces lanza la excepción "División entre cero".

Para ello utiliza los métodos típicos de la calculadora de la clase ServicioCalculadora.

El controlador Hola mundo solo se dedica a devolver el método Hola mundo.

Para ello utiliza el método Saludar de la clase ServicioHolaMundo.

Además, cuenta con pruebas unitarias realizadas con Mockito y la base
de datos de prueba de H2.