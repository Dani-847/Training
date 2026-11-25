package org.example.ui;

import org.example.context.ContextService;
import org.example.data.Juego;
import org.example.data.DataService;
import org.example.user.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Main extends javax.swing.JFrame {
    private JTable table1;
    private JPanel panel1;

    private DataService dataservice;
    private UserService userservice;

    private ArrayList<Juego> juegos = new ArrayList<>();

    /* Es necesario que este accesible para poder modificarlo */
    private JMenuItem menuItemAñadir;

    public Main(DataService ds, UserService us) {
        dataservice = ds;
        userservice = us;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Juegoteca");
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel1);

        /* Añadir menu de forma programática */
        JMenuBar menuBar = PrepareMenuBar();
        panel1.add(menuBar, BorderLayout.NORTH);


        /* Configuración y carga de la tabla */
        var modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        table1.setModel(modelo);

        loadDataTable();

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table1.getSelectionModel().addListSelectionListener( (e)->{
                if(!e.getValueIsAdjusting() && table1.getSelectedRow()>=0 ){
                    System.out.println("seleccionado: "+table1.getSelectedRow());
                    Juego juego = juegos.get(table1.getSelectedRow());

                    // AppContext.juegoSeleccionado = juego;
                    ContextService.getInstance().addItem("juegoSeleccionado",juego);

                    (new Details(this)).setVisible(true);
                }
            }
        );

    }

    private void loadDataTable() {
        DefaultTableModel modelo = (DefaultTableModel) table1.getModel();
        juegos = (ArrayList<Juego>) dataservice.findAll();
        modelo.setRowCount(0);
        juegos.forEach( (j)->{
            var fila =  new Object[]{ j.getId(), j.getNombre(),j.getDescripcion() };
            modelo.addRow(fila);
        });
    }

    private JMenuBar PrepareMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu jMenuInicio = new JMenu("Inicio");
        JMenuItem menuItemLogin = new JMenuItem("Login");
        // es un atributo de la clase, no hay que hacer nwe
        menuItemAñadir = new JMenuItem("Añadir");
        menuItemAñadir.setEnabled(false);
        JMenuItem menuItemSalir = new JMenuItem("Salir");

        menuBar.add(jMenuInicio);
        jMenuInicio.add(menuItemLogin);
        jMenuInicio.addSeparator();
        jMenuInicio.add(menuItemAñadir);
        jMenuInicio.addSeparator();
        jMenuInicio.add(menuItemSalir);

        /* Eventos del menú */

        menuItemLogin.addActionListener(e -> {

            (new Login(this, userservice)).setVisible(true);

            // Como es modal, se espera la ejecución hasta que se cierre

            ContextService.getInstance().getItem("usuarioActivo").ifPresent( (_)->{
                menuItemAñadir.setEnabled(true);
            });
        });

        menuItemSalir.addActionListener(e -> { System.exit(0); });
        menuItemAñadir.addActionListener(e -> {
           // Añadir nuevo juego
           //JOptionPane.showMessageDialog(this, "No implementado aún");
            (new CreateForm(dataservice)).setVisible(true);
            loadDataTable();
        });
        return menuBar;
    }

    public void start(){
        this.setVisible(true);
    }
}
