package br.ufsc.ine5605.trabfinal.controllers;

import br.ufsc.ine5605.trabfinal.display.TelaCadastraFunc;
import br.ufsc.ine5605.trabfinal.objects.Funcionario;

public class ControladorFuncionario extends Controlador {
    private TelaCadastraFunc telaCadFunc;
    private static ControladorFuncionario ctrlFuncionario;
    
    public ControladorFuncionario() {
        super();
        telaCadFunc = new TelaCadastraFunc(this);
		
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
    
    public void validandoDados (String nome, String matricula, String salario, String dependentes) {
        Funcionario func = new Funcionario(nome, matricula, salario, dependentes);
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
}
