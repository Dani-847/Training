# U2-1 · Registro y análisis de importes

**Conceptos:** Nivel 3 (leer hasta EOF, try/catch) · Nivel 4 (while, continue) · patrón media

## Enunciado

Permite que el usuario introduzca tantos importes reales como desee por la terminal, hasta que inserte la combinación especial de caracteres EOF (CTRL+Z en MS Windows, CTRL+D en UNIX/Linux/macOS). Calcula la suma y media de valores.

## Solución

- `solution.cpp`: solución oficial del profesor.

## Ejemplo de ejecución

Datos escritos por el usuario: "12.5", "7", "abc", "20" y después CTRL+Z (en Windows) o CTRL+D (en Linux/Mac).

Salida de `solution.cpp`:

```text
Introduce una cantidad (€; EOF para terminar): 12.5
Introduce una nueva cantidad (€; EOF para terminar): 7
Introduce una nueva cantidad (€; EOF para terminar): abc
Valor erróneo. Prueba otra vez (€; EOF para terminar): 20
Introduce una nueva cantidad (€; EOF para terminar): ^Z   ← CTRL+Z (fin de entrada, EOF)
Total: 39.5 € | Media: 13.2 €
```
