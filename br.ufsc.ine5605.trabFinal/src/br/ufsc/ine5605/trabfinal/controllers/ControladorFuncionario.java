package br.ufsc.ine5605.trabfinal.controllers;

import br.ufsc.ine5605.trabfinal.display.TelaCadastraFunc;

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
    
}
