#include <array>
#include <print>
#include <ranges>

using namespace std;

// función que determina si dos enteros son iguales:
auto equal_integers(int i, int j) -> bool
{
   return i == j;
}

auto main() -> int
{
   // array de números ordenados, de forma que elementos idénticos se encuentran ya agrupados:
   auto nums = array{1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5};

   // bucle for-each que itera cada subgrupo de elementos idénticos del array:
   for (auto grp : nums | views::chunk_by(equal_integers)){
      // imprimimos el número de elementos en el subgrupo y la entrada de índice 0 como
      // representante:
      println("hay {} elementos iguales al {}", grp.size(), grp[0]);
   }
}
