# Proyecto Challenge Conexa

## Descripción

Este proyecto tiene un listado de noticias donde utiliza su buscador para filtrar las noticias, donde si apretas en una noticia te lleva a su detalle.
Por otro lado, tiene un listado de usuarios, donde si apretas en un usuario te lleva a su detalle y si apretas en el icono de localidad te lleva a la latitud y longitud marcada de dicho usuario.


## Librerias Utilizadas

- Retrofit
- Navigation Component
- Hilt
- Fragments
- Coroutines
- Espresso
- GoogleMap


## Arquitectura del Proyecto

- **`MVVM + Clean Architecture`**: La arquitectura posee un manejo de errores basado en sealed clases. Result maneja los llamados a networking y en el viewmodel seccionar especificamente por codigo HTTP.
- Se utilizo la estructura de un DataSource que se encarga de llamar al servicio mapear los objetos e identificarlos (Se utiliza Result para identificar los distintos estados del llamado y identificar codigos HTTP).
- Se implemento un Repository que puede llamar a varios DataSource ya sea de Network o de DataBase.
- No se utilizo UseCase por falta de tiempo.
- Se implemento ViewModel donde abre esta sealed class Result y puede atraves de los codigos HTTP crear otra sealed class para identificar esos numeros y crear para cada caso un mensaje especifico ya sea 404, 500 o time out.
- Se implemento fragments para la fluides de las pantallas.

## Pruebas Implementadas

### Prueba de `NewsFragment`

Los tests se implementan usando **JUnit4** y **Fragment Testing**.

La prueba `NewsFragmentTest` asegura que el `RecyclerView` en `NewsFragment` tenga un adaptador configurado correctamente.