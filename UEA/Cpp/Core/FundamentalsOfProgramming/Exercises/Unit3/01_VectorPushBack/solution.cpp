#include <algorithm> // contiene std::ranges::sort
#include <iostream>  // contiene el flujo de entrada estándar std::cin
#include <print>
#include <string>    // cadenas de caracteres
#include <vector>    // contenedor secuencial de elementos contiguos en memoria

using namespace std;

auto message() -> bool
{
   print("Introduce un nombre: ");
   return true;
}

auto main() -> int
{
   auto names = vector<string>{}; // vector de strings inicialmente vacío

   auto str = string{}; // string auxiliar inicialmente vacío
   while (message() and getline(cin, str)) {
      // enviamos al fondo del vector el nombre introducido por el usuario:
      names.push_back(str);
   }

   // una vez finalizada la inserción de nombres, ordenamos el vector por orden
   // lexicográfico:
   ranges::sort(names);

   // imprimimos los nombres contenidos en el vector:
   for (string& nm : names) {
      println("{}", nm);
   }
}
