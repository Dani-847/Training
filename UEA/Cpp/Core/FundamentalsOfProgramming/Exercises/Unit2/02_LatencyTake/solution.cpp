#include <array>
#include <iostream>
#include <print>
#include <ranges> // contiene vistas std::views
#include <string>

using namespace std;

auto main() -> int
{
   auto latencies = array<int, 10>{}; // contiene 10 enteros inicializados a 0 por defecto

   auto str = string{};
   for (auto i = 0uz; i < latencies.size(); ++i) {
      print("Introduce la latencia #{} (milisegundos): ", i + 1);
      getline(cin, str);
      latencies[i] = stoi(str);
   }

   print("Introduce el número de latencias a promediar: ");
   getline(cin, str);
   auto n = stoi(str);
   if (n >= 1 and n <= 10) {
      auto sum = double{0.0};
      for (int lat : latencies | views::take(n)) {
         sum += lat;
      }
      println("Media de las {} primeras latencias: {:.2f}", n, sum/n);
   }
   else {
      println("Lo sentimos, no es posible calcular una media de latencias con ese valor");
   }
}
