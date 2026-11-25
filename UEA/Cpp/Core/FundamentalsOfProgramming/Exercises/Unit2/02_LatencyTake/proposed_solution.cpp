// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL). La oficial valida N con un if; el enunciado pide un bucle while.
// Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <array>
#include <iostream>
#include <print>
#include <ranges> // contiene vistas std::views
#include <string>

using namespace std;

auto main() -> int
{
   auto latencies = array<int, 10>{}; // 10 enteros inicializados a 0

   auto str = string{};
   for (auto i = 0uz; i < latencies.size(); ++i) {
      print("Introduce la latencia #{} (milisegundos): ", i + 1);
      getline(cin, str);
      latencies[i] = stoi(str);
   }

   print("Introduce el número de latencias a promediar (1-10): ");
   getline(cin, str);
   auto n = stoi(str);

   // validación con while: se repite la pregunta MIENTRAS el valor no sea válido
   while (n < 1 or n > 10) {
      print("Valor fuera de rango. Introduce un número entre 1 y 10: ");
      getline(cin, str);
      n = stoi(str);
   }

   // views::take(n) = vista sobre los n primeros elementos (no copia nada)
   auto sum = double{0.0};
   for (int lat : latencies | views::take(n)) {
      sum += lat;
   }
   println("Media de las {} primeras latencias: {:.2f} ms", n, sum / n);
}
