package br.ufsc.ine5605.trabfinal.display;

import br.ufsc.ine5605.trabfinal.controllers.ControladorFuncionario;
import javax.swing.*;
import java.awt.*;

public class TelaCadastraFunc extends JFrame {
    private ControladorFuncionario ctrlFuncionario;
    
    private JLabel lbNome;
    private JTextField tfNome;
    private JLabel lbMatri;
    private JTextField tfMatri;
    private JLabel lbSalario;
    private JTextField tfSalario;
    private JLabel lbDepentente;
    private JTextField tfDependente;
    private JLabel lbInformacoes; // Escrever Informações Adicionais
    private JCheckBox cbVT;
    private JCheckBox cbInsalubridade;
    private JCheckBox cbPericulosidade;
    private JButton bCadastrarFuncionario;

    public TelaCadastraFunc(ControladorFuncionario owner) {
        super("Cadastrar Funcionário");

        this.ctrlFuncionario = owner;
        init();
    }
    
    private void init() {
        
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        lbNome = new JLabel("Nome");
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(lbNome, constraints);

        tfNome = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 0;
        tfNome.setPreferredSize(new Dimension(200, 25));
        container.add(tfNome, constraints);

        lbMatri = new JLabel("Matrícula");
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(lbMatri, constraints);

        tfMatri = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 1;
        tfMatri.setPreferredSize(new Dimension(200, 25));
        container.add(tfMatri, constraints);

        lbSalario = new JLabel("Salário bruto ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(lbSalario, constraints);

        tfSalario = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 2;
        tfSalario.setPreferredSize(new Dimension(200, 25));
        container.add(tfSalario, constraints);

        lbDepentente = new JLabel("Nº. de dependentes ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(lbDepentente, constraints);

        tfDependente = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 3;
        tfDependente.setPreferredSize(new Dimension(200, 25));
        container.add(tfDependente, constraints);

        lbInformacoes = new JLabel("<html><u>Informações adicionais</u></html>");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 20;
        constraints.insets = new Insets(20, 0, 20, 0);
        container.add(lbInformacoes, constraints);

        cbVT = new JCheckBox(" VT");
        constraints.gridx = 0;
        constraints.gridy = 5;
        cbVT.setPreferredSize(new Dimension(300, 25));
        constraints.insets = new Insets(0, 0, 0, 0);
        container.add(cbVT, constraints);

        cbInsalubridade = new JCheckBox(" Insalubridade");
        constraints.gridx = 0;
        constraints.gridy = 6;
        cbInsalubridade.setPreferredSize(new Dimension(300, 25));
        container.add(cbInsalubridade, constraints);

        cbPericulosidade = new JCheckBox(" Periculosidade");
        constraints.gridx = 0;
        constraints.gridy = 7;
        cbPericulosidade.setPreferredSize(new Dimension(300, 25));
        container.add(cbPericulosidade, constraints);

        bCadastrarFuncionario = new JButton("Cadastrar");
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.insets = new Insets(20, 0, 20, 0);
        container.add(bCadastrarFuncionario, constraints);
        
        setSize(500, 500);        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

    }

}