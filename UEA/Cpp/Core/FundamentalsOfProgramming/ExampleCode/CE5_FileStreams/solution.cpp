#include <algorithm>
#include <fstream>   // flujos de entrada/salida a ficheros
#include <print>
#include <ranges>
#include <string>    // cadenas de caracteres
#include <vector>    // contenedor secuencial de elementos contiguos en memoria

#include <nlohmann/json.hpp> // biblioteca de serialización/deserialización de objetos JSON

using namespace std;

// agregado capaz de almacenar la información de un ítem en venta;
// importante: identificadores coincidentes con las claves del fichero JSON Lines
struct Item {
   string brand;   // marca
   string garment; // tipo de prenda
   double price;   // precio (en euros)
};

// macro de la biblioteca nlohmann-json para indicar cómo convertir automáticamente
// un objeto de tipo Item a JSON y viceversa:
NLOHMANN_DEFINE_TYPE_NON_INTRUSIVE(Item, brand, garment, price)

// función que determina si dos objetos Item pertenecen a la misma marca:
auto equal_brand(Item& i1, Item& i2) -> bool
{
   return i1.brand == i2.brand;
}

auto main() -> int
{
   // abrimos flujo de sólo lectura (entrada) al fichero JSON Lines:
   auto ifs = ifstream{"../../sample_clothing.jsonl", ios::binary};

   // comprobamos si el flujo al fichero está abierto:
   if (!ifs) {
      println("Lo sentimos, no hemos podido abrir el fichero JSON Lines");
      return 1; // terminación en estado de error
   }

   // creamos un contenedor inicialmente vacío en el que poder acumular
   // los Items obtenidos desde el fichero:
   auto items_for_sale = vector<Item>{};

   auto line = string{""}; // string para cargar cada línea del fichero
   // bucle que analiza el fichero línea a línea:
   while (getline(ifs, line)) {
      // parseamos la línea para comprobar que tenga formato JSON correcto,
      // extraer el nombre de la marca, el tipo de prenda y su precio,
      // e insertar esos datos en un objeto Item:
      auto item = nlohmann::json::parse(line).get<Item>(); // item es de tipo Item
      // enviamos al fondo del vector el ítem recién obtenido:
      items_for_sale.push_back(item);
   }

   // ordenamos las entradas del vector por orden alfabético de marca:
   ranges::sort(items_for_sale, {}, &Item::brand);

   // iteramos marca a marca:
   for (auto brand_group : items_for_sale | views::chunk_by(equal_brand)) {
      // nombre de la marca:
      println("Marca: {} ---------------------", brand_group[0].brand);
      
      // número de ítems en venta de dicha marca:
      println("  - número de ítems: {}", brand_group.size());
      
      // coste total acumulado por la marca:
      auto total_price = double{0.0};
      for (auto i = 0uz; i < brand_group.size(); ++i) {
         total_price += brand_group[i].price;
      }
      println("  - coste total: {:.1f} $", total_price);
   }

   return 0; // se puede omitir
}

/* Alternativa para el cálculo del precio total:

   auto total_price = ranges::fold_left(
      brand_group | views::transform(&Item::price), 0.0, std::plus{}
   );
*/