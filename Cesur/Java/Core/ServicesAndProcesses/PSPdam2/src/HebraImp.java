public class HebraImp implements Runnable {
    private String caracter;
    private int repeticiones;

        public HebraImp(String caracter, int repeticiones) {
        this.caracter = caracter;
        this.repeticiones = repeticiones;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeticiones; i++) {
            System.out.println(caracter);
        }
    }
}
