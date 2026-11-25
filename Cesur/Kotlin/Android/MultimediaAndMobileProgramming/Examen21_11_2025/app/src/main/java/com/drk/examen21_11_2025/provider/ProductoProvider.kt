package com.drk.examen21_11_2025.provider

import com.drk.examen21_11_2025.R
import com.drk.examen21_11_2025.model.Producto

class ProductoProvider {
    companion object {
        fun listaCarga() = mutableListOf(
            Producto(
                0, "Laptop Lenovo ThinkPad X1 Carbon",
                R.drawable.laptop_lenovo_x1_carbon,
                1200.0,
                "Portátiles",
                "Ultraligera y potente, con procesador Intel i7, 16GB RAM y pantalla Full HD."
            ),
            Producto(
                0, "Monitor Dell Ultrasharp U2723QE",
                R.drawable.monitor_dell_ultrasharp,
                450.0,
                "Monitores",
                "Monitor 27\" 4K UHD con alta precisión de color y conectividad USB-C."
            ),
            Producto(
                0, "Teclado mecánico Logitech G Pro",
                R.drawable.teclado_logitech_gpro,
                130.0,
                "Periféricos",
                "Teclado mecánico compacto con switches táctiles y retroiluminación RGB."
            ),
            Producto(
                0, "Ratón inalámbrico Microsoft Surface",
                R.drawable.raton_microsoft_surface,
                70.0,
                "Periféricos",
                "Ratón ergonómico inalámbrico con alta precisión y diseño minimalista."
            ),
            Producto(
                0, "Disco SSD Samsung 970 EVO Plus 1TB",
                R.drawable.ssd_samsung_970_evo,
                150.0,
                "Almacenamiento",
                "Unidad SSD NVMe de alta velocidad para mejorar tiempos de carga y transferencia."
            ),
            Producto(
                0, "Placa base ASUS ROG Strix Z690-E",
                R.drawable.placa_asus_rog_strix,
                350.0,
                "Componentes",
                "Placa base gaming con soporte para procesadores Intel de última generación y conectividad avanzada."
            ),
            Producto(
                0, "Memoria RAM Corsair Vengeance LPX 16GB",
                R.drawable.ram_corsair_16gb,
                90.0,
                "Componentes",
                "Memoria DDR4 de alto rendimiento para gaming y edición."
            ),
            Producto(
                0, "Fuente de alimentación EVGA 600W",
                R.drawable.fuente_evga_600w,
                60.0,
                "Componentes",
                "Fuente eficiente y silenciosa con certificación 80 Plus."
            ),
            Producto(
                0, "Caja NZXT H510",
                R.drawable.caja_nzxt_h510,
                80.0,
                "Componentes",
                "Caja compacta con buena ventilación y diseño moderno."
            ),
            Producto(
                0, "Tarjeta gráfica NVIDIA RTX 3060",
                R.drawable.gpu_nvidia_rtx3060,
                500.0,
                "Componentes",
                "Tarjeta gráfica para gaming en alta definición y trabajo creativo."
            ),
            Producto(
                0, "Router WiFi TP-Link Archer AX50",
                R.drawable.router_tp_link_ax50,
                120.0,
                "Redes",
                "Router WiFi 6 con alta velocidad y cobertura mejorada."
            ),
            Producto(
                0, "Monitor curvo Samsung Odyssey G5",
                R.drawable.monitor_samsung_odyssey,
                300.0,
                "Monitores",
                "Monitor gamer curvo de 27 pulgadas con alta tasa de refresco."
            ),
            Producto(
                0, "Webcam Logitech C920",
                R.drawable.webcam_logitech_c920,
                90.0,
                "Periféricos",
                "Webcam HD para streaming y videollamadas."
            ),
            Producto(
                0, "Auriculares gaming HyperX Cloud II",
                R.drawable.auriculares_hyperx_cloud2,
                100.0,
                "Periféricos",
                "Auriculares cómodos con sonido envolvente y micrófono."
            ),
            Producto(
                0, "Disco duro externo WD 2T",
                R.drawable.hdd_wd_2tb,
                80.0,
                "Almacenamiento",
                "Disco duro portátil para copias y almacenamiento adicional."
            ),
            Producto(
                0, "Placa base MSI B550 TOMAHAWK",
                R.drawable.placa_msi_b550_tomahawk,
                180.0,
                "Componentes",
                "Placa base con soporte para procesadores AMD Ryzen y múltiples puertos."
            ),
            Producto(
                0, "Mousepad XXL Glorious",
                R.drawable.mousepad_glorious_xxl,
                30.0,
                "Periféricos",
                "Alfombrilla grande para escritorio con superficie anti-deslizante."
            ),
            Producto(
                0, "SSD Crucial MX500 500GB",
                R.drawable.ssd_crucial_mx500,
                60.0,
                "Almacenamiento",
                "Unidad SSD SATA de alto rendimiento y fiabilidad."
            ),
            Producto(
                0, "Teclado inalámbrico Apple Magic Keyboard",
                R.drawable.teclado_apple_magic,
                110.0,
                "Periféricos",
                "Teclado inalámbrico compacto con diseño minimalista."
            ),
            Producto(
                0, "Hub USB Anker 7 puertos",
                R.drawable.hub_anker_7puertos,
                35.0,
                "Accesorios",
                "Concentrador USB para conectar múltiples dispositivos."
            )
        )
    }
}