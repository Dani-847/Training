// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <algorithm>
#include <array>
#include <print>
#include <string>

using namespace std;

// ¿contiene el string al menos un dígito?
auto has_digit(string const& s) -> bool
{
   for (char c : s) {
      if (c >= '0' and c <= '9') {
         return true;
      }
   }
   return false;
}

// predicado: contraseña segura = longitud >= 8 y al menos un dígito
auto is_secure(string const& s) -> bool
{
   return s.size() >= 8 and has_digit(s);
}

auto main() -> int
{
   auto passwords = array<string, 6>{"admin", "hunter2", "access87", "1234578", "cyberSec99", "root"};

   // partition deja delante las seguras y devuelve el subrango de las inseguras;
   // su begin() es el punto de corte entre ambos grupos:
   auto insecure = ranges::partition(passwords, is_secure);
   auto cut = insecure.begin();

   // ordenamos sólo el subrango de las seguras: [begin, cut)
   ranges::sort(passwords.begin(), cut);

   println("Seguras:");
   for (auto it = passwords.begin(); it != cut; ++it) {
      println("  {}", *it); // *it = elemento al que apunta el iterador
   }

   println("Inseguras:");
   for (auto it = cut; it != passwords.end(); ++it) {
      println("  {}", *it);
   }
}
