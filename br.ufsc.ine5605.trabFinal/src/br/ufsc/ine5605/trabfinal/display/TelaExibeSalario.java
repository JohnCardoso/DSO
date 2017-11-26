package br.ufsc.ine5605.trabfinal.display;

import br.ufsc.ine5605.trabfinal.controllers.ControladorExibeSalario;
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
    private ControladorExibeSalario ctrlExibeSal;
    private ControladorFuncionario ctrlFunc;
    private JLabel lbvalor1;
    private JLabel lbvalor2;
    private String matricula;
    private String falta;
    private String horas;

    public TelaExibeSalario(ControladorExibeSalario owner) {
        super("Holerite Digital");
        this.ctrlExibeSal = owner;

            init();
    }

    public void teste(String teste) {
        this.matricula = teste;
    }

    private void init() {

		// TODO
		// Recebe valores de calculo (salario, faltas horas extras
		// chama controlador de funcionario
		// calcula
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        lbvalor1 = new JLabel("O salário líquido vai ser: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 5, 0, 0);
        container.add(lbvalor1, constraints);

        lbvalor2 = new JLabel();
        try {
            lbvalor2.setText(String.valueOf(ctrlFunc.descontoDependentes(matricula)));
            container.add(lbvalor2, constraints);
        } catch (Exception exception) {
            System.out.println(" saiu exception");
        }
        lbvalor2.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 0;
        lbvalor2.setPreferredSize(new Dimension(60, 25));
        container.add(lbvalor2, constraints);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void exibeResultado() throws Exception {
        try {
            lbvalor2.setText(String.valueOf(ctrlFunc.recuperaSalario(matricula)));
        } catch (Exception exception) {
            System.out.println(String.valueOf(ctrlFunc.recuperaSalario(matricula)));
        }

    }

}