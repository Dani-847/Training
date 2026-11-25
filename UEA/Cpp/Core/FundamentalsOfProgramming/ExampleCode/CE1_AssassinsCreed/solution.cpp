#include <algorithm> // contiene algoritmos de búsqueda, partición, ordenación, etc.
#include <array>
#include <print>
#include <ranges> // contiene vistas estándar como chunk_by, take, etc.
#include <string>

using namespace std;

// nuevo tipo definido por el programador (agregado con la información acerca de un target):
struct Target {
   string type;
   bool achieved;
   int level;
   array<double, 3> position;
};

// paso de argumentos por referencia (operador &) para evitar copias innecesarias:
auto equal_type(Target& t1, Target& t2) -> bool
{
   return t1.type == t2.type;
}

// ts es un alias para el array pasado como argumento por referencia:
auto print_hud(array<Target, 9>& ts) -> void
{
   // bucle for basado en rango que visita cada subgrupo de targets
   // con el mismo tipo de objetivo:
   for (auto target_grp : ts | views::chunk_by(equal_type)) {
      // contamos los targets ya completados del subgrupo:
      auto counter = int{0};
      for (Target& t : target_grp) {
         if (t.achieved) {
            ++counter;
         }
      }
      // imprimimos la información relevante del subgrupo:
      println(
         "{} : {}/{}",
         target_grp[0].type, // primer representante del grupo empleado para imprimir el tipo
         counter, // número de targets completados del subgrupo
         target_grp.size() // número total de targets del subgrupo
      );
   }
}

auto main () -> int
{ 
   // ---------------------------------------------------------------------
   // información acerca del estado de la partida contenida en un array:
   auto targets = array<Target, 9>{{
      {"soldier", true, 2, {1.0, 3.5, 0.0}},
      {"leader", false, 5, {2.1, 4.0, 1.2}},
      {"treasure", true, 1, {0.5, 2.2, 0.0}},
      {"soldier", false, 3, {1.5, 3.0, 0.5}},
      {"secret weapon", false, 4, {3.0, 1.0, 2.0}},
      {"leader", true, 5, {2.0, 4.5, 1.0}},
      {"soldier", true, 2, {1.2, 3.3, 0.0}},
      {"secret weapon", true, 4, {3.1, 1.2, 2.1}},
      {"soldier", false, 3, {1.6, 3.1, 0.4}}
   }};

   // --------------------------------------------------------------------
   // ordenamos el array de targets por orden alfabético de tipos:
   ranges::sort(targets, {}, &Target::type);

  // ---------------------------------------------------------------------
  // imprimimos el HUD en la terminal:
  print_hud(targets);

}