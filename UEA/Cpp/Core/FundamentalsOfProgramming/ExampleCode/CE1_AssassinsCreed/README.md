# CE1 · Assassin's Creed [Dificultad: 2/5]

> ⭐ **MUY IMPORTANTE PARA EL EXAMEN.** CE1 y CE2 son de los ejercicios principales de cara al examen.

**Conceptos:** Nivel 7 (struct con array dentro) · Nivel 5 (función que recibe un array por referencia) · Nivel 9 (sort con proyección) · Nivel 10 (chunk_by)

## Enunciado

Objetivo: En este ejemplo simularemos el estado en que un jugador de un videojuego guardó su estado de avance en el ataque a un campamento militar. 

Cada objetivo tiene un tipo (string "type") de enemigo u objetivo del campamento ("soldier", "leader", "secret weapon" o "treasure"), un valor booleano ("achieved") que indica si ha sido ya eliminado o adquirido, un nivel de dificultad (entero "level") y una posición en el mapa tridimensional (array de tres doubles "location"). Nos proponemos:

Definir un agregado de datos Target capaz de almacenar los datos de un objetivo.
Dado el array de tamaño fijo siguiente, contaremos cuántos objetivos hay de cada tipo, y cuántos de ellos han sido completados. Imprimiremos en la terminal los resultados ordenando los tipos lexicográficamente:
```cpp
   auto targets = std::array<Target, 9>{{
       {"soldier", true, 2, {1.0, 3.5, 0.0}},
       {"leader", false, 5, {2.1, 4.0, 1.2}},
       {"treasure", true, 1, {0.5, 2.2, 0.0}},
       {"soldier", false, 3, {1.5, 3.0, 0.5}},
       {"secret weapon", false, 4, {3.0, 1.0, 2.0}},
       {"leader", true, 5, {2.0, 4.5, 1.0}},
       {"soldier", true, 2, {1.2, 3.3, 0.0}},
       {"secret weapon", true, 4, {3.1, 1.2, 2.1}},
       {"soldier", false, 3, {1.6, 3.1, 0.4}}
   }};
```

Puedes consultar el resultado esperado en la imagen inferior derecha.

Cuando corresponda, emplearemos la biblioteca de rangos del lenguaje C++23 a fin de adoptar un estilo funcional de programación, utilizando en particular las vistas std::views::enumerate y std::views::chunk_by.

## Solución

- `solution.cpp`: solución oficial del profesor.
- El enunciado menciona `views::enumerate`, pero la solución no lo usa (es opcional).
- El enunciado habla de una "imagen inferior derecha" con el resultado esperado. Esa imagen no está en el material.

## Ejemplo de ejecución

Salida de `solution.cpp`:

```text
leader : 1/2
secret weapon : 1/2
soldier : 2/4
treasure : 1/1
```
