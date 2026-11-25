# Simulacro de examen · Torneo de eSports [Dificultad: 3/5]

> Ejercicio de práctica **creado para este repaso** (no viene del campus). Mezcla en un solo programa todo lo que entra en el examen. Hazlo al final, cuando hayas terminado U1, U2, CE1, CE2, SC1 y SC2.
> Tiempo orientativo: 60–75 minutos.

**Conceptos:** struct · array de structs · funciones con paso por referencia · máximo con índice · getline + stoi + try/catch + while · contador y porcentaje · partition + iteradores · sort con proyección · chunk_by · take

## Enunciado

Un torneo de eSports guarda la información de sus jugadores en el siguiente array:

```cpp
std::array<Player, 8>{{
    {"Nova",    "Dragons", 87, true },
    {"Kaiser",  "Wolves",  64, false},
    {"Pixel",   "Dragons", 92, true },
    {"Shadow",  "Titans",  45, true },
    {"Blaze",   "Wolves",  78, true },
    {"Echo",    "Titans",  81, false},
    {"Raven",   "Dragons", 39, false},
    {"Volt",    "Titans",  70, true }
}};
```

Cada jugador tiene un nombre, un equipo, sus puntos y un booleano que indica si sigue en activo.

**[A]** Define el agregado `Player` adecuado para esos datos.

**[B]** Crea una función que reciba el array por referencia y devuelva la suma de los puntos de todos los jugadores. Desde `main()`, imprime el total y la media con dos decimales.

**[C]** Crea una función que devuelva el **índice** del jugador con más puntos. En `main()`, usa ese índice para imprimir su nombre, su equipo y sus puntos.

**[D]** Pide al usuario una puntuación mínima entre 0 y 100. Si escribe algo que no es un número, o un número fuera de rango, el programa debe volver a pedirla (usa `while` + `try/catch`). Cuenta cuántos jugadores tienen al menos esa puntuación e imprime el número y el porcentaje respecto al total.

**[E]** Con `std::ranges::partition`, pon delante a los jugadores activos. Ordena **solo a los activos** por puntos con `std::ranges::sort`. Usa iteradores para imprimir por un lado los activos (nombre y puntos) y por otro los inactivos.

**[F]** Ordena el array por equipo y, con `std::views::chunk_by`, imprime para cada equipo su nombre, su número de jugadores y la suma de sus puntos.

**[G]** Ordena el array por puntos y, con `std::views::take`, imprime los 3 jugadores con menos puntos.

## Pistas (míralas solo si te atascas)
- [C] Es el patrón "máximo con índice" de SC1. La función devuelve `size_t` (el tipo de `0uz`).
- [D] Mira la función `read_min_points()` de la solución: un `while (true)` del que se sale con `return`.
- [E] Es lo mismo que U1-13 y U1-14. Con un iterador, `it->name` da el miembro `name` del elemento señalado.
- [F] Es lo mismo que CE1 y U2-4: **ordenar antes** de `chunk_by`.

## Solución

- `proposed_solution.cpp`: solución propia (no oficial), en el estilo del profesor.

## Ejemplo de ejecución

Datos escritos por el usuario: "hola", "150", "70".

Salida de `proposed_solution.cpp`:

```text
[B] Puntos totales: 556 | media: 69.50
[C] Mejor jugador: Pixel (Dragons) con 92 puntos
Puntuación mínima (0-100): hola
Eso no es un número, prueba otra vez.
Puntuación mínima (0-100): 150
Fuera de rango, prueba otra vez.
Puntuación mínima (0-100): 70
[D] Jugadores con al menos 70 puntos: 5 (62.5%)
[E] Activos (por puntos):
     Shadow  45
     Volt    70
     Blaze   78
     Nova    87
     Pixel   92
    Inactivos:
     Echo
     Raven
     Kaiser
[F] Resumen por equipo:
     Dragons  3 jugadores, 218 puntos
     Titans   3 jugadores, 196 puntos
     Wolves   2 jugadores, 142 puntos
[G] Los 3 con menos puntos:
     Raven (39)
     Shadow (45)
     Kaiser (64)
```
