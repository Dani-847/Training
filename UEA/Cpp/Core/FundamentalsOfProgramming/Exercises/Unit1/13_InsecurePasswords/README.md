# U1-13 · Filtrado de contraseñas inseguras

**Conceptos:** Nivel 9 (partition, sort de un subrango, iteradores) · Nivel 5 (predicado)

## Enunciado

Un sistema almacena contraseñas en un std::array<std::string, 6>{"admin", "hunter2", "access87", "1234578", "cyberSec99", "root"}. Implementa un programa que:

Use la función std::ranges::partition (documentaciónLinks to an external site.) para separar contraseñas seguras (longitud ≥ 8 y que contengan al menos un dígito) de las inseguras.
Ordene las contraseñas seguras alfabéticamente con la función std::ranges::sort.
Utilice iteradores para recorrer el array y mostrar las contraseñas seguras por una parte y las inseguras por otra.

## Solución

- `proposed_solution.cpp`: solución **no oficial**. El campus no da solución; está escrita siguiendo el estilo de las del profesor.

## Ejemplo de ejecución

Salida de `proposed_solution.cpp`:

```text
Seguras:
  access87
  cyberSec99
Inseguras:
  hunter2
  1234578
  admin
  root
```
