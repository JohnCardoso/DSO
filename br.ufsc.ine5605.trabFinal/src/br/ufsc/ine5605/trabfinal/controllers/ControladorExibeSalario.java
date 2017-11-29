package br.ufsc.ine5605.trabfinal.controllers;

import br.ufsc.ine5605.trabfinal.display.TelaExibeSalario;

public class ControladorExibeSalario {
    private TelaExibeSalario telaExibeSal;
    private static ControladorExibeSalario ctrlExibeSal;
    
    public ControladorExibeSalario() throws Exception {
         super();
         telaExibeSal = new TelaExibeSalario(this);
        
    }
    
    public void inicia(String matricula, String faltas, String horasExtras) throws Exception {
    	TelaExibeSalario telaExibeSal1 = new TelaExibeSalario(this);
    	telaExibeSal1.passaValores(matricula, faltas, horasExtras);
        telaExibeSal1.setVisible(true);
        

    }
    public static ControladorExibeSalario getCtrlExibeSal() throws Exception {
        if(ctrlExibeSal == null) {
            ctrlExibeSal = new ControladorExibeSalario();
        }
        return ctrlExibeSal;
    }
    
    public static void setctrlExibeSal(ControladorExibeSalario ctrlExibeSal) {
        ControladorExibeSalario.ctrlExibeSal = ctrlExibeSal;
    }
    
    public void telaExibeSal() throws Exception {
        telaExibeSal.setVisible(true);
        
    }
    
} 