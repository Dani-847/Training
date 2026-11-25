# Sesión colaborativa 1 · Death Stranding [Dificultad: 2/5]

**Conceptos:** Nivel 3 (getline, stod, stoi) · Nivel 6 (std::array) · Nivel 4 (for) · patrones acumulador, contador y máximo con índice · Nivel 10 (views::take)

## Enunciado

Fecha de entrega 8 de oct por 12:10 Puntos 10 Tipo de entrega: un archivo cargado Tipos de archivo cpp
Death Stranding [Dificultad: 2/5]
Tomemos como modelo el videojuego "Death Stranding 2: On the Beach" (Kojima Productions, 2025). Imaginad que estáis desarrollando un sistema para registrar los objetos que porta en su inventario el protagonista Sam Porter Bridges (un repartidor de suministros que ayuda a reconectar las ciudades aisladas en un mundo postapocalíptico). Cada objeto posee:

Destino (std::string).
Peso (double implícitamente medido en kg).
Kilometraje (int implícitamente medido en km).
A modo de ejemplo: Laboratorio de Heartman; 18.0; 73

[A] Solicitad al usuario que proporcione por terminal los datos de cinco objetos a repartir (indicando destino, peso y kilometraje de cada uno). Almacenad los datos en tres arrays independientes, según correspondan:

   auto destinos = std::array<std::string, 5>{};
   auto pesos = std::array<double, 5>{};
   auto kilometrajes = std::array<int, 5>{};

[B] A través de un bucle for, calculad el peso total transportado y el kilometraje total recorrido. 

[C] Contad cuántas entregas requieren recorrer más de 50 km. Imprimid el resultado en la terminal.

[D] Mostrad únicamente los tres primeros destinos registrados utilizando un bucle for y la vista std::views::take.

[E] Identificad el destino más lejano alcanzado, de forma que el programa imprima su nombre en la terminal.

Deberéis entregar el código del ejercicio con extensión .cpp, 100% conforme con el estándar ISO C++23.

## Solución

Hay dos soluciones válidas:
- `candidate_solution_1_three_arrays.cpp`: usa tres arrays independientes, tal como pide el enunciado.
- `candidate_solution_2_struct.cpp`: agrupa los datos en un `struct Item` y usa un solo array. Es más ordenada y es el estilo que se impone después.

## Ejemplo de ejecución

Datos escritos por el usuario: "Laboratorio de Heartman", "18.0", "73", "Puerto Norte", "5.5", "42", "Granja Sur", "12", "55", "Estación Central", "7.25", "30", "Refugio Este", "20", "91".

Salida de `candidate_solution_2_struct.cpp`:

```text
Destino #1: Laboratorio de Heartman
Peso #1: 18.0
Kilometraje #1: 73
Destino #2: Puerto Norte
Peso #2: 5.5
Kilometraje #2: 42
Destino #3: Granja Sur
Peso #3: 12
Kilometraje #3: 55
Destino #4: Estación Central
Peso #4: 7.25
Kilometraje #4: 30
Destino #5: Refugio Este
Peso #5: 20
Kilometraje #5: 91
Pesos total: 62.75 kg | Kilometraje total: 291 km
Número de encargos a más de 50 km: 3
Destino: Laboratorio de Heartman
Destino: Puerto Norte
Destino: Granja Sur
Carga #5 a 'Refugio Este' tiene el mayor kilometraje: 91 km
```
