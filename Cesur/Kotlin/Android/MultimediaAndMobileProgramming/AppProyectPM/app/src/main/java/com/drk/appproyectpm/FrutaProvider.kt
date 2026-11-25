package com.drk.appproyectpm

class FrutaProvider {
    companion object {
        fun cargarLista(): MutableList<Fruta> {
            return mutableListOf(
                Fruta(
                    "Fresas",
                    "La fresa de Huelva, la mejor del mundo.",
                    R.drawable.strawberry_bg,
                    5.99,
                    "Huelva, España",
                    "Primavera",
                    "Rojo brillante",
                    "Ricas en vitamina C y antioxidantes"
                ),
                Fruta(
                    "Manzanas",
                    "Manzanas golden, pink lady...",
                    R.drawable.apple_bg,
                    2.49,
                    "Lleida, España",
                    "Otoño",
                    "Verde o rojo",
                    "Aporta fibra y vitamina C"
                ),
                Fruta(
                    "Peras",
                    "Peras blanquilla, conferencia, limonera...",
                    R.drawable.pear_bg,
                    2.89,
                    "Aragón, España",
                    "Verano - Otoño",
                    "Verde o amarillento",
                    "Fuente de fibra y antioxidantes naturales"
                ),
                Fruta(
                    "Bananas",
                    "Plátano de Canarias, mejor que las bananas",
                    R.drawable.banana_bg,
                    2.99,
                    "Islas Canarias",
                    "Todo el año",
                    "Amarillo intenso",
                    "Fuente de potasio y energía natural"
                ),
                Fruta(
                    "Cerezas",
                    "Cerezas del Valle del Jerte",
                    R.drawable.cherry_bg,
                    7.49,
                    "Valle del Jerte, España",
                    "Primavera",
                    "Rojo oscuro brillante",
                    "Aporta antioxidantes y melatonina natural"
                ),
                Fruta(
                    "Naranjas",
                    "Naranjas de Valencia",
                    R.drawable.orange_bg,
                    1.99,
                    "Valencia, España",
                    "Invierno - Primavera",
                    "Naranja",
                    "Ricas en vitamina C y ácido fólico"
                ),
                Fruta(
                    "Frambuesas",
                    "Las frambuesas aportan potasio, hierro y calcio...",
                    R.drawable.raspberry_bg,
                    6.49,
                    "Huesca, España",
                    "Verano",
                    "Rojo rosado",
                    "Ricas en fibra, potasio y antioxidantes"
                )
            )
        }
    }
}
