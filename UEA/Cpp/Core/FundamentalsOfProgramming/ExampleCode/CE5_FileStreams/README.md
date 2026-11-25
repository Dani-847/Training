# CE5 · Flujos a ficheros 2 [Dificultad: 4/5]

> ⛔ **NO ENTRA EN EL EXAMEN.** El examen llega hasta los vectores, sin incluirlos. Puedes saltarte este ejercicio o hacerlo solo como ampliación.
> Aun así, es útil como **anexo**: muestra cómo se lee un fichero JSON Lines (`.jsonl`) con la biblioteca nlohmann. Está explicado al final de `01_apuntes_explicados.pdf`.

**Conceptos:** Nivel 13 (ifstream, JSON Lines, nlohmann::json) · Nivel 11 (vector, push_back) · Nivel 9 (sort con proyección) · Nivel 10 (chunk_by)

## Enunciado

Objetivo: Consideremos el fichero JSON Lines llamado sample_clothing.jsonl, que contiene información sobre prendas de ropa en una tienda online. Cada línea proporciona el nombre de la marca de ropa (brand), el tipo de prenda (garment) y el precio (price) de un ítem. Almacenaremos la información del fichero en un vector de objetos Item para, mediante ordenaciones std::ranges::sort y vistas std::views::chunk_by, imprimir por terminal, para cada marca distinta: su nombre, el número de ítems en venta y el coste total de dicho número de ítems (en dólares).

Nota: Emplearemos la biblioteca Nlohmann's JSON for Modern C++Links to an external site. para la serialización/deserialización de objetos JSON. Comando de instalación en MSYS2: 

```
pacman -S mingw-w64-ucrt-x86_64-nlohmann-json
```

## Solución

- `solution.cpp`: solución oficial del profesor.
- `sample_clothing.jsonl`: fichero de datos.
- **Ojo con la ruta:** el código abre `../../sample_clothing.jsonl`, relativa a la carpeta `build/debug` que genera CMake. Si lo ejecutas desde otra carpeta, ajusta la ruta.
- Necesita la biblioteca nlohmann-json (comando de instalación en el enunciado).

## Ejemplo de ejecución

Salida esperada de `solution.cpp` con el `sample_clothing.jsonl` incluido (compilada y ejecutada con nlohmann-json 3.11.3):

```text
Marca: Aether & Thread ---------------------
  - número de ítems: 3
  - coste total: 158.5 $
Marca: BluePeak Apparel ---------------------
  - número de ítems: 5
  - coste total: 312.1 $
Marca: NovaWear ---------------------
  - número de ítems: 2
  - coste total: 124.9 $
```
