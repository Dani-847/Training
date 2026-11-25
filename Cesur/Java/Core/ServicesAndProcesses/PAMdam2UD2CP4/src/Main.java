public class Main {
    public static void main(String[] args) throws InterruptedException {
        Pistas pistas = new Pistas();
        Vehiculo v1 = new Vehiculo("Vehiculo 1", pistas);
        Vehiculo v2 = new Vehiculo("Vehiculo 2", pistas);
        Vehiculo v3 = new Vehiculo("Vehiculo 3", pistas);
        Vehiculo v4 = new Vehiculo("Vehiculo 4", pistas);

        v1.start();
        v2.start();
        v3.start();
        v4.start();
    }
}

