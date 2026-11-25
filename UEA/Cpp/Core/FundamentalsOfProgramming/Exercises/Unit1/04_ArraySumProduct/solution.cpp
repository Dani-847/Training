#include <array>
#include <print>

using namespace std;

auto main() -> int
{
   // creamos un array de cinco enteros:
   auto nums = array{1, 2, 3, 4, 5}; // equivalentemente: array<int, 5>{1, 2, 3, 4, 5}

   // inicializamos la suma y el producto:
   auto sum = int{0};
   auto prod = int{1};

   // recorremos el array con un bucle for:
   for (auto i = 0uz; i < nums.size(); ++i) {
      sum += nums[i];
      prod *= nums[i];
   }

   // imprimimos los valores finales de la suma y el producto:
   println("suma = {} | producto = {}", sum, prod); // output: suma = 15 | producto = 120
}
