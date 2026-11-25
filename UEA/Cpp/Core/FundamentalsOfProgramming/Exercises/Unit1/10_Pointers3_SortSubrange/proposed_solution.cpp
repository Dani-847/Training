// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <algorithm>
#include <array>
#include <print>
#include <string>

using namespace std;

auto main() -> int
{
   auto names = array<string, 6>{"Zoe", "Mario", "Ana", "Luis", "Carla", "Bea"};

   // punteros que delimitan el subrango [first, last) = 3 primeros elementos:
   string* first = &names[0];
   string* last = first + 3; // aritmética de punteros: apunta a names[3] (no incluido)

   // ordenamos alfabéticamente sólo ese subrango:
   ranges::sort(first, last);

   for (string& nm : names) {
      println("{}", nm); // Ana Mario Zoe | Luis Carla Bea (sin cambios)
   }
}
