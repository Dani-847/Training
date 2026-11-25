public class TestHebras {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new HebraImp("A", 3));
        Thread t2 = new Thread(new HebraImp("B", 3));
        Thread t3 = new Thread(new HebraImp("C", 3));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        //En algunas ejecuciones se ve como el orden de impresion no es el mismo por como funciona el planificador de hilos
        //A veces se imprime A B C, otras veces C A B, etc. E incluso a veces se imprime A A B C C B, etc.
        System.out.println();
    }
}
