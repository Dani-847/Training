# U2-4 · Vista chunk_by para personajes de Star Wars

**Conceptos:** Nivel 7 · Nivel 9 (sort con proyección) · Nivel 10 (chunk_by)

## Enunciado

Partiendo de un std::array con 9 personajes Character de tres tipos diferentes ("Yedi", "Dark Side", "Wookiee"), codifica un programa que:

Ordene el array por tipo.
Use la vista std::views::chunk_byLinks to an external site. para delimitar los subgrupos de personajes consecutivos que comparten el mismo tipo.
Imprima cada grupo en la terminal indicando el tipo, los nombres y el total de personajes por grupo.
```cpp
    std::array<Character, 9>{{
        {"Luke", "Yedi"},
        {"Vader", "Dark Side"},
        {"Leia", "Yedi"},
        {"Chewbacca", "Wookiee"},
        {"Yoda", "Yedi"},
        {"Palpatine", "Dark Side"},
        {"Obi-Wan", "Yedi"},
        {"Darth Maul", "Dark Side"},
        {"Tarfful", "Wookiee"}
    }};
```

## Solución

- `solution.cpp`: solución oficial del profesor.

## Ejemplo de ejecución

Salida de `solution.cpp`:

```text
Hay 3 personajes de tipo Dark Side:
 * Darth Maul
 * Palpatine
 * Vader
Hay 2 personajes de tipo Wookiee:
 * Chewbacca
 * Tarfful
Hay 4 personajes de tipo Yedi:
 * Leia
 * Luke
 * Obi-Wan
 * Yoda
```
