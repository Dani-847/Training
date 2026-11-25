# CE2 · Estadística astrofísica [Dificultad: 3/5]

> ⭐ **MUY IMPORTANTE PARA EL EXAMEN.** CE1 y CE2 son de los ejercicios principales de cara al examen.

**Conceptos:** Nivel 7 (struct como valor de retorno) · Nivel 5 (función con varios return y validación) · patrones mín/máx/contador/media · `numeric_limits` (opcional)

## Enunciado

Objetivo: Implementaremos una función que reciba un std::array<double, 5> con las velocidades (en km/s) de cinco cometas y un valor umbral. La función calculará entonces:

Velocidad media.
Velocidad mínima y máxima en el array.
Cantidad de cometas cuya velocidad supera el umbral.
Si el array contuviese alguna velocidad negativa o el umbral fuese negativo, la función deberá devolver un mensaje de error.

Como velocidades realistas para los cometas (en km/s), pueden tomarse:

std::array<double, 5>{12.3, 55.0, 35.2, 18.7, 42.8};

## Solución

- `solution.cpp`: solución oficial del profesor. Devuelve un `struct Stats` con todos los resultados y un `bool valid` para el caso de error.

## Ejemplo de ejecución

Datos escritos por el usuario: "30".

Salida de `solution.cpp`:

```text
Enter the threshold speed (km/s): 30
average speed: 32.8 km/s, min: 12.3 km/s, max: 55 km/s, num comets: 3
```
