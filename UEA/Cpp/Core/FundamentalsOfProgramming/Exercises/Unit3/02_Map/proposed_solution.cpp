// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <map>
#include <print>
#include <string>

using namespace std;

auto main() -> int
{
   // agenda: clave = nombre (string), valor = teléfono (int)
   auto phone_book = map<string, int>{};

   // m[clave] = valor -> inserta la pareja (o sobrescribe si ya existía):
   phone_book["Pedro"] = 51212;
   phone_book["Ana"] = 61251;
   phone_book["Marcos"] = 41257;
   phone_book["Ana"] = 69999; // la clave ya existe: se actualiza su valor

   // el map se recorre SIEMPRE ordenado por clave (Ana, Marcos, Pedro).
   // [name, phone] = structured bindings: separa la pareja clave-valor
   for (auto const& [name, phone] : phone_book) {
      println("name: {:>7} | phone: {}", name, phone);
   }

   // ¿existe una clave? (contains)
   if (phone_book.contains("Luis")) {
      println("Luis: {}", phone_book["Luis"]);
   }
   else {
      println("Luis no está en la agenda");
   }

   println("Número de contactos: {}", phone_book.size());
}
