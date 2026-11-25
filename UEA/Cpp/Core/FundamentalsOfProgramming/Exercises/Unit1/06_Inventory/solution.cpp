#include <algorithm>
#include <array>
#include <iostream>
#include <print>
#include <string>

using namespace std;

auto main() -> int
{
   auto inventory = std::array<std::string, 5>{}; // 5 strings inicialmente vacíos

   println("Enter 5 items for your inventory:");

   // bucle for-each que introduce los ítems en el array
   for (auto i = 0uz; i < inventory.size(); ++i) {
      print("Item #{}: ", i + 1);
      getline(cin, inventory[i]);
   }

   // ordenamos los ítems por orden lexicográfico:
   ranges::sort(inventory);

   // imprimimos en la terminal los ítems registrados por el usuario, a modo de comprobación:
   for (auto i = 0uz; i < inventory.size(); ++i) {
      if (inventory[i].empty()) { // string vacío
         println("Espacio en blanco");
      }
      else {
         println("{}", inventory[i]);
      }
   }
}
