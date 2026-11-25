# Sesión colaborativa 3 · Spider-Man [Dificultad: 3/5]

> 🎯 **REFERENCIA PARA EL USO DE STRUCTS EN EL EXAMEN.** El struct que se pedirá será de este nivel: un struct sencillo, ordenar por un miembro y agrupar con `chunk_by`.
> - El apartado **[A]** (leer el fichero `.jsonl` en un `vector` con nlohmann) **no entra en el examen**: es igual que CE5 (está como anexo en los apuntes).
> - El apartado **[B]** (ordenar + `chunk_by` dentro de `chunk_by` sobre structs) es lo que hay que practicar. Usa `proposed_solution_without_file.cpp`, donde los datos van en un `std::array`.

**Conceptos:** Nivel 7 (struct sencillo con dos strings) · Nivel 9 (sort con proyección, también sobre un grupo) · Nivel 10 (chunk_by anidado) · Nivel 13 (ficheros y JSON, solo para [A], no entra)

## Enunciado

Fecha de entrega 12 de nov por 12:10 Puntos 10 Tipo de entrega: un archivo cargado Tipos de archivo cpp Intentos 0 Intentos permitidos 2
Spider-Man [Dificultad: 3/5]
Tomemos como modelo el videojuego 'Marvel's Spider-Man 2' (Insomniac Games para PS5, 2023). Asumiremos que el intrépido Peter Parker puede visitar cuatro distritos de la ciudad de Nueva York (Harlem, Queens, Upper East Side y Upper West Side) a fin de completar, en cada uno de ellos, un número variable de misiones/desafíos de tres tipos diferentes, a saber:

Misiones "Kraven the Hunter": erradicación de una facción de mercenarios que azota la ciudad.
Misiones "Venom symbiote": ataques contra Venom, un simbionte extraterrestre capaz de unirse a cuerpos humanos como anfitriones.
Misiones "Mysterium VR": visitas a un mundo de realidad virtual poblado de enemigos cibernéticos.
Diseña un programa realizando los puntos siguientes:

A [2/10]. Abre el fichero JSON Lines peter_parker.jsonl asociado a esta actividad en modo std::ios::binary (véase la documentación proporcionada más abajo). Este fichero contiene 100 misiones registradas en forma de objetos JSON, cada uno de los cuales especifica el distrito donde se desarrolla (clave "district") y el tipo al que pertenece (clave "type").

Consideremos un agregado de datos Mission capaz de almacenar, para cada misión, dos strings: uno con el nombre del distrito y el otro con el tipo de misión que corresponda ("Kraven the Hunter", "Venom symbiote" o "Mysterium VR"):

```cpp
struct Mission {
    std::string district;
    std::string type;
};
```

Deserializa el contenido del fichero en un vector estándar de objetos Mission.

Nota: Si inspeccionas el fichero JSON, observarás que éste contiene líneas que resultan idénticas, al corresponder a desafíos de un mismo tipo en un mismo distrito. Sin embargo, asumiremos que corresponden a misiones realmente distintas (a completar en ubicaciones diferentes del distrito, aun cuando las coordenadas geográficas de cada misión no se incluyan en el fichero a fin de simplificar su contenido) y las registraremos todas ellas en el vector.

B [8/10]. Muestra por la terminal un listado de los distritos de nuestro videojuego ordenados alfabéticamente. Para cada distrito, proporciona un listado de los distintos tipos de misiones en él contenidos --ordenados también alfabéticamente-- junto al número total de desafíos asociados a ellos. Este resultado debe alcanzarse empleando el vector del punto A, llamadas al algoritmo std::ranges::sort y vistas std::views::chunk_by (tantas como consideres necesario). El programa debe deducir tanto los distritos como los tipos de misión a partir del vector, sin poder explicitar sus nombres en el propio código. Puedes visualizar el resultado a reproducir en la captura inferior derecha.

Deberás entregar el código del ejercicio con extensión .cpp, 100% conforme con el estándar ISO C++23. Se permite el uso de la biblioteca no-estándar NLohmann's JSON for Modern C++ (https://github.com/nlohmann/json).

> Nota: el enunciado habla de una "captura inferior derecha" con el resultado. Esa imagen no está en el material; el ejemplo de ejecución de abajo da la salida esperada.

## Solución

- `proposed_solution_with_file.cpp`: versión completa (A + B), igual que CE5. Necesita nlohmann-json y el fichero `peter_parker.jsonl` en la carpeta desde la que se ejecuta. Compilada y probada con nlohmann-json 3.11.3.
- `proposed_solution_without_file.cpp`: solo el apartado [B], con los datos en un `std::array<Mission, 100>`. Compila sin bibliotecas extra.
- Ninguna es oficial: el campus no publica solución.

**Idea clave del apartado [B]:**
1. `ranges::sort` por distrito.
2. `chunk_by` por distrito → un grupo por distrito.
3. Dentro de cada grupo: `ranges::sort` por tipo.
4. `chunk_by` por tipo → `type_group.size()` = número de misiones.

## Ejemplo de ejecución

Salida de `proposed_solution_without_file.cpp`. La versión con fichero imprime además, al principio, "Misiones leídas: 100".

```text
Harlem:
   - Kraven the Hunter: 15 misiones
   - Mysterium VR: 5 misiones
   - Venom symbiote: 8 misiones
Queens:
   - Kraven the Hunter: 7 misiones
   - Mysterium VR: 8 misiones
   - Venom symbiote: 9 misiones
Upper East Side:
   - Kraven the Hunter: 10 misiones
   - Mysterium VR: 4 misiones
   - Venom symbiote: 8 misiones
Upper West Side:
   - Kraven the Hunter: 7 misiones
   - Mysterium VR: 13 misiones
   - Venom symbiote: 6 misiones
```
