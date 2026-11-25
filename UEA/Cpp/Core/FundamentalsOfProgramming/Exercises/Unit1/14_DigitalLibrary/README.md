# U1-14 · Biblioteca digital

**Conceptos:** Nivel 9 (partition, sort de un subrango, iteradores) · Nivel 5 (predicado)

## Enunciado

Una biblioteca digital guarda títulos de libros en un std::array<std::string, 8>:

```cpp
std::array<std::string, 8>{
    "Les Misérables",
    "Anna Karenina",
    "Oliver Twist",    
    "A Tale of Two Cities",
    "Inferno",
    "Don Quixote",
    "Ulysses",
    "The Count of Monte Cristo"
};
```

Debes:

Usar la función std::ranges::partition para separar títulos que empiezan por vocal (A, E, I, O, U) de los demás.
Ordenar los títulos que empiezan por vocal con std::ranges::sort.
Usar iteradores para imprimir los títulos que no empiezan por vocal por una parte y los que empiezan por consonante por otra.

> Nota: el último punto del enunciado original dice "no empiezan por vocal" y "empiezan por consonante", que es lo mismo. Se entiende como: imprime por un lado los que empiezan por vocal y por otro los demás.

## Solución

- `proposed_solution.cpp`: solución **no oficial**. El campus no da solución; está escrita siguiendo el estilo de las del profesor.

## Ejemplo de ejecución

Salida de `proposed_solution.cpp`:

```text
Empiezan por vocal:
  A Tale of Two Cities
  Anna Karenina
  Inferno
  Oliver Twist
  Ulysses
Empiezan por consonante:
  Don Quixote
  Les Misérables
  The Count of Monte Cristo
```
