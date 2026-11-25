# U1-6 · Inventario básico

**Conceptos:** Nivel 6 (array de string, .empty()) · Nivel 3 (getline)

## Enunciado

En un videojuego, el jugador tiene un inventario con 5 espacios para objetos. Escribe un programa que:

Declare un std::array<std::string, 5> para representar el inventario.
Pida al usuario que introduzca el nombre de cada objeto (uno por espacio).
Recorra el inventario con un bucle for y muestre todos los objetos introducidos.
Si algún espacio está vacío (cadena vacía), muestra el mensaje “Espacio libre” en su lugar.

## Solución

- `solution.cpp`: solución oficial del profesor. Ojo: además ordena el inventario (no se pide) y muestra "Espacio en blanco" en vez de "Espacio libre".
- `proposed_solution.cpp`: versión ajustada al enunciado, sin ordenar y con "Espacio libre".

## Ejemplo de ejecución

Datos escritos por el usuario: "Espada", (Enter sin escribir nada), "Poción", "Escudo", (Enter sin escribir nada).

Salida de `proposed_solution.cpp`:

```text
Introduce 5 objetos (pulsa Enter sin escribir nada para dejar el espacio libre):
Espacio #1: Espada
Espacio #2: 
Espacio #3: Poción
Espacio #4: Escudo
Espacio #5: 
--- Inventario ---
#1: Espada
#2: Espacio libre
#3: Poción
#4: Escudo
#5: Espacio libre
```
