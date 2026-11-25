// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <print>
#include <string>

using namespace std;

class Car {
public:
   Car(string brand, int max_speed)
      : brand_{brand}, max_speed_{max_speed}
   {}

   auto brand() const -> string { return brand_; }
   auto speed() const -> int { return speed_; }

   // acelerar sin superar la velocidad máxima:
   auto accelerate(int amount) -> void
   {
      speed_ += amount;
      if (speed_ > max_speed_) {
         speed_ = max_speed_;
      }
   }

   // frenar sin bajar de 0:
   auto brake(int amount) -> void
   {
      speed_ -= amount;
      if (speed_ < 0) {
         speed_ = 0;
      }
   }

private:
   string brand_;
   int max_speed_;
   int speed_ = 0; // inicializador por defecto: el coche empieza parado
};

auto main() -> int
{
   auto car = Car{"Seat", 180};

   car.accelerate(100);
   println("{}: {} km/h", car.brand(), car.speed()); // 100
   car.accelerate(150);
   println("{}: {} km/h", car.brand(), car.speed()); // 180 (máximo)
   car.brake(200);
   println("{}: {} km/h", car.brand(), car.speed()); // 0
}
