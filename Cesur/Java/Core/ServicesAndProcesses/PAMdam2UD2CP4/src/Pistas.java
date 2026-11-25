public class Pistas {
    private boolean[] pistasDisponibles;

    public Pistas() {
        this.pistasDisponibles = new boolean[] {true, true};
    }

    public synchronized int ocuparPista(String nombreVehiculo) throws InterruptedException {
        while (true){
            for(int i=0; i<pistasDisponibles.length; i++){
                if (pistasDisponibles[i]){
                    System.out.println(nombreVehiculo + " ha entrado en la pista " + i);
                    pistasDisponibles[i] = false;
                    return i;
                }
            }
            wait();
        }
    }

    public synchronized void liberarPista(int numPista, String nombreVehiculo, double tiempo) {
        pistasDisponibles[numPista] = true;
        System.out.println(nombreVehiculo + " completo la pista " + numPista + " en " + tiempo + " segundos.");
        notifyAll();
    }
}
