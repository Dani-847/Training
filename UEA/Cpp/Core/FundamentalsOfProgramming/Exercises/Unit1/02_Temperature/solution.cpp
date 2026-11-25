#include <cstdlib>
#include <iostream>
#include <print>
#include <string>

using namespace std;

auto from_celsius_to_fahrenheit(double cs) -> double
{
   return (9*cs)/5 + 32.0;
}

auto main() -> int
{
   print("Introduce una temperatura en Celsius: ");

   auto data = string{""}; // inicialmente vacío
   getline(cin, data);

   try {
      // convertimos el string a double para obtener el valor numérico de la temperatura:
      auto temp_celsius = stod(data);
      auto temp_fahrenheit = from_celsius_to_fahrenheit(temp_celsius);
      println("Temperatura en Fahrenheit: {:.2f} °F", temp_fahrenheit);
   }
   catch (exception& e) {
      println("wrong data: {}", e.what());
   }

   println("Bye!");
}
