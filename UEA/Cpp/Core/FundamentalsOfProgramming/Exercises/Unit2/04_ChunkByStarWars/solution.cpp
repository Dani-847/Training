#include <algorithm> // contiene algoritmos de búsqueda, partición, ordenación, etc.
#include <array>
#include <print>
#include <ranges> // contiene vistas estándar como chunk_by, take, etc.
#include <string>

using namespace std;

// agregado para representar personajes de Star Wars:
struct Character {
   string name; // nombre del personaje
   string type; // tipo de personaje (Dark Side, Yedi, etc)
};

// predicado binario que se evalúa como 'true' cuando dos personajes son del mismo tipo;
// 'false' en caso contrario:
auto equal_type(Character& c1, Character& c2) -> bool
{
   return c1.type == c2.type;
}

auto main () -> int
{
   auto characters = array<Character, 9>{{
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

   // ordenamos el array de personajes por orden alfabético de tipo:
   ranges::sort(characters, {}, &Character::type);

   // bucle que itera los subgrupos de personajes de idéntico tipo:
   for (auto subgroup : characters | views::chunk_by(equal_type)) {
      // para cada subgrupo, imprimimos el número de personajes y su tipo (obtenido este último
      // a través del primer representante, con índice de acceso 0):
      println(
          "Hay {} personajes de tipo {}:",
          subgroup.size(),
          subgroup[0].type
      );

      // ordenamos el subgrupo por orden alfabético de nombre de personaje:
      ranges::sort(subgroup, {}, &Character::name);

      // imprimimos los nombres de los personajes del subgrupo:
      for (Character& c : subgroup) {
          println(" * {}", c.name);
      }
   }
}
