package br.ufsc.ine5605.trabfinal.controllers;

import br.ufsc.ine5605.trabfinal.display.TelaPrincipal;

public class ControladorPrincipal extends Controlador {
    private TelaPrincipal telaPrinc;
    private static ControladorPrincipal ctrlPrincipal;
    
    public ControladorPrincipal() {
        super();
        telaPrinc = new TelaPrincipal(this);
    }

    // Override method

    @Override
    public void inicia() {
        telaPrinc.setVisible(true);
    }

    // Getters & Setters

    public static ControladorPrincipal getCtrlPrincipal() {
        if (ctrlPrincipal == null) {
            ctrlPrincipal = new ControladorPrincipal();
        }
        return ctrlPrincipal;
    }

    public static void setCtrlPrincipal(ControladorPrincipal ctrlPrincipal) {
        ControladorPrincipal.ctrlPrincipal = ctrlPrincipal;
    }
    
    public ControladorFuncionario getCtrlFuncionario() {
        return ControladorFuncionario.getCtrlFuncionario();
    }
}
