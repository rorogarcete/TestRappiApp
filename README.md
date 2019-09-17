# Arquitectura de la aplicacion
Este proyecto esta basado en la Arquitectura MVVM y Repository Pattern. Se ha utilizado los siguintes componentes y librerias
- Se utilizo Architecture Components (Lifecycle, LiveData, ViewModel, Room Persistence)
- DataBinding
- Material Design & Animations
- Dagger2 para inyección de dependencia
- Retrofit2 para consumir el (API REST)[https://developers.themoviedb.org/3/getting-started/introduction]

# Capas de la aplicacion

### 1 - Capa de Persistencia
- Se utilizo (Room)[https://developer.android.com/topic/libraries/architecture/room] como abstraccion del SQLite de android para almacenar.
- MovieDao: Es la clase encargado de la comunicacion con la base de datos, es implementado utilizando el DAO Pattern. Realiza operaciones con las tablas.

### 2 - Capa de Red
- Se utilizo (retrofit)[https://square.github.io/retrofit/] para realizar las peticiones al API REST.
- La paginación de implemento utilizando (Paging)[https://developer.android.com/topic/libraries/architecture/paging/]

### 3 - Capa de Negocio
- La capa de negocio es implementado utilizando Repository Pattern.
- MovieRepository: Es la fuente de datos, encargado de obtener de la cache local o del servidor.
- ViewModel: Mediante la utilizacion de LiveData se informa a la UI que se obtuvo un resultado.

### 4 - Capa de UI
- Las vistas son manejadas por las activities y los fragments
- MainActivity: Contiene un view pager con bottom navigation que contiene 3 fragments, cada uno encargado de manejar la lista de peliculas de acuerdo a la categoria que pertenecen.
- MovieDetailActivity: Encargado de mostrar el detalle una pelicula.

# Preguntas

### 1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?
Consiste en que una clase debe tener solamente una resposabilidad concreta.
El proposito es identificar y crear abstracciones en pequeños componentes de software reutilizables.

### 2. Qué características tiene, según su opinión, un “buen” código o código limpio?
- Debe tener una arquitectura definida (MVP, MVVM, CLEAN, etc). 
- En lo posible se debe aplicar en la creación de las clases el principio SOLID.
- Implementar design paterns en las distintas capas de la arquitectura (DAO, Singleton, Factory, etc)

Escribir "buen" codigo implica tener buenos nombres de variables, clases y métodos. Debe reflejar cual es su cometido, su proposito.
A parte de aplicar los Design Patterns, buenos principios de Ingenieria de Software, etc.
