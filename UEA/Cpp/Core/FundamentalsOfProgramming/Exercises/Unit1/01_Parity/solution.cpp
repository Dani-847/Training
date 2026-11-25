#include <iostream> // contiene el objeto global std::cin (flujo de entrada estándar)
#include <print>
#include <string>

using namespace std;

auto main() -> int
{
   // imprimimos mensaje de bienvenida:
   print("Inserta un entero: ");

   auto data = string{""}; // string inicialmente vacío

   // aguardamos a que el usuario introduzca una cadena de caracteres por la terminal,
   // que acumulamos en el string data (prescindiendo del salto de línea final):
   getline(cin, data);

   try { 
      // tratamos de convertir el string a entero (esta operación puede lanzar excepciones
      // de tipo std::invalid_argument o std::out_of_range):
      auto n = stoi(data);
      // imprimimos el valor, así como si es entero o no:
      println("es {} par?: {}", n, n%2 == 0);
   }
   catch (exception& e) { // si stoi lanza excepción, la capturamos por referencia
      println("error: {}", e.what());
   }
}
