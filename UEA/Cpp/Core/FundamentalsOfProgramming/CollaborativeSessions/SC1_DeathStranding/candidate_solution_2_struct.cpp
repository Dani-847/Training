#include <array>
#include <iostream>
#include <print>
#include <string>
#include <ranges>

using namespace std;

// agregado que representa un ítem de reparto:
struct Item {
    string destino;
    double peso = 0.0;
    int kilometraje = 0;
};

auto main() -> int
{
    auto items = array<Item, 5>{};

    // apartado (a):

    for (auto i = 0uz; i < items.size(); ++i) {
        print("Destino #{}: ", i + 1);
        getline(cin, items[i].destino);
        auto str = string{""};
        print("Peso #{}: ", i + 1);
        getline(cin, str);
        items[i].peso = stod(str);
        print("Kilometraje #{}: ", i + 1);
        getline(cin, str);
        items[i].kilometraje = stoi(str);
    }

    // apartado (b):

    auto peso_total = double{0.0};
    auto kilometraje_total = int{0};
    for (auto i = 0uz; i < items.size(); ++i) {
        peso_total += items[i].peso;
        kilometraje_total += items[i].kilometraje;
    }
    println("Pesos total: {} kg | Kilometraje total: {} km", peso_total, kilometraje_total);

    // apartado (c):
    auto mas_de_50_km = 0uz;
    for (auto i = 0uz; i < items.size(); ++i) {
        if (items[i].kilometraje > 50) {
            ++mas_de_50_km;
        }
    }
    println("Número de encargos a más de 50 km: {}", mas_de_50_km);

    // apartado (d):

    for (Item& item : items | views::take(3)) {
        println("Destino: {}", item.destino);
    }

    // apartado (e):

    auto idx = 0uz;
    for (auto j = 1uz; j < items.size(); ++j) {
        if (items[j].kilometraje > items[idx].kilometraje) {
            idx = j;
        }
    }
    println(
        "Carga #{} a '{}' tiene el mayor kilometraje: {} km",
        idx + 1,
        items[idx].destino,
        items[idx].kilometraje
    );
}