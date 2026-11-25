# U1-11 · Análisis de ventas trimestrales

**Conceptos:** Nivel 7 (array de structs) · Nivel 9 (sort con proyección)

## Enunciado

Una empresa guarda las cifras de ventas trimestrales (en millones de euros) en sus seis sucursales europeas en el contenedor secuencial std::array<Branch, 6>{ {{"London", 4.5}, {"Paris", 4.8}, {"Madrid", 3.1}, {"Málaga", 4.7}, {"Barcelona", 4.3}, {"Lisbon", 1.9}} }. Usa la función std::ranges::sort para ordenar las ventas de menor a mayor e identificar la sucursal con la venta más baja y la más alta.

## Solución

- `proposed_solution.cpp`: solución **no oficial**. El campus no da solución; está escrita siguiendo el estilo de las del profesor.

## Ejemplo de ejecución

Salida de `proposed_solution.cpp`:

```text
    Lisbon: 1.9 M€
    Madrid: 3.1 M€
 Barcelona: 4.3 M€
    London: 4.5 M€
    Málaga: 4.7 M€
     Paris: 4.8 M€
Venta más baja: Lisbon (1.9 M€)
Venta más alta: Paris (4.8 M€)
```
