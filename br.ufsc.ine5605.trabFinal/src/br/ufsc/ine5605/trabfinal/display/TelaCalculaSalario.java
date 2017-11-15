/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabfinal.display;

import javax.swing.*;
import java.awt.*;
import br.ufsc.ine5605.trabfinal.controllers.ControladorCalculaSalario;

public class TelaCalculaSalario extends JFrame {
    private JLabel lbMatricula;
    private JTextField jtMatricula;
    private JLabel lbFaltas;
    private JTextField jtFaltas;
    private JLabel lbHorasExtras;
    private JTextField jtHorasExtras;
    private JButton bCalcular;
    private ControladorCalculaSalario ctrlCalcular;

    public TelaCalculaSalario(ControladorCalculaSalario owner) {
        super("Informações");
        this.ctrlCalcular = owner;

        init();
    }

    private void init() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        lbMatricula = new JLabel("Matrícula");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 5, 0, 0);
        container.add(lbMatricula, constraints);

        jtMatricula = new JTextField();
        jtMatricula.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 0;
        jtMatricula.setPreferredSize(new Dimension(60, 25));
        container.add(jtMatricula, constraints);

        lbFaltas = new JLabel("Quantidade de Faltas");
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(lbFaltas, constraints);

        jtFaltas = new JTextField();
        jtFaltas.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 1;
        jtFaltas.setPreferredSize(new Dimension(60, 25));
        container.add(jtFaltas, constraints);

        lbHorasExtras = new JLabel("Quantidade de Horas Extras");
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(lbHorasExtras, constraints);

        jtHorasExtras = new JTextField();
        jtHorasExtras.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 2;
        jtHorasExtras.setPreferredSize(new Dimension(60, 25));
        container.add(jtHorasExtras, constraints);
        

        bCalcular = new JButton("Calcular");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 20;
        bCalcular.setPreferredSize(new Dimension(100, 25));
        constraints.insets = new Insets(60, 0, 0, 0);
        container.add(bCalcular, constraints);

        setSize(400, 300);        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }

}