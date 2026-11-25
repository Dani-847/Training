public class VariableCompartida{
    private int v;

    public VariableCompartida(int valorInicial) {
        this.v = valorInicial;
    }

    public void set(int valor) {
        this.v = valor;
    }

    public int get() {
        return this.v;
    }

    public void inc() {
        this.v++;
    }
}
