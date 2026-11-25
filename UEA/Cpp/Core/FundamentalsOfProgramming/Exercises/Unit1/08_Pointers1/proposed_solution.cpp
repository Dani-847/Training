// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <print>

using namespace std;

auto main() -> int
{
   auto x = int{1};  // variable entera
   int* p = &x;      // puntero a int que guarda la dirección de x

   // std::println no imprime directamente un int*: lo convertimos a void* para
   // que muestre la dirección de memoria:
   println("dirección de x:       {}", static_cast<void*>(&x));
   println("valor del puntero p:  {}", static_cast<void*>(p)); // misma dirección
   println("valor apuntado (*p):  {}", *p);                    // 1

   // modificamos x a través del puntero:
   *p = 42;

   println("nuevo valor de x:     {}", x);   // 42: x ha cambiado
   println("nuevo valor de *p:    {}", *p);  // 42
}
