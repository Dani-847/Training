#include <iostream> // contiene std::cin (flujo de entrada estándar)
#include <print>
#include <string>

using namespace std;

auto main() -> int
{   
   auto str = string{""}; // string inicialmente vacío

   auto total = double{0.0}; // suma de cantidades
   auto counter = int{0}; // número de cantidades introducidas

   print("Introduce una cantidad (€; EOF para terminar): ");
   while (getline(cin, str)) { // el bucle finaliza con EOF
      try {
         total += stod(str); // equivalente a total = total + stod(str);
      }
      catch (...) {
          print("Valor erróneo. Prueba otra vez (€; EOF para terminar): ");
          continue;
      }
      ++counter;
      print("Introduce una nueva cantidad (€; EOF para terminar): ");
   }

   println("Total: {:.1f} € | Media: {:.1f} €", total, total/counter);
}
