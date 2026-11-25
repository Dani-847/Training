// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL). Versión completa (A + B) con fichero JSON Lines y nlohmann-json.
// Está escrita siguiendo el estilo de las soluciones del profesor (ver CE5).
// ============================================================================

#include <algorithm>
#include <fstream>   // flujos de entrada/salida a ficheros
#include <print>
#include <ranges>
#include <string>
#include <vector>

#include <nlohmann/json.hpp> // biblioteca de serialización/deserialización JSON

using namespace std;

// agregado proporcionado por el enunciado (nombres = claves del fichero JSON):
struct Mission {
   string district;
   string type;
};

// indica a nlohmann-json cómo convertir un objeto JSON en un Mission:
NLOHMANN_DEFINE_TYPE_NON_INTRUSIVE(Mission, district, type)

// predicado binario: ¿pertenecen dos misiones al mismo distrito?
auto equal_district(Mission& m1, Mission& m2) -> bool
{
   return m1.district == m2.district;
}

// predicado binario: ¿son dos misiones del mismo tipo?
auto equal_type(Mission& m1, Mission& m2) -> bool
{
   return m1.type == m2.type;
}

auto main() -> int
{
   // ------------------------------------------------------------------- [A]
   // abrimos el fichero en modo binario, como pide el enunciado
   // (ajusta la ruta según desde dónde ejecutes el programa):
   auto ifs = ifstream{"peter_parker.jsonl", ios::binary};
   if (!ifs) {
      println("No se ha podido abrir el fichero peter_parker.jsonl");
      return 1;
   }

   auto missions = vector<Mission>{}; // vector inicialmente vacío
   auto line = string{""};
   while (getline(ifs, line)) {
      // cada línea es un objeto JSON -> lo convertimos en Mission:
      missions.push_back(nlohmann::json::parse(line).get<Mission>());
   }
   println("Misiones leídas: {}", missions.size());

   // ------------------------------------------------------------------- [B]
   // 1) ordenamos TODAS las misiones por distrito (orden alfabético):
   ranges::sort(missions, {}, &Mission::district);

   // 2) agrupamos por distrito:
   for (auto district_group : missions | views::chunk_by(equal_district)) {
      println("{}:", district_group[0].district);

      // 3) dentro del distrito, ordenamos por tipo de misión:
      ranges::sort(district_group, {}, &Mission::type);

      // 4) agrupamos por tipo y contamos (chunk_by dentro de chunk_by):
      for (auto type_group : district_group | views::chunk_by(equal_type)) {
         println("   - {}: {} misiones", type_group[0].type, type_group.size());
      }
   }
}
