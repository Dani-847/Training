package org.drk.Ejercicio1;

public class Supervisor extends Thread{

    /**
     * Método run que inicia el proceso de cálculo de nómina total.
     * Crea y arranca hilos para cada departamento, espera a que terminen
     * y luego suma las nóminas individuales para obtener la nómina total.
     */
    @Override
    public void run() {
        System.out.println("[Supervisor] Inicio del proceso de calculo de nomina total.");
        CalculoDepartamento dept1 = new CalculoDepartamento("Administración");
        CalculoDepartamento dept2 = new CalculoDepartamento("Producción");
        CalculoDepartamento dept3 = new CalculoDepartamento("Ventas");
        CalculoDepartamento dept4 = new CalculoDepartamento("Marketing");

        dept1.start();
        dept2.start();
        dept3.start();
        dept4.start();

        try {
            dept1.join();
            dept2.join();
            dept3.join();
            dept4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double totalNomina = dept1.getNominaTotal() + dept2.getNominaTotal() + dept3.getNominaTotal() + dept4.getNominaTotal();
        System.out.println("[Supervisor] Nómina total calculada: " + totalNomina);
        System.out.println("[Supervisor] Fin del proceso de cálculo de nomina total.");
    }
}







































