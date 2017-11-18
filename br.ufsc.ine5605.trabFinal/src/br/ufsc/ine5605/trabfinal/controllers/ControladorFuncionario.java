package br.ufsc.ine5605.trabfinal.controllers;

import br.ufsc.ine5605.trabfinal.display.TelaCadastraFunc;
import br.ufsc.ine5605.trabfinal.display.TelaListarFunc;
import br.ufsc.ine5605.trabfinal.objects.Funcionario;
import br.ufsc.ine5605.trabfinal.persistence.FuncDAO;
import java.util.ArrayList;

public class ControladorFuncionario extends Controlador {
    private TelaCadastraFunc telaCadFunc;
    private TelaListarFunc telaListarFunc;
    private static ControladorFuncionario ctrlFuncionario;
    
    public ControladorFuncionario() {
        super();
        telaCadFunc = new TelaCadastraFunc(this);
        telaListarFunc = new TelaListarFunc(this);
		
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
    
    public int validandoVT (boolean vt) {
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
    }
    
    //Métodos validadores
    
    public boolean validarMatricula (String numeroMat) {
       boolean validMat = false;
       if(numeroMat.matches("\\d{6}") && !numeroMat.equals("000000")) {
           validMat = true;
       }
       return validMat;
    }
    
    public boolean validarNome (String nm) {
        boolean validNm = false;
        
        if (nm.length() >= 3) {
            for (int i = 0; i <nm.length(); i++) {
                if(Character.isAlphabetic(nm.charAt(i)) || Character.isWhitespace(nm.charAt(i))) {
                    validNm = true;
                }                
            }
        }        
        return validNm;
    }
    
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

    private void cadastrar(Object funcionario) throws IllegalArgumentException {
        Funcionario func = (Funcionario) funcionario;
        if (!func.getNome().equals("") && !func.getMatricula().equals("") && func != null) {
            if (!FuncDAO.getFDAO().getList().isEmpty()) {
                if (!validaFuncionarioExiste(func.getMatricula())) {
                    FuncDAO.getFDAO().insert(func);
                    telaListarFunc.atualDados();
                } else {
                    throw new IllegalArgumentException("Funcionario existente");
                }
            } else {
                FuncDAO.getFDAO().insert(func);
                telaListarFunc.atualDados();
            }
        } else {
            throw new IllegalArgumentException("Verifique o preenchimento");
        }
    }
    
    public void telaListarFunc() {
        telaListarFunc.setVisible(true);
    }
}
