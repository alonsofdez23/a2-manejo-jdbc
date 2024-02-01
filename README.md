# Actividad 2. Manejo de conectores de BBDD (JDBC)

## Objetivos

Aprender a manejar JDBC mediante una pequeña aplicación de gestión de coches.

## Pautas de elaboración

**Requerimiento 1**

Se desea hacer un CRUD completo de la entidad ‘Coche’, pero esta vez no se trabajará con ningún
fichero, se trabajará con una BBDD. Es muy importante usar el patrón DAO visto en clase. Los
parámetros de conexión a la BBDD deben estar hechos en un fichero de **propiedades**.

El coche tendrá los siguientes atributos: id, marca, modelo, año de fabricación y km.

El menú mostrado será de la siguiente forma:

- Añadir nuevo coche (El ID lo incrementará automáticamente la base de datos)
- Borrar coche por ID
- Consulta coche por ID
- Modificar coche por ID (pedirá todos los valores y modificará dichos valores a partir del ID del coche)
- Listado de coches
- Terminar el programa

_Valoración: 4 puntos sobre 10_

**Requerimiento 2**

Se pide añadir la siguiente funcionalidad.

Los coches, tendrán asociados N pasajeros en él (habrá que crear la tabla pasajeros y hacer la relación
pertinente). Los pasajeros tendrán los siguientes atributos, id, nombre, edad y peso. Se añadirá la opción
“gestión de pasajeros” al programa principal, dicha opción nos mostrará un submenú como el que sigue

- Crear nuevo pasajero
- Borrar pasajero por id
- Consulta pasajero por id
- Listar todos los pasajeros
- Añadir pasajero a coche, el programa nos pedirá un id de un pasajero y el id de un coche, y lo añadirá al
coche a nivel de base de datos. Sería una buena opción mostrar todos los coches disponibles.
- Eliminar pasajero de un coche, el programa nos pedirá un id de un pasajero y lo eliminará del coche a nivel
de base de datos. Sería una buena opción mostrar todos los coches y sus pasajeros asociados.
- Listar todos los pasajeros de un coche, el programa pedirá el id de un coche, y nos mostrará todos los
pasajeros asociados a él.

_Valoración: 5 puntos sobre 10_

**Requerimiento 3**

La aplicación no debe permitir que la marca y el modelo estén vacíos. Esta parte la debe de gestionar la
capa gestora y seguir el modelo de tres capas visto en clase.

_Valoración: 1 puntos sobre 10_

## Consideraciones de entrega

Para la entrega, se subirá **un documento PDF con todo lo necesario para demostrar el correcto funcionamiento
de la actividad** (resultados, capturas de pantalla, ficheros, fotos, etc.). No es necesario que el documento
PDF sea muy extenso, pero SÍ que incluya, al menos, la metodología de trabajo del grupo, las capturas de los
resultados obtenidos con los comentarios pertinentes, y la explicación de los puntos clave de la actividad
realizada. No cumplir con este punto puede llevar a suspender la actividad o a reducir considerablemente
la nota final.

Además, para toda la actividad se valorará la claridad de código, la modularidad y la eficiencia de los
algoritmos empleados.

Para la actividad se recomienda que todos los integrantes aporten una posible solución y luego elijan cual
será la solución final mediante consenso.

**Se recomienda el uso de GITHUB para realizar el trabajo** y dejar el código fuente en dicha plataforma, ya que,
si hay problemas con la entrega al subirla a la plataforma de EDIX, queda constancia en GITHUB de los commits
hechos. Se puede subir el código fuente también comprimido a la plataforma en su lugar si así se prefiere,
pero en este caso, el fichero PDF y el código comprimido (fichero .zip o .7z) deben de ir por separado.