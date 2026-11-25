# U1-9 · Empleo básico de punteros #2

> ⚪ **NO CAE EN EL EXAMEN.** Los punteros no entran, pero es interesante saber qué hacen: ayudan a entender las referencias. Hazlo solo por curiosidad.

**Conceptos:** Nivel 7 (struct + método) · Nivel 8 (puntero a struct, ->)

## Enunciado

Define una estructura Student con los siguientes miembros públicos:

std::string name
double grade_1
double grade_2
double grade_3

Implementa en Student un método average() que devuelva la media aritmética de las tres calificaciones. A continuación, en main():

Crea un objeto Student llamado s con nombre "James Kerry" y las notas 6.0, 8.0, 9.5.
Declara un puntero Student* p que apunte a s.
Muestra por pantalla, usando std::println, el nombre del estudiante y su nota media con dos decimales, accediendo a los miembros a través del puntero (p->...).
Modifica la primera nota del estudiante a 7.5 utilizando el puntero.
Vuelve a imprimir el nombre y la nueva media con dos decimales para comprobar el cambio.

## Solución

- `proposed_solution.cpp`: solución **no oficial**. El campus no da solución; está escrita siguiendo el estilo de las del profesor.

## Ejemplo de ejecución

Salida de `proposed_solution.cpp`:

```text
James Kerry: media = 7.83
James Kerry: media = 8.33
```
