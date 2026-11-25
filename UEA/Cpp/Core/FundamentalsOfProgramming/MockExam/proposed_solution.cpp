// ============================================================================
// SIMULACRO DE EXAMEN · SOLUCIÓN PROPUESTA (NO OFICIAL)
// Ejercicio de práctica creado para repasar todo lo que entra en el examen.
// Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <algorithm> // ranges::sort, ranges::partition
#include <array>
#include <iostream>  // cin
#include <print>
#include <ranges>    // views::take, views::chunk_by
#include <string>

using namespace std;

// [A] agregado con los datos de un jugador:
struct Player {
   string name;  // nombre del jugador
   string team;  // equipo
   int points;   // puntos acumulados
   bool active;  // ¿sigue en activo en el torneo?
};

// [B] suma de puntos de todos los jugadores (array pasado por referencia):
auto total_points(array<Player, 8>& ps) -> int
{
   auto sum = int{0};
   for (Player& p : ps) {
      sum += p.points;
   }
   return sum;
}

// [C] índice del jugador con más puntos (patrón "máximo con índice"):
auto best_index(array<Player, 8>& ps) -> size_t
{
   auto idx = 0uz;
   for (auto i = 1uz; i < ps.size(); ++i) {
      if (ps[i].points > ps[idx].points) {
         idx = i;
      }
   }
   return idx;
}

// [D] lee un entero entre 0 y 100; repite mientras el dato no sea válido:
auto read_min_points() -> int
{
   auto str = string{""};
   while (true) {
      print("Puntuación mínima (0-100): ");
      getline(cin, str);
      try {
         auto n = stoi(str);
         if (n >= 0 and n <= 100) {
            return n; // dato válido: salimos de la función
         }
         println("Fuera de rango, prueba otra vez.");
      }
      catch (...) { // stoi falla si el texto no es un número
         println("Eso no es un número, prueba otra vez.");
      }
   }
}

// [E] predicado para partition:
auto is_active(Player& p) -> bool
{
   return p.active;
}

// [F] predicado binario para chunk_by:
auto same_team(Player& p1, Player& p2) -> bool
{
   return p1.team == p2.team;
}

auto main() -> int
{
   auto players = array<Player, 8>{{
      {"Nova",    "Dragons", 87, true },
      {"Kaiser",  "Wolves",  64, false},
      {"Pixel",   "Dragons", 92, true },
      {"Shadow",  "Titans",  45, true },
      {"Blaze",   "Wolves",  78, true },
      {"Echo",    "Titans",  81, false},
      {"Raven",   "Dragons", 39, false},
      {"Volt",    "Titans",  70, true }
   }};

   // ---------------------------------------------------------------- [B]
   auto total = total_points(players);
   println("[B] Puntos totales: {} | media: {:.2f}", total, 1.0 * total / players.size()); // 1.0 evita la división entera

   // ---------------------------------------------------------------- [C]
   auto idx = best_index(players);
   println("[C] Mejor jugador: {} ({}) con {} puntos",
           players[idx].name, players[idx].team, players[idx].points);

   // ---------------------------------------------------------------- [D]
   auto min_points = read_min_points();
   auto counter = int{0};
   for (Player& p : players) {
      if (p.points >= min_points) {
         ++counter;
      }
   }
   println("[D] Jugadores con al menos {} puntos: {} ({:.1f}%)",
           min_points, counter, 100.0 * counter / players.size());

   // ---------------------------------------------------------------- [E]
   // activos delante; cut = primer jugador inactivo
   auto inactive = ranges::partition(players, is_active);
   auto cut = inactive.begin();
   // ordenamos sólo los activos por puntos (de menor a mayor):
   ranges::sort(players.begin(), cut, {}, &Player::points);

   println("[E] Activos (por puntos):");
   for (auto it = players.begin(); it != cut; ++it) {
      println("     {:<7} {}", it->name, it->points);
   }
   println("    Inactivos:");
   for (auto it = cut; it != players.end(); ++it) {
      println("     {}", it->name);
   }

   // ---------------------------------------------------------------- [F]
   ranges::sort(players, {}, &Player::team); // ¡ordenar antes de chunk_by!
   println("[F] Resumen por equipo:");
   for (auto team : players | views::chunk_by(same_team)) {
      auto team_points = int{0};
      for (Player& p : team) {
         team_points += p.points;
      }
      println("     {:<8} {} jugadores, {} puntos", team[0].team, team.size(), team_points);
   }

   // ---------------------------------------------------------------- [G]
   ranges::sort(players, {}, &Player::points);
   println("[G] Los 3 con menos puntos:");
   for (Player& p : players | views::take(3)) {
      println("     {} ({})", p.name, p.points);
   }
}
