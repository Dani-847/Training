# U2-2 · Latencia en redes

**Conceptos:** Nivel 10 (views::take) · Nivel 4 (while para validar) · Nivel 6

## Enunciado

Solicita al usuario 10 valores de latencia (en milisegundos) medidos en una simulación de red y almacénalos en un std::array<int, 10>.
Pregunta al usuario cuántos valores iniciales quiere promediar (por ejemplo, los 5 primeros). Usa std::views::take para crear una vista sobre los primeros N elementos del array. Calcula el promedio y muéstralo en la terminal. Valida que N esté entre 1 y 10 mediante un bucle while.

## Solución

- `solution.cpp`: solución oficial del profesor. Valida N con un `if`: si el valor no es válido, termina.
- `proposed_solution.cpp`: valida con un bucle `while`, como pide el enunciado: vuelve a preguntar hasta recibir un valor entre 1 y 10.

## Ejemplo de ejecución

Datos escritos por el usuario: "12", "15", "9", "20", "11", "14", "10", "13", "16", "18", "15", "3".

Salida de `proposed_solution.cpp`:

```text
Introduce la latencia #1 (milisegundos): 12
Introduce la latencia #2 (milisegundos): 15
Introduce la latencia #3 (milisegundos): 9
Introduce la latencia #4 (milisegundos): 20
Introduce la latencia #5 (milisegundos): 11
Introduce la latencia #6 (milisegundos): 14
Introduce la latencia #7 (milisegundos): 10
Introduce la latencia #8 (milisegundos): 13
Introduce la latencia #9 (milisegundos): 16
Introduce la latencia #10 (milisegundos): 18
Introduce el número de latencias a promediar (1-10): 15
Valor fuera de rango. Introduce un número entre 1 y 10: 3
Media de las 3 primeras latencias: 12.00 ms
```
