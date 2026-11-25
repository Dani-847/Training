// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL). Versión SIN ficheros ni JSON: los 100 datos van en un std::array
// para practicar el apartado [B], que es el que entra en el examen.
// Está escrita siguiendo el estilo de las soluciones del profesor (ver CE5).
// ============================================================================

#include <algorithm>
#include <array>
#include <print>
#include <ranges>
#include <string>

using namespace std;

struct Mission {
   string district;
   string type;
};

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
   // mismos 100 datos que peter_parker.jsonl, escritos directamente en el código:
   auto missions = array<Mission, 100>{{
      {"Queens", "Kraven the Hunter"},
      {"Upper West Side", "Kraven the Hunter"},
      {"Queens", "Venom symbiote"},
      {"Upper East Side", "Venom symbiote"},
      {"Queens", "Venom symbiote"},
      {"Queens", "Mysterium VR"},
      {"Upper West Side", "Kraven the Hunter"},
      {"Queens", "Venom symbiote"},
      {"Upper East Side", "Kraven the Hunter"},
      {"Upper East Side", "Kraven the Hunter"},
      {"Upper West Side", "Venom symbiote"},
      {"Harlem", "Venom symbiote"},
      {"Upper West Side", "Kraven the Hunter"},
      {"Queens", "Kraven the Hunter"},
      {"Harlem", "Kraven the Hunter"},
      {"Harlem", "Kraven the Hunter"},
      {"Upper East Side", "Kraven the Hunter"},
      {"Upper West Side", "Mysterium VR"},
      {"Queens", "Kraven the Hunter"},
      {"Harlem", "Kraven the Hunter"},
      {"Queens", "Kraven the Hunter"},
      {"Upper East Side", "Venom symbiote"},
      {"Upper East Side", "Mysterium VR"},
      {"Upper East Side", "Kraven the Hunter"},
      {"Upper East Side", "Kraven the Hunter"},
      {"Harlem", "Kraven the Hunter"},
      {"Harlem", "Kraven the Hunter"},
      {"Harlem", "Kraven the Hunter"},
      {"Upper West Side", "Mysterium VR"},
      {"Upper East Side", "Mysterium VR"},
      {"Queens", "Mysterium VR"},
      {"Upper East Side", "Mysterium VR"},
      {"Upper West Side", "Mysterium VR"},
      {"Upper East Side", "Kraven the Hunter"},
      {"Upper East Side", "Venom symbiote"},
      {"Queens", "Mysterium VR"},
      {"Upper East Side", "Venom symbiote"},
      {"Harlem", "Mysterium VR"},
      {"Upper West Side", "Mysterium VR"},
      {"Upper West Side", "Kraven the Hunter"},
      {"Harlem", "Venom symbiote"},
      {"Queens", "Mysterium VR"},
      {"Upper West Side", "Venom symbiote"},
      {"Upper East Side", "Venom symbiote"},
      {"Upper West Side", "Venom symbiote"},
      {"Upper West Side", "Venom symbiote"},
      {"Harlem", "Kraven the Hunter"},
      {"Harlem", "Mysterium VR"},
      {"Upper West Side", "Mysterium VR"},
      {"Upper West Side", "Kraven the Hunter"},
      {"Upper East Side", "Kraven the Hunter"},
      {"Harlem", "Venom symbiote"},
      {"Upper East Side", "Venom symbiote"},
      {"Harlem", "Kraven the Hunter"},
      {"Upper East Side", "Kraven the Hunter"},
      {"Upper West Side", "Mysterium VR"},
      {"Harlem", "Venom symbiote"},
      {"Upper West Side", "Mysterium VR"},
      {"Upper West Side", "Mysterium VR"},
      {"Harlem", "Mysterium VR"},
      {"Harlem", "Kraven the Hunter"},
      {"Harlem", "Kraven the Hunter"},
      {"Upper East Side", "Kraven the Hunter"},
      {"Upper West Side", "Kraven the Hunter"},
      {"Queens", "Kraven the Hunter"},
      {"Queens", "Venom symbiote"},
      {"Upper West Side", "Mysterium VR"},
      {"Queens", "Venom symbiote"},
      {"Harlem", "Venom symbiote"},
      {"Upper East Side", "Mysterium VR"},
      {"Queens", "Venom symbiote"},
      {"Upper West Side", "Mysterium VR"},
      {"Queens", "Venom symbiote"},
      {"Queens", "Mysterium VR"},
      {"Queens", "Mysterium VR"},
      {"Harlem", "Kraven the Hunter"},
      {"Upper East Side", "Venom symbiote"},
      {"Upper East Side", "Kraven the Hunter"},
      {"Harlem", "Kraven the Hunter"},
      {"Harlem", "Venom symbiote"},
      {"Harlem", "Kraven the Hunter"},
      {"Upper West Side", "Mysterium VR"},
      {"Upper West Side", "Mysterium VR"},
      {"Queens", "Mysterium VR"},
      {"Queens", "Kraven the Hunter"},
      {"Harlem", "Mysterium VR"},
      {"Upper East Side", "Venom symbiote"},
      {"Queens", "Venom symbiote"},
      {"Harlem", "Mysterium VR"},
      {"Upper West Side", "Mysterium VR"},
      {"Upper West Side", "Venom symbiote"},
      {"Queens", "Venom symbiote"},
      {"Queens", "Mysterium VR"},
      {"Harlem", "Kraven the Hunter"},
      {"Harlem", "Kraven the Hunter"},
      {"Queens", "Kraven the Hunter"},
      {"Harlem", "Venom symbiote"},
      {"Upper West Side", "Kraven the Hunter"},
      {"Harlem", "Venom symbiote"},
      {"Upper West Side", "Venom symbiote"}
   }};

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
