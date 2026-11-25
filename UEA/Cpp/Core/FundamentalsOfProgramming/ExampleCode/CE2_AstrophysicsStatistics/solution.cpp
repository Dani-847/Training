#include <array>
#include <iostream>
#include <limits>
#include <print>
#include <string>

using namespace std;

struct Stats {
   double average; // velocidad media de los cometas
   double min;     // velocidad mínima en el array
   double max;     // velocidad máxima en el array
   int greater_than_threshold; // número de cometas con velocidad superior a la umbral
   bool valid;     // ¿hemos podido realizar el cálculo?
   string message; // mensaje acerca del análisis
};

auto analyze_speeds(array<double, 5>& speeds, double threshold) -> Stats
{
   // validación de inputs #1:
   for (double& s : speeds) {
      if (s < 0.0) {
         return Stats{0.0, 0.0, 0.0, 0, false, "speeds cannot be negative"};
      }
   }

   // validación de inputs #2:
   if (threshold < 0.0) {
      return Stats{0.0, 0.0, 0.0, 0, false, "invalid threshold (it cannot be negative)"};
   }

   // variables auxiliaRes:
   auto sum = double{0.0};
   auto counter = int{0};
   auto min_speed = numeric_limits<double>::max();
   auto max_speed = 0.0;

   for (double& s : speeds) {
      sum += s;
      if (s < min_speed) {
         min_speed = s;
      }
      if (s > max_speed) {
         max_speed = s;
      }
      if (s >= threshold) {
         ++counter;
      }
   }

   return Stats{
      sum/speeds.size(),
      min_speed,
      max_speed,
      counter,
      true,
      "evaluation successfully completed"
   };
}

auto main() -> int
{
   // velocidades realistas de cometas en km/s (rango típico ~10-70 km/s):
   auto speeds = array<double, 5>{12.3, 55.0, 35.2, 18.7, 42.8};

   // solicitamos la velocidad umbral al usuario:
   print("Enter the threshold speed (km/s): ");
   auto str = string{""};
   getline(cin, str);
   auto threshold = stod(str);

   auto stats = analyze_speeds(speeds, threshold);
   if (stats.valid) {
      println(
         "average speed: {} km/s, min: {} km/s, max: {} km/s, num comets: {}",
         stats.average, stats.min, stats.max, stats.greater_than_threshold
      );
   }
   else {
      println("{}", stats.message);
   }
}
