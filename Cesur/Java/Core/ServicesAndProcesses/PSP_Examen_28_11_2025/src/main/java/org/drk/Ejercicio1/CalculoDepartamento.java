package org.drk.Ejercicio1;

public class CalculoDepartamento extends Thread{
    private String nombreDepartamento;
    private double nominaTotal;

    public CalculoDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
        this.nominaTotal = 0;
    }

    /**
     * Método run que simula el cálculo de la nómina del departamento.
     * Genera salarios aleatorios para 5 empleados y los suma para obtener la nómina
     * total del departamento.
     */
    @Override
    public void run() {
        System.out.println("[" + nombreDepartamento + "] Inicio del calculo de nomina.");
        for (int i = 1; i <= 5; i++) {
            int salarioEmpleado = (int)(Math.random() * 3000) + 1000;
            nominaTotal += salarioEmpleado;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[" + nombreDepartamento + "] Nómina total calculada: " + nominaTotal);
        System.out.println("[" + nombreDepartamento + "] Fin del calculo de nomina.");
    }

    public double getNominaTotal() {
        return nominaTotal;
    }
}




































