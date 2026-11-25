public class Vehiculo extends Thread {
    private String nombre;
    private Pistas pistas;

    public Vehiculo(String nombre, Pistas pistas) {
        this.nombre = nombre;
        this.pistas = pistas;
    }

    @Override
    public void run() {
        try {
            int numPista = pistas.ocuparPista(nombre);
            double tiempo = 2 + (Math.random() * 3);
            Thread.sleep((long) (tiempo * 1000));
            pistas.liberarPista(numPista, nombre, tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
