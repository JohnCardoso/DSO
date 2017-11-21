package br.ufsc.ine5605.trabfinal.controllers;

import br.ufsc.ine5605.trabfinal.display.TelaCadastraFunc;
import br.ufsc.ine5605.trabfinal.display.TelaCalculaSalario;
import br.ufsc.ine5605.trabfinal.display.TelaListarFunc;
import br.ufsc.ine5605.trabfinal.interfaces.ICrud;
import br.ufsc.ine5605.trabfinal.objects.Funcionario;
import br.ufsc.ine5605.trabfinal.persistence.FuncDAO;
import java.util.ArrayList;

public class ControladorFuncionario extends Controlador implements ICrud {
    private TelaCadastraFunc telaCadFunc;
    private TelaListarFunc telaListarFunc;
    private TelaCalculaSalario telaCalcSal;
    private static ControladorFuncionario ctrlFuncionario;
    
    public ControladorFuncionario() {
        super();
        telaCadFunc = new TelaCadastraFunc(this);
        telaListarFunc = new TelaListarFunc(this);
	telaCalcSal = new TelaCalculaSalario(this);
    }

    @Override
    public void inicia() {
        telaCadFunc.setVisible(true);
    }
        
    public static ControladorFuncionario getCtrlFuncionario() {
        if (ctrlFuncionario == null) {
            ctrlFuncionario = new ControladorFuncionario();
        }
        return ctrlFuncionario;
    }

    public static void setCtrlFuncionario(ControladorFuncionario ctrlFuncionario) {
        ControladorFuncionario.ctrlFuncionario = ctrlFuncionario;
    }
    
    //catching data
    
    public void validandoDados (String nome, String matricula, String salario, String dependentes, boolean vt, boolean insalubridade, boolean periculosidade) {
        Funcionario func = new Funcionario(nome, matricula, salario, dependentes, vt, insalubridade, periculosidade);
        cadastrar(func);
    }
    
   /* public int validandoVT (boolean vt) {
        int veriVt = 1;
        if (vt == false) {
            veriVt = 0;
        }
        return veriVt;
    }
    
    public int validandoInsalubridade (boolean isl) {
        int veriInsa = 1;
        if ( isl == false) {
            veriInsa = 0;
        }
        return veriInsa;
    }
    
    public int validandoPericulosidade (boolean prc) {
        int veriPrc = 1;
        if (prc == false) {
            veriPrc = 0;
        }
        return veriPrc;
    }    */
    
    public ArrayList<Funcionario> listarFuncionarios() {
        return new ArrayList<Funcionario>(FuncDAO.getFDAO().getList());
    }
    
    public boolean validaFuncionarioExiste(String numeroMat) {
        if (!FuncDAO.getFDAO().getList().isEmpty()) {
            for (int i = 0; i < listarFuncionarios().size(); i++) {
                if (listarFuncionarios().get(i).getMatricula().equalsIgnoreCase(numeroMat)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public FuncDAO getFDAO() {
        return FuncDAO.getFDAO();

    }

    @Override
    public void cadastrar(Object funcionario) throws IllegalArgumentException {
        Funcionario func = (Funcionario) funcionario;
        double verSal = Double.parseDouble(func.getSalario());
        if (func.getNome().matches("[\\w+\\sà-ùÀ-Ù]{3,}")) {
            if (func.getMatricula().matches("[0-9]{6,6}") && func != null) {
                if (func.getSalario().matches("^\\d+(\\.\\d\\d)*$")) {
                    if(verSal >= 937) {
                        if(func.getDependente().matches("[0-9]{1,}")) {
                            if (!FuncDAO.getFDAO().getList().isEmpty()) {
                                if (!validaFuncionarioExiste(func.getMatricula())) {
                                    FuncDAO.getFDAO().insert(func);
                                    telaListarFunc.atualDados();
                                } else {
                                    throw new IllegalArgumentException("Funcionario já cadastrado no sistema");
                                }
                            } else {
                                FuncDAO.getFDAO().insert(func);
                                telaListarFunc.atualDados();
                            }
                        } else {
                            throw new IllegalArgumentException(" Nº de dependentes inválido,\ncaso não possua dependente coloque 0");
                        }
                    } else {
                         throw new IllegalArgumentException("Salário inferior ao mínimo previsto: R$ 937,00");
                    }
                } else {
                    throw new IllegalArgumentException("Verifique o preenchimento Salário inválido \nSomente dígitos e duas casas decimais após a vírgula");
                } 
            } else {
                throw new IllegalArgumentException("Verifique o preenchimento Matrícula inválida \n6 caracteres exigido! Somente dígitos");
            }
        } else {
            throw new IllegalArgumentException("Verifique o preenchimento Nome inválido");      
        }
    }
    
    public Funcionario buscarPelaMatricula(String numeroMatricula) {
        if (validaFuncionarioExiste(numeroMatricula)) {
            for (Funcionario f : FuncDAO.getFDAO().getList()) {
                if (f.getMatricula().equals(numeroMatricula)) {
                    return f;
                }
            }
        }
        return null;
    }
    
    public void recuperaDados(String numeroMatricula, String faltas, String horasExtras) throws Exception {
        Funcionario func = buscarPelaMatricula(numeroMatricula);
        if (func != null) {
            if(faltas.matches("[0-9]")) {
               if (horasExtras.matches("^\\d+(\\.\\d*)*$")) {
                    if (!FuncDAO.getFDAO().getList().isEmpty()) {
                        func.getSalario();
                        func.isVt();
                        func.isInsalubridade();
                        func.isPericulosidade();
                    } else {
                    throw new NullPointerException("Não existem Funcionários cadastrados.");
                    }
               } else {
                   throw new IllegalArgumentException("Valor de Horas Extras inválidas,\nsomente dígitos, ponto ou vírgula");
               }
            } else {
                throw new IllegalArgumentException ("Número de faltas inválidas Somente dígitos");
            }
        } else {
            throw new IllegalArgumentException ("Funcionário inexistente, digite outra matrícula.");
        }
    }
    
    public void telaListarFunc() {
        telaListarFunc.setVisible(true);
    }
    
    public void telaCalcSal() {
        telaCalcSal.setVisible(true);
    }
}
