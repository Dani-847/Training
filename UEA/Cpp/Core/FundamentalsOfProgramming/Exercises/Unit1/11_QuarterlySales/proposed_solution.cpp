// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <algorithm>
#include <array>
#include <print>
#include <string>

using namespace std;

struct Branch {
   string city;  // sucursal
   double sales; // ventas trimestrales (millones de euros)
};

auto main() -> int
{
   auto branches = array<Branch, 6>{ {{"London", 4.5}, {"Paris", 4.8}, {"Madrid", 3.1},
                                      {"Málaga", 4.7}, {"Barcelona", 4.3}, {"Lisbon", 1.9}} };

   // ordenamos de menor a mayor por el miembro sales (proyección &Branch::sales):
   ranges::sort(branches, {}, &Branch::sales);

   for (Branch& b : branches) {
      println("{:>10}: {:.1f} M€", b.city, b.sales);
   }

   // tras ordenar, la venta más baja está al principio y la más alta al final:
   auto last = branches.size() - 1;
   println("Venta más baja: {} ({:.1f} M€)", branches[0].city, branches[0].sales);
   println("Venta más alta: {} ({:.1f} M€)", branches[last].city, branches[last].sales);
}
