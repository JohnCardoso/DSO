package br.ufsc.ine5605.trabfinal.display;

import br.ufsc.ine5605.trabfinal.controllers.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaPrincipal extends JFrame {
    private ControladorPrincipal ctrlPrinc;
    private GerenciadorBotoesGeral gerBotGeral;
    
    private JButton bCalculo;
    private JButton bCadastro;
    private JButton bExibeFunc;

    public TelaPrincipal(ControladorPrincipal owner) {
        super("Calculadora de Salário");
        
        this.ctrlPrinc = owner;
        this.gerBotGeral = new GerenciadorBotoesGeral();
        init();
    }
    
    private void init() {
        
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        

        bCalculo = new JButton("Calcular Salário");
        bCalculo.setActionCommand("ControladorSalario");
        bCalculo.addActionListener(gerBotGeral);
        constraints.gridx = 0;
        constraints.gridy = 0;
        bCalculo.setPreferredSize(new Dimension(200, 25));
        constraints.insets = new Insets(5, 0, 5, 0);
        container.add(bCalculo, constraints);
        
        
        bCadastro = new JButton("Cadastrar funcionário");
        bCadastro.setActionCommand("ControladorFuncionario");
        bCadastro.addActionListener(gerBotGeral);
        constraints.gridx = 0;
        constraints.gridy = 1;
        bCadastro.setPreferredSize(new Dimension(200, 25));
        container.add(bCadastro, constraints);
        
        
        bExibeFunc = new JButton("Listar Funcionários");
        bExibeFunc.setActionCommand("ExibirFuncionario");
        bExibeFunc.addActionListener(gerBotGeral);
        constraints.gridx = 0;
        constraints.gridy = 2;
        bExibeFunc.setPreferredSize(new Dimension(200, 25));
        container.add(bExibeFunc, constraints);
        
        setSize(400, 200);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
    
    private class GerenciadorBotoesGeral implements ActionListener {
		
        @Override
        public void actionPerformed(ActionEvent e) {
            String opcao = e.getActionCommand();
            switch(opcao){
                case "ControladorFuncionario":
                    ctrlPrinc.getCtrlFuncionario().inicia();
                    break;
               case "ControladorSalario":
                    ctrlPrinc.getCtrlFuncionario().telaCalcSal();
                    break;
                 case "ExibirFuncionario":
                    ctrlPrinc.getCtrlFuncionario().telaListarFunc();
                    break;
                default:
                    break; 
            }
        }
    	
    }
}