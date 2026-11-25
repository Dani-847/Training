#include <iostream> // incluye std::cin
#include <print>
#include <string>

using namespace std;

auto is_vowel(char c) -> bool // ¿es el carácter c una vocal? (verdadero/falso)
{ 
   return c == 'a' or c == 'e' or c == 'i' or c == 'o' or c == 'u' or
          c == 'A' or c == 'E' or c == 'I' or c == 'O' or c == 'U';
}

auto main() -> int
{
   print("Insert a string: ");
   auto str = string{""}; // inicialmente vacío
   getline(cin, str);

   auto num_vowels = int{0}; // contador de vocales
   // recorremos cada carácter del string str e incrementamos el contador en una unidad cada
   // vez que descubramos una vocal:
   for (auto i = 0uz; i < str.size(); ++i) {
      if (is_vowel(str[i])) {
         ++num_vowels;
      }
   }

   // imprimimos el resultado en la terminal:
   println("Number of vowels is: {}", num_vowels);
}
