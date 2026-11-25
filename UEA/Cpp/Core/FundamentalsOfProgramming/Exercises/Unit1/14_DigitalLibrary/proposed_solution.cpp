// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <algorithm>
#include <array>
#include <print>
#include <string>

using namespace std;

// predicado: ¿empieza el título por vocal (mayúscula)?
auto starts_with_vowel(string const& title) -> bool
{
   if (title.empty()) {
      return false;
   }
   auto c = title[0];
   return c == 'A' or c == 'E' or c == 'I' or c == 'O' or c == 'U';
}

auto main() -> int
{
   auto books = array<string, 8>{
      "Les Misérables",
      "Anna Karenina",
      "Oliver Twist",
      "A Tale of Two Cities",
      "Inferno",
      "Don Quixote",
      "Ulysses",
      "The Count of Monte Cristo"
   };

   // delante: títulos que empiezan por vocal; cut = inicio del resto:
   auto others = ranges::partition(books, starts_with_vowel);
   auto cut = others.begin();

   // ordenamos alfabéticamente sólo los que empiezan por vocal:
   ranges::sort(books.begin(), cut);

   println("Empiezan por vocal:");
   for (auto it = books.begin(); it != cut; ++it) {
      println("  {}", *it);
   }

   println("Empiezan por consonante:");
   for (auto it = cut; it != books.end(); ++it) {
      println("  {}", *it);
   }
}
