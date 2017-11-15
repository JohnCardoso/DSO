package br.ufsc.ine5605.trabfinal.controllers;

import br.ufsc.ine5605.trabfinal.display.TelaCalculaSalario;

public class ControladorCalculaSalario extends Controlador {
    private TelaCalculaSalario telaCalcSal;
    private static ControladorCalculaSalario ctrlCalcular;

    public ControladorCalculaSalario() {
        super();
        telaCalcSal = new TelaCalculaSalario(this);
		
    }

    @Override
    public void inicia() {
        telaCalcSal.setVisible(true);
    }

    public static ControladorCalculaSalario getCtrlCalcular() {
        if (ctrlCalcular == null) {
            ctrlCalcular = new ControladorCalculaSalario();
        }
        return ctrlCalcular;
    }

    public static void setCtrlCalcular(ControladorCalculaSalario ctrlCalcular) {
        ControladorCalculaSalario.ctrlCalcular = ctrlCalcular;
    }   

}