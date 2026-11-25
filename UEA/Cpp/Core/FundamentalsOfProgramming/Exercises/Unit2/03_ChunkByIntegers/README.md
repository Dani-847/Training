# U2-3 · Vista chunk_by para array de enteros ordenados

**Conceptos:** Nivel 10 (views::chunk_by) · Nivel 5 (predicado binario)

## Enunciado

Partiendo del array ya ordenado de números enteros {1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5}, emplea la vista std::views::chunk_by para delimitar los subgrupos de números idénticos. Itera los subgrupos resultantes con un bucle for-each imprimiendo, para cada uno de ellos, su tamaño y el elemento de índice 0 como representante.

## Solución

- `solution.cpp`: solución oficial del profesor.

## Ejemplo de ejecución

Salida de `solution.cpp`:

```text
hay 3 elementos iguales al 1
hay 8 elementos iguales al 3
hay 4 elementos iguales al 5
```
