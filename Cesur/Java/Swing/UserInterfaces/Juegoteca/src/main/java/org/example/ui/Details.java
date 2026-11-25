package org.example.ui;

import org.example.context.ContextService;
import org.example.data.Juego;

import javax.swing.*;
import java.awt.event.*;

public class Details extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel labelId;
    private JLabel labelNombre;
    private JLabel labelDescripcion;
    private JLabel labelAño;
    private JLabel LabelImagen;
    private JLabel labelPlataforma;
    private JLabel labelTipo;
    private JLabel labelDesarrolladora;
    private JLabel labelOwner;

    public Details(JFrame parent) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);


        // Juego juego = AppContext.juegoSeleccionado;
        Juego juego = (Juego) ContextService.getInstance().getItem("juegoSeleccionado").get();

        //nombre la ventana
        setTitle(juego.getNombre());

        labelId.setText(juego.getId().toString());
        labelNombre.setText(juego.getNombre());
        labelDescripcion.setText(juego.getDescripcion());
        labelAño.setText(juego.getAño().toString());
        LabelImagen.setText(juego.getImageUrl());
        labelPlataforma.setText(juego.getPlataforma());
        labelTipo.setText(juego.getTipo());
        labelDesarrolladora.setText(juego.getDesarrollador());
        labelOwner.setText(juego.getOwnerId().toString());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

}
