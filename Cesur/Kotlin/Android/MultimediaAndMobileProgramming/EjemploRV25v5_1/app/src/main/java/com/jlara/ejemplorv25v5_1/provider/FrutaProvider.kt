package com.jlara.ejemplorv25v5_1.provider

import com.jlara.ejemplorv25v5_1.model.Fruta
import com.jlara.ejemplorv25v5_1.R

class FrutaProvider {
    companion object{
        fun cargarLista():MutableList<Fruta> {
            return mutableListOf(
                Fruta(
                    0,
                    "Fresas",
                    "La fresa de Huelva, la mejor del mundo.",
                    R.drawable.strawberry_bg
                ),
                Fruta(1, "Manzanas", "Manzanas golden, pink lady...", R.drawable.apple_bg),
                Fruta(2, "Peras", "Peras blanquilla, conferencia, limonera...", R.drawable.pear_bg),
                Fruta(
                    3,
                    "Bananas",
                    "Plátano de Canarias, mejor que las bananas",
                    R.drawable.banana_bg
                ),
                Fruta(4, "Cerezas", "Cerezas del Valle del Jerte", R.drawable.cherry_bg),
                Fruta(5, "Naranjas", "Naranjas de Valencia", R.drawable.orange_bg),
                Fruta(
                    6,
                    "Frambuesas",
                    "Las frambuesas aportan potasio, hierro y calcio...",
                    R.drawable.raspberry_bg
                )
            )
        }
    }
}

