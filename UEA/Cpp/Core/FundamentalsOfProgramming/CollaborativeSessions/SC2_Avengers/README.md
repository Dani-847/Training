# Sesión colaborativa 2 · Avengers [Dificultad: 3/5]

**Conceptos:** Nivel 7 (struct) · Nivel 5 (función que recibe un array por referencia) · porcentajes (`100.0*n/size`) · Nivel 9 (sort con proyección) · Nivel 10 (chunk_by) · lambda (opcional)

## Enunciado

Fecha de entrega 22 de oct por 12:30 Puntos 10 Tipo de entrega: un archivo cargado Tipos de archivo cpp
Avengers [Dificultad: 3/5]
Inspirándonos en el universo Marvel, consideremos el siguiente array de personajes (el booleano indica si el personaje fue destruido por Thanos):

```cpp
std::array<Character, 12>{{
    {"Iron Man",        "Avenger", false},
    {"Loki",            "Villain", true },
    {"Captain America", "Avenger", false},
    {"Ultron",          "Villain", false},
    {"Thor",            "Avenger", false},
    {"Black Widow",     "Avenger", false},
    {"Spider-Man",      "Avenger", true },
    {"Wolverine",       "Mutant",  false},
    {"Wasp",            "Avenger", true }, 
    {"Doctor Strange",  "Avenger", true },
    {"Deadpool",        "Mutant",  false},
    {"Captain Marvel",  "Avenger", false}
}};
```
[A] Definid el agregado Character adecuado para los datos proporcionados.

[B] Cread una función que reciba por referencia al array completo y retorne cuántos personajes Avenger existen en el array. Desde main(), imprimid dicho número, junto al porcentaje de Avengers respecto al total.

[C] Cread una función que reciba por referencia al array completo y retorne el número de personajes destruidos por Thanos. Desde main(), imprimid dicho número, junto a su porcentaje respecto al total.

[D] Mediante un uso apropiado del algoritmo de ordenación std::ranges::sort y la vista std::views::chunk_by, imprimid en terminal los distintos tipos de personajes en el array ("Avenger", "Mutant", "Villain") y, para cada uno de ellos, un desglose de sus personajes, indicando si sobrevivieron o no a Thanos. Los personajes de cada grupo deben aparecer ordenados alfabéticamente.

Deberéis entregar el código del ejercicio con extensión .cpp, 100% conforme con el estándar ISO C++23.

No se permite el uso de herramientas de IA generativa para la resolución del ejercicio. Cualquier código que emplee técnicas de programación diferentes a las abordadas en el curso hasta la fecha se considerará inválido.

## Solución

- `solution.cpp`: solución oficial.
- El predicado de `chunk_by` está escrito como **lambda**. Una función con nombre, como en U2-4, funciona igual.
- La solución oficial no imprime si cada personaje sobrevivió, aunque el apartado [D] lo pide.
- `proposed_solution.cpp`: versión completa. Indica "sobrevivió" o "destruido por Thanos" y usa una función con nombre en lugar de la lambda.

## Ejemplo de ejecución

Salida de `proposed_solution.cpp`:

```text
Avengers: 8 (66.7%)
Destruidos por Thanos: 4 (33.3%)
Tipo Avenger (8 personajes):
  - Black Widow: sobrevivió
  - Captain America: sobrevivió
  - Captain Marvel: sobrevivió
  - Doctor Strange: destruido por Thanos
  - Iron Man: sobrevivió
  - Spider-Man: destruido por Thanos
  - Thor: sobrevivió
  - Wasp: destruido por Thanos
Tipo Mutant (2 personajes):
  - Deadpool: sobrevivió
  - Wolverine: sobrevivió
Tipo Villain (2 personajes):
  - Loki: destruido por Thanos
  - Ultron: sobrevivió
```
