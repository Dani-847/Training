// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL). Completa el apartado [D]: indica si cada personaje sobrevivió a Thanos.
// Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <algorithm>
#include <array>
#include <print>
#include <ranges>
#include <string>

using namespace std;

struct Character {
   string name;
   string type;
   bool destroyed; // true si Thanos lo destruyó
};

// [B] número de personajes de tipo "Avenger"
auto num_avengers(array<Character, 12>& a) -> int
{
   auto counter = int{0};
   for (Character& c : a) {
      if (c.type == "Avenger") {
         ++counter;
      }
   }
   return counter;
}

// [C] número de personajes destruidos por Thanos
auto num_destroyed(array<Character, 12>& a) -> int
{
   auto counter = int{0};
   for (Character& c : a) {
      if (c.destroyed) {
         ++counter;
      }
   }
   return counter;
}

// predicado binario para chunk_by: ¿son del mismo tipo?
auto equal_type(Character& c1, Character& c2) -> bool
{
   return c1.type == c2.type;
}

auto main() -> int
{
   auto characters = array<Character, 12>{{
      {"Iron Man",        "Avenger", false},
      {"Loki",            "Villain", true },
      {"Captain America", "Avenger", false},
      {"Ultron",          "Villain", false},
      {"Thor",            "Avenger", false},
      {"Black Widow",     "Avenger", false},
      {"Spider-Man",      "Avenger", true },
      {"Wolverine",       "Mutant",  false},
      {"Wasp",            "Avenger", true },
      {"Doctor Strange",  "Avenger", true },
      {"Deadpool",        "Mutant",  false},
      {"Captain Marvel",  "Avenger", false}
   }};

   // [B] y [C]: el 100.0 fuerza la división con decimales
   auto n_avengers = num_avengers(characters);
   println("Avengers: {} ({:.1f}%)", n_avengers, 100.0 * n_avengers / characters.size());
   auto n_destroyed = num_destroyed(characters);
   println("Destruidos por Thanos: {} ({:.1f}%)", n_destroyed, 100.0 * n_destroyed / characters.size());

   // [D] 1) ordenar por tipo para que chunk_by forme grupos completos
   ranges::sort(characters, {}, &Character::type);

   // 2) recorrer cada grupo de personajes del mismo tipo
   for (auto group : characters | views::chunk_by(equal_type)) {
      // 3) ordenar el grupo alfabéticamente por nombre
      ranges::sort(group, {}, &Character::name);

      println("Tipo {} ({} personajes):", group[0].type, group.size());
      for (Character& c : group) {
         if (c.destroyed) {
            println("  - {}: destruido por Thanos", c.name);
         }
         else {
            println("  - {}: sobrevivió", c.name);
         }
      }
   }
}
