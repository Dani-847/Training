// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <array>
#include <iostream>
#include <print>
#include <string>

using namespace std;

auto main() -> int
{
   auto grades = array<double, 5>{}; // 5 notas inicializadas a 0.0

   // leemos las 5 notas desde la terminal:
   auto str = string{""};
   for (auto i = 0uz; i < grades.size(); ++i) {
      print("Introduce la nota #{}: ", i + 1);
      getline(cin, str);
      grades[i] = stod(str); // convertimos el texto a double
   }

   // acumulamos la suma de las notas:
   auto sum = double{0.0};
   for (double g : grades) {
      sum += g;
   }
   auto average = sum / grades.size();

   println("Media: {:.2f}", average);
   if (average >= 5.0) {
      println("APRUEBA");
   }
   else {
      println("SUSPENDE");
   }
}
