import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Principal extends JFrame {
    private JPanel panel1;
    private JList list1;
    private JButton boton;

    public Principal() {
        this.setTitle("Alumnos");
        this.setResizable(false);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);

        boton.addActionListener(e -> {
            Path archivo = Path.of("alumnos.txt");
            try {
                var lineas = Files.readAllLines(archivo);
                lineas.forEach(linea -> {
                    ((DefaultListMode<String>)lista.getModel()).addElement(linea);
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void start(Boolean b) {
        this.setVisible(b);
    }
}
