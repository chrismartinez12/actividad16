Pregunta. ¿Qué cambiaste en el proyecto para pasar de consola a web? ¿Tuviste que tocar
alguna clase de lógica o de persistencia? ¿Por qué? Respondé en un archivo RESPUESTAS.md en  
la raíz del proyecto.

Para pasar el proyecto de consola a web, agregué Servlets para recibir los datos de los formularios y enviarlos a 
la lógica para guardarlos en la base de datos. También agregué los archivos JSP para mostrar las páginas de artistas y 
canciones, editar, además del archivo CSS para los estilos que va a tener la web

Sí, modifiqué la parte de lógica para agregar los métodos de edición y eliminación.
En la parte de persistencia también agregué los métodos necesarios para editar y eliminar registros en la base de datos

Hice estos cambios para que el proyecto funcione desde una página web y para poder crear, editar y 
eliminar artistas y canciones

Primero, provocá los errores y anotá en RESPUESTAS.md qué pasa en cada caso:(ya lo hice):)
1. En el navegador, intentá escribir letras en el campo Edad del formulario.

cuando intentas escribir  letras en el campo de edad te sale un mensaje diciendo que no podes introducir letras

2. En Bruno, mandá un alta de artista con edad = abc. Bruno sí te deja.

Al enviar desde Bruno un artista con edad = abc, se presenta un problema con la carga del archivo. No aparece el archivo nuevo y siguen apareciendo los archivos anteriores.

3. En Bruno, mandá un alta de artista con nombre vacío.

pasa lo mismo que en el 2

Pregunta. Si el formulario no deja escribir letras en la edad, ¿para qué validar en el Servlet?

Necesitamos validar los datos porque otros programas, como Bruno, permiten enviar datos incorrectos. 
Además, se pueden enviar datos directamente al Servlet sin usar el formulario de la web
