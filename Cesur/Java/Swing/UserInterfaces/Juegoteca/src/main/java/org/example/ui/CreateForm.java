package org.example.ui;

import org.example.data.DataService;
import org.example.data.Juego;

import javax.swing.*;
import java.awt.event.*;

public class CreateForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textTitulo;
    private JComboBox comboBox1;
    private JSpinner spinnerAño;

    private DataService dataService;

    public CreateForm(DataService ds) {

        dataService = ds;

        setContentPane(contentPane);
        setModalityType(ModalityType.APPLICATION_MODAL);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);

        //( (DefaultComboBoxModel) comboBox1.getModel()).addAll(...);

        spinnerAño.setModel(new SpinnerNumberModel(1990, 1974, 2025, 1));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
    }

    private void onOK() {

        Juego juego = new Juego();

        juego.setNombre( textTitulo.getText());
        juego.setAño((Integer) spinnerAño.getValue());
        juego.setPlataforma( comboBox1.getSelectedItem().toString() );
        juego.setDesarrollador("Des");
        juego.setValoracion(0);
        juego.setTipo("AAA");
        juego.setDescripcion("Des");
        juego.setImageUrl("#");
        juego.setVideoUrl("#");
        juego.setOwnerId(0);
        juego.setStatus("public");

        if(dataService.save(juego).isEmpty()){
            JOptionPane.showMessageDialog(this, "Error al guardar","",JOptionPane.WARNING_MESSAGE);
        } else dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
