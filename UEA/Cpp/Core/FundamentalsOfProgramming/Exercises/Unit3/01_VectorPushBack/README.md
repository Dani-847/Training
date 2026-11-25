# U3-1 · Inclusión de elementos en un vector mediante la función miembro push_back

> ⛔ **NO ENTRA EN EL EXAMEN.** El examen llega hasta los vectores, sin incluirlos. Puedes saltarte este ejercicio o hacerlo solo como ampliación.

**Conceptos:** Nivel 11 (vector, push_back) · Nivel 3 (EOF) · Nivel 9 (sort)

## Enunciado

Permite que el usuario introduzca tantos nombres como desee por la terminal, hasta que inserte la combinación especial de caracteres EOF (CTRL+Z en MS Windows, CTRL+D en UNIX/Linux/macOS). Almacena los nombres en un vector de strings. Una vez concluida esa operación, ordena el vector por orden lexicográfico y muestra los nombres en la terminal.

## Solución

- `solution.cpp`: solución oficial del profesor.

## Ejemplo de ejecución

Datos escritos por el usuario: "Marta", "Luis", "Ana" y después CTRL+Z (en Windows) o CTRL+D (en Linux/Mac).

Salida de `solution.cpp`:

```text
Introduce un nombre: Marta
Introduce un nombre: Luis
Introduce un nombre: Ana
Introduce un nombre: ^Z   ← CTRL+Z (fin de entrada, EOF)
Ana
Luis
Marta
```
