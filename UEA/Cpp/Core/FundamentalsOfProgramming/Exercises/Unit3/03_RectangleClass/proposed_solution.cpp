// ============================================================================
// SOLUCIÓN PROPUESTA (NO OFICIAL): el campus no publica solución para este
// ejercicio. Está escrita siguiendo el estilo de las soluciones del profesor.
// ============================================================================

#include <print>
#include <stdexcept>

using namespace std;

class Rectangle {
public:
   // constructor: valida los datos (invariante: lados positivos)
   Rectangle(double width, double height)
      : width_{width}, height_{height}
   {
      if (width <= 0.0 or height <= 0.0) {
         throw invalid_argument{"los lados deben ser positivos"};
      }
   }

   // funciones miembro const: no modifican el objeto
   auto width() const -> double { return width_; }
   auto height() const -> double { return height_; }
   auto area() const -> double { return width_ * height_; }
   auto perimeter() const -> double { return 2 * (width_ + height_); }

   // modificador que mantiene el invariante:
   auto scale(double factor) -> void
   {
      if (factor <= 0.0) {
         throw invalid_argument{"factor no válido"};
      }
      width_ *= factor;
      height_ *= factor;
   }

private:
   // datos privados: sólo accesibles desde dentro de la clase (encapsulamiento)
   double width_;
   double height_;
};

auto main() -> int
{
   auto r = Rectangle{8.5, 5.6};
   println("{} x {} -> área {:.2f}, perímetro {:.2f}", r.width(), r.height(), r.area(), r.perimeter());

   r.scale(2.0);
   println("escalado: {} x {} -> área {:.2f}", r.width(), r.height(), r.area());

   try {
      auto bad = Rectangle{-1.0, 3.0}; // lanza excepción
      println("{}", bad.area());
   }
   catch (exception& e) {
      println("error: {}", e.what());
   }
}
