package br.ufsc.ine5605.trabfinal.display;

import br.ufsc.ine5605.trabfinal.controllers.ControladorFuncionario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.lang.Exception;

public class TelaExibeSalario extends JFrame {

    private static final long serialVersionUID = -326516504164144527L;
    private ControladorFuncionario ctrlFuncionario;
    private JLabel lbAprovado20181;
    private JLabel chupa;
    private String matricula;
    
    
    public TelaExibeSalario (ControladorFuncionario owner) {
        super("Holerite");
        this.ctrlFuncionario = owner;
        
        init();
    }
    
    public void teste(String teste) {
        this.matricula = teste;
    }
    
    private void init() {
        
        //TODO 
        // Recebe valores de calculo (salario, faltas horas extras
        // chama controlador de funcionario
        // calcula
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        lbAprovado20181 = new JLabel("Acabou carai");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 5, 0, 0);
        container.add(lbAprovado20181, constraints);

        chupa = new JLabel();
        try {
        lbAprovado20181.setText(String.valueOf(ctrlFuncionario.calculaSalario("5", "5", "5")));
        } catch (Exception exception) {
            System.out.println(" saiu execption");
        }
        chupa.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 0;
        chupa.setPreferredSize(new Dimension(60, 25));
        container.add(chupa, constraints);



        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
