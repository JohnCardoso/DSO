 package br.ufsc.ine5605.trabfinal.display;

import javax.swing.*;
import java.awt.*;
import br.ufsc.ine5605.trabfinal.controllers.ControladorCalculaSalario;
import br.ufsc.ine5605.trabfinal.controllers.ControladorFuncionario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaCalculaSalario extends JFrame {
    private ControladorFuncionario ctrlFuncionario;
    private ControladorCalculaSalario ctrlCalcular;
    private GerenciadorBotoesCalculo gerBotCalc;    

    private JLabel lbMatricula;
    private JTextField tfMatricula;
    private JLabel lbFaltas;
    private JTextField tfFaltas;
    private JLabel lbHorasExtras;
    private JTextField tfHorasExtras;
    private JButton bCalcular;


    public TelaCalculaSalario(ControladorFuncionario owner) {
        super("Informações");
        this.ctrlFuncionario = owner;
        this.gerBotCalc = new GerenciadorBotoesCalculo();

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

        tfMatricula = new JTextField();
        tfMatricula.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 0;
        tfMatricula.setPreferredSize(new Dimension(60, 25));
        container.add(tfMatricula, constraints);

        lbFaltas = new JLabel("Quantidade de Faltas");
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(lbFaltas, constraints);

        tfFaltas = new JTextField();
        tfFaltas.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 1;
        tfFaltas.setPreferredSize(new Dimension(60, 25));
        container.add(tfFaltas, constraints);

        lbHorasExtras = new JLabel("Quantidade de Horas Extras");
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(lbHorasExtras, constraints);

        tfHorasExtras = new JTextField();
        tfHorasExtras.setHorizontalAlignment(JTextField.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 2;
        tfHorasExtras.setPreferredSize(new Dimension(60, 25));
        container.add(tfHorasExtras, constraints);
        

        bCalcular = new JButton("Calcular");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 20;
        bCalcular.setPreferredSize(new Dimension(100, 25));
        constraints.insets = new Insets(60, 0, 0, 0);
        container.add(bCalcular, constraints);
        bCalcular.setActionCommand("calculando");
        bCalcular.addActionListener(gerBotCalc);

        setSize(400, 300);        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }
    
    private class GerenciadorBotoesCalculo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("calculando")) {
                 try {
                    ctrlFuncionario.recuperaDados(tfMatricula.getText(), tfFaltas.getText(), 
                            tfHorasExtras.getText().replace(",","."));
                    JOptionPane.showMessageDialog(null, "PASSOU CARALHO");
                  //  ctrlPrinc.getCtrlExibe().inicia();
                } catch (NullPointerException | IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    Logger.getLogger(TelaCalculaSalario.class.getName()).log(Level.SEVERE, null, ex.getMessage());
                }      
            }
        }
    } 

} 