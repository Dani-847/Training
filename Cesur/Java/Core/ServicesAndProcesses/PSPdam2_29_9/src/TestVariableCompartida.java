public class TestVariableCompartida {
    public static void main(String[] args) throws InterruptedException {
        VariableCompartida variable = new VariableCompartida(0);
        Hebra hebra = new Hebra(variable, 10000);

        Thread h1 = new Thread(hebra);
        Thread h2 = new Thread(hebra);

        h1.start();
        h2.start();

        h1.join();
        h2.join();

        System.out.println("Valor final de v: " + variable.get());
    }
}
