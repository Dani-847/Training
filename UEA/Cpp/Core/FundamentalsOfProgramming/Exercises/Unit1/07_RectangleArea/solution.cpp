#include <cstdlib>
#include <print>

using namespace std;

struct Rectangle { // agregado de dos doubles para representar rectángulos
   double width;  // base
   double height; // altura

   auto area() -> double // función miembro que calcula el área de un rectángulo
   {
      return width * height;
   }
};

auto main() -> int
{
   // rectángulo 1:
   auto r_1 = Rectangle{8.5, 5.6};

   // rectángulo 2:
   auto r_2 = Rectangle{11.2, 4.3};

   // invocamos la función miembro area() a través del objeto correspondiente:
   auto area_1 = r_1.area();
   auto area_2 = r_2.area();

   // imprimimos las áreas:
   println("Area 1: {} | Area 2: {}", area_1, area_2);
}
