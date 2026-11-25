package com.example.pmdmdam2u6cp0

class PersonaProvider {
    companion object {
        fun cargarLista(): MutableList<Persona> {
            return mutableListOf(
                Persona("Ana López", "Ingeniera de software", R.drawable.chica01),
                Persona("Carlos Ruiz", "Médico pediatra", R.drawable.chica02),
                Persona("Lucía Gómez", "Diseñadora gráfica", R.drawable.chica03),
                Persona("María Torres", "Psicóloga", R.drawable.chica04),
                Persona("Elena Navarro", "Periodista", R.drawable.chica05),
                Persona("Patricia Ramos", "Bióloga", R.drawable.chica06),
                Persona("Miguel Fernández", "Abogado", R.drawable.chico01),
                Persona("Sofía Martínez", "Arquitecta", R.drawable.chico02),
                Persona("Javier Sánchez", "Profesor universitario", R.drawable.chico03),
                Persona("María Torres", "Psicóloga", R.drawable.chico04),
                Persona("Elena Navarro", "Periodista", R.drawable.chico05),
                Persona("Patricia Ramos", "Bióloga", R.drawable.chico06),


            )
        }
    }
}
