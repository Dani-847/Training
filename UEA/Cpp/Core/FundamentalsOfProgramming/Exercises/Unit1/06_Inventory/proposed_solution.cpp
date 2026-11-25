// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL). Ajustada al enunciado: sin ordenar y con el mensaje "Espacio libre".
// Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <array>
#include <iostream>
#include <print>
#include <string>

using namespace std;

auto main() -> int
{
   auto inventory = array<string, 5>{}; // 5 strings inicialmente vacíos

   println("Introduce 5 objetos (pulsa Enter sin escribir nada para dejar el espacio libre):");
   for (auto i = 0uz; i < inventory.size(); ++i) {
      print("Espacio #{}: ", i + 1);
      getline(cin, inventory[i]);
   }

   println("--- Inventario ---");
   for (auto i = 0uz; i < inventory.size(); ++i) {
      if (inventory[i].empty()) { // string vacío
         println("#{}: Espacio libre", i + 1);
      }
      else {
         println("#{}: {}", i + 1, inventory[i]);
      }
   }
}
