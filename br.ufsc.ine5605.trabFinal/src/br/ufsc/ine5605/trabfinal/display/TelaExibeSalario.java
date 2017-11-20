/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabfinal.display;

import br.ufsc.ine5605.trabfinal.controllers.ControladorCalculo;
import javax.swing.JFrame;

/**
 *
 * @author Jonathan
 */
public class TelaExibeSalario extends JFrame {
    private ControladorCalculo ctrlCalculo;
    
    public TelaExibeSalario (ControladorCalculo owner) {
        super("Holerite");
        this.ctrlCalculo = owner;
    }
}
