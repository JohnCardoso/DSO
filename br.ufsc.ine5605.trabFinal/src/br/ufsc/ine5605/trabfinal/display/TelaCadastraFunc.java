package br.ufsc.ine5605.trabfinal.display;

import br.ufsc.ine5605.trabfinal.controllers.ControladorFuncionario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TelaCadastraFunc extends JFrame {
    private ControladorFuncionario ctrlFuncionario;
    private GerenciadorBotoesCadastro gerBotCad;
    private GerenciadorCheckboxCadastro gerCheckCad;
    
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
    private JButton bVoltar;

    public TelaCadastraFunc(ControladorFuncionario owner) {
        super("Cadastrar Funcionário");
        this.ctrlFuncionario = owner;
        this.gerBotCad = new GerenciadorBotoesCadastro();
        this.gerCheckCad = new GerenciadorCheckboxCadastro();
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
        tfNome.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 0;
        tfNome.setPreferredSize(new Dimension(200, 25));
        container.add(tfNome, constraints);

        lbMatri = new JLabel("Matrícula");
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(lbMatri, constraints);

        tfMatri = new JTextField();
        tfMatri.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 1;
        tfMatri.setPreferredSize(new Dimension(200, 25));
        container.add(tfMatri, constraints);

        lbSalario = new JLabel("Salário bruto ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(lbSalario, constraints);

        tfSalario = new JTextField();
        tfSalario.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 2;
        tfSalario.setPreferredSize(new Dimension(200, 25));
        container.add(tfSalario, constraints);

        lbDepentente = new JLabel("Nº. de dependentes ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(lbDepentente, constraints);

        tfDependente = new JTextField();
        tfDependente.setHorizontalAlignment(JTextField.CENTER);
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
        cbVT.addItemListener(gerCheckCad);

        cbInsalubridade = new JCheckBox(" Insalubridade");
        constraints.gridx = 0;
        constraints.gridy = 6;
        cbInsalubridade.setPreferredSize(new Dimension(300, 25));
        container.add(cbInsalubridade, constraints);
        cbInsalubridade.addItemListener(gerCheckCad);

        cbPericulosidade = new JCheckBox(" Periculosidade");
        constraints.gridx = 0;
        constraints.gridy = 7;
        cbPericulosidade.setPreferredSize(new Dimension(300, 25));
        container.add(cbPericulosidade, constraints);
        cbPericulosidade.addItemListener(gerCheckCad);

        bCadastrarFuncionario = new JButton("Cadastrar");
        constraints.gridx = 0;
        constraints.gridy = 8;
        bCadastrarFuncionario.setPreferredSize(new Dimension(150, 25));
        constraints.insets = new Insets(20, 0, 0, 0);
        container.add(bCadastrarFuncionario, constraints);
        bCadastrarFuncionario.setActionCommand("registraFunc");
        bCadastrarFuncionario.addActionListener(gerBotCad);
        
        bVoltar = new JButton("Voltar");
        constraints.gridx = 0;
        constraints.gridy = 9;
        bVoltar.setPreferredSize(new Dimension(150, 25));
        constraints.insets = new Insets(5, 0, 0, 0);
        container.add(bVoltar, constraints);
        bVoltar.setActionCommand("voltarTela");
        bVoltar.addActionListener(gerBotCad);
        
        setSize(500, 500);        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

    }
    
    private class GerenciadorBotoesCadastro implements ActionListener {
		
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("voltarTela")) {               
                dispose();
            }
            if (e.getActionCommand().equals("registraFunc")) {
                 try {
                    ctrlFuncionario.validandoDados(tfNome.getText(), tfMatri.getText(), 
                            tfSalario.getText().replace(",", "."), tfDependente.getText(), cbVT.isSelected(), cbInsalubridade.isSelected(), cbPericulosidade.isSelected());
                    JOptionPane.showMessageDialog(null, "Registro realizado com sucesso");
                   
                } catch (IllegalArgumentException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());                    
                }
            }
        }
    	
    }
    
    private class GerenciadorCheckboxCadastro implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if(cbVT.isSelected()) {
            }
            if(cbInsalubridade.isSelected()) {
            }
            if(cbPericulosidade.isSelected()) {
            }
        }
        
    }

}