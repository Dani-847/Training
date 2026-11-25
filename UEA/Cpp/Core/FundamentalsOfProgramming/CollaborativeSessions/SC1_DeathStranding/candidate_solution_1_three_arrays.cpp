#include <array>
#include <iostream>
#include <print>
#include <string>
#include <ranges>

using namespace std;

auto main() -> int
{
    auto destinos     = array<string, 5>{};
    auto pesos        = array<double, 5>{};
    auto kilometrajes = array<int, 5>{};

    // apartado (a):
    for (auto i = 0uz; i < destinos.size(); ++i) {
        print("Destino #{}: ", i + 1);
        getline(cin, destinos[i]);
        auto str = string{""};
        print("Peso #{}: ", i + 1);
        getline(cin, str);
        pesos[i] = stod(str);
        print("Kilometraje #{}: ", i + 1);
        getline(cin, str);
        kilometrajes[i] = stoi(str);
    }

    // apartado (b):
    auto peso_total = double{0.0};
    auto kilometraje_total = int{0};
    for (auto i = 0uz; i < pesos.size(); ++i) {
        peso_total += pesos[i];
        kilometraje_total += kilometrajes[i];
    }
    println("Peso total = {} | Kilometraje total = {}", peso_total, kilometraje_total);

    // apartado (c):
    auto mas_de_50_km = 0uz;
    for (auto i = 0uz; i < kilometrajes.size(); ++i) {
        if (kilometrajes[i] > 50) {
            ++mas_de_50_km;
        }
    }
    println("Número de encargos a más de 50 km: {}", mas_de_50_km);

    // apartado (d):
    for (string& destino : destinos | views::take(3)) {
        println("Destino: {}", destino);
    }

    // apartado (e): 
    auto idx = 0uz;
    for (auto i = 1uz; i < kilometrajes.size(); ++i) {
        if (kilometrajes[i] > kilometrajes[idx]) {
            idx = i;
        }
    }
    println(
        "Mayor distancia: carga #{} a '{}' requiere {}km",
        idx + 1,
        destinos[idx],
        kilometrajes[idx]
    );
}