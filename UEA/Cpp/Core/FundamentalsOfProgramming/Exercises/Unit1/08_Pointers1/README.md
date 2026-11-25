# U1-8 · Empleo básico de punteros #1

> ⚪ **NO CAE EN EL EXAMEN.** Los punteros no entran, pero es interesante saber qué hacen: ayudan a entender las referencias. Hazlo solo por curiosidad.

**Conceptos:** Nivel 8 (punteros: &, *)

## Enunciado

Declara un int con un valor inicial (por ejemplo, 1). Declara un puntero a int que apunte a esa variable. Imprime entonces la dirección, el valor del puntero y el valor al que apunta a través del puntero. Cambia el valor de la variable a través del puntero y verifica el cambio.

## Solución

- `proposed_solution.cpp`: solución **no oficial**. El campus no da solución; está escrita siguiendo el estilo de las del profesor.

## Ejemplo de ejecución

Salida de `proposed_solution.cpp`:

```text
dirección de x:       0x7ffd5e3c (cambia en cada ejecución)
valor del puntero p:  0x7ffd5e3c (cambia en cada ejecución)
valor apuntado (*p):  1
nuevo valor de x:     42
nuevo valor de *p:    42
```
