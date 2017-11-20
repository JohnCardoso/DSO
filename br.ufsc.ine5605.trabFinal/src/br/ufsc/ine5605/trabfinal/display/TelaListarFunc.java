package br.ufsc.ine5605.trabfinal.display;

import br.ufsc.ine5605.trabfinal.controllers.ControladorFuncionario;
import br.ufsc.ine5605.trabfinal.objects.Funcionario;
import java.awt.Container;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaListarFunc extends JFrame {
    
    private ControladorFuncionario ctrlFunc;
    
    private String[] mostraColunas = { "Matrícula", "Nome", "Salário Bruto", "Nº Dependentes", "V.T.", "Insalubridade", "Periculosidade" };
    private JTable mostraFuncionarios;
    private DefaultTableModel tabelaMF;
    
    public TelaListarFunc (ControladorFuncionario owner) {
        super("Listar Funcionários");
        
        this.ctrlFunc = owner;
        this.tabelaMF = new DefaultTableModel(mostraColunas, 0);
        init();
    }
    
    private void init() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());


        atualDados();

        container.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 12, 835, 565);
        container.add(scrollPane);

        mostraFuncionarios = new JTable();
        mostraFuncionarios.setModel(tabelaMF);
        scrollPane.setViewportView(mostraFuncionarios);


        setSize(865, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void atualDados() {
        tabelaMF.setNumRows(0);
        for (Funcionario f : ctrlFunc.getFDAO().getList()) {
            String nome = f.getNome();
            String matricula = f.getMatricula();
            String salario = f.getSalario();
            String dependente = f.getDependente();
            String vt = String.valueOf(f.isVt());
            String insalubridade = String.valueOf(f.isInsalubridade());
            String periculosidade = String.valueOf(f.isPericulosidade());
            
            Object[] row = { matricula, nome, salario, dependente, vt, insalubridade, periculosidade };
            tabelaMF.addRow(row);
        }
    }
}
