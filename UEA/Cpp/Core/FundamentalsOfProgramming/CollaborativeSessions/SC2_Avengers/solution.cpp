#include <algorithm>
#include <array>
#include <print>
#include <ranges>
#include <string>

using namespace std;

struct Character {
   string name;
   string type;
   bool destroyed;
};

auto num_avengers(array<Character, 12>& a) -> int
{
   auto counter = int{0};
   for (auto i = 0uz; i < a.size(); ++i){
      if(a[i].type == "Avenger"){
         ++counter;
      }
   }
   return counter;
}

auto num_destroyed(array<Character, 12>& a) -> int
{
   auto counter = int{0};
   for (Character& c : a) {
      if (c.destroyed){
         ++counter;
      }
   }
   return counter;
}

auto main () -> int
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

   auto num_1 = num_avengers(characters);
   println("Number of Avengers: {} ({:.1f}%)", num_1, 100.0*num_1/characters.size());
   auto num_2 = num_destroyed(characters);
   println("Number of characters destroyed by Thanos: {} ({:.1f}%)", num_2, 100.0*num_2/characters.size());

   // ----------------------------------------------------------------------------------------------

   // ordenamos todos los personajes por tipo:
   ranges::sort(characters, {}, &Character::type);

   // expresión lambda:
   auto equal_type = [](Character& c1, Character& c2) { return c1.type == c2.type; };

   for (auto group : characters | views::chunk_by(equal_type)) {
      // ordenamos los personajes del grupo por nombre:
      ranges::sort(group, {}, &Character::name);
      
      println("Tipo {}:", group[0].type);
      
      for (Character& c : group){
         println("  - {}", c.name);
      }
   }
}

