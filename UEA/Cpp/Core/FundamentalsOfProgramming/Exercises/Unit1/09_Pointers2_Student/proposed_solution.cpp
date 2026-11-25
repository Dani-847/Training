// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <print>
#include <string>

using namespace std;

struct Student {
   string name;
   double grade_1;
   double grade_2;
   double grade_3;

   auto average() -> double // media aritmética de las tres notas
   {
      return (grade_1 + grade_2 + grade_3) / 3.0;
   }
};

auto main() -> int
{
   auto s = Student{"James Kerry", 6.0, 8.0, 9.5};
   Student* p = &s; // puntero que apunta a s

   // con un puntero a struct se accede a los miembros con -> (equivale a (*p).name):
   println("{}: media = {:.2f}", p->name, p->average());

   // modificamos la primera nota a través del puntero:
   p->grade_1 = 7.5;

   println("{}: media = {:.2f}", p->name, p->average());
}
