package br.ufsc.ine5605.trabfinal.display;

import br.ufsc.ine5605.trabfinal.controllers.ControladorCalculo;
import javax.swing.JFrame;

public class TelaExibeSalario extends JFrame {
    private ControladorCalculo ctrlCalculo;
    
    public TelaExibeSalario (ControladorCalculo owner) {
        super("Holerite");
        this.ctrlCalculo = owner;
    }
}
