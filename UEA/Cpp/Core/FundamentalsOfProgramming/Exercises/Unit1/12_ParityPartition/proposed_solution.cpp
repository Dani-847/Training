// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <algorithm>
#include <array>
#include <print>

using namespace std;

// predicado: ¿es n par?
auto is_even(int n) -> bool
{
   return n % 2 == 0;
}

auto main() -> int
{
   auto nums = array{1, 2, 3, 4, 5, 6, 7};

   // partition coloca delante los elementos que cumplen el predicado (pares).
   // El orden dentro de cada grupo no está garantizado.
   ranges::partition(nums, is_even);

   for (int n : nums) {
      print("{} ", n);
   }
   println("");
}
