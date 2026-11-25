public class Hebra implements Runnable {
    private VariableCompartida variable;
    private int veces;

    public Hebra(VariableCompartida variable, int veces) {
        this.variable = variable;
        this.veces = veces;
    }

    @Override
    public void run() {
        for (int i = 0; i < veces; i++) {
            variable.inc();
            System.out.println("Valor de v: " + variable.get());
        }
    }
}
