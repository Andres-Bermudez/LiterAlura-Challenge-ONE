<h1 align="center"> LiterAlura </h1>

- Arquitectura:
![arquitectura.png](imagenes/LiterAlura_Arquitectura.png)
<br>
<br>
- Inicio de la aplicacion:
![inicio.png](imagenes/inicio.png)

## Descripcion:
Esta es una aplicación desarrollada en Java con Spring Boot que permite
la búsqueda y gestión de libros mediante el consumo de la API 
de https://gutendex.com/ con persistencia de datos en PostgreSQL.

La aplicacion almacena de forma automatica cada consulta que realice
el usuario sobre un libro y tiene opciones donde podemos consultar 
sobre estos datos almacenados, realizar filtros por idioma o una fecha
en especifico.

- Consultas a la base de datos:
![consultaAño.png](imagenes/consultaA%C3%B1o.png)
  <br>
  <br>
- Filtrado de datos:
![consultaIdioma.png](imagenes/consultaIdioma.png)

La persistencia de datos en esta aplicación se gestiona mediante
Spring Data JPA, con un diseño en capas que sigue el patrón de 
repositorio.