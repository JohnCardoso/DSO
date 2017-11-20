package br.ufsc.ine5605.trabfinal.controllers;

import br.ufsc.ine5605.trabfinal.display.TelaExibeSalario;
import br.ufsc.ine5605.trabfinal.persistence.FuncDAO;
import br.ufsc.ine5605.trabfinal.controllers.ControladorFuncionario;

public class ControladorCalculo extends Controlador {
    
    private TelaExibeSalario telaExibeSal;
    private static ControladorCalculo ctrlCalculo;
    
    public ControladorCalculo() {
        super();
        telaExibeSal = new TelaExibeSalario(this);

    }

    @Override
    public void inicia() {
        telaExibeSal.setVisible(true);
    }

    public static ControladorCalculo getCtrlCalculo() {
        if (ctrlCalculo == null) {
            ctrlCalculo = new ControladorCalculo();
        }
        return ctrlCalculo;
    }

    public static void setCtrlCalculo(ControladorCalculo ctrlCalculo) {
        ControladorCalculo.ctrlCalculo = ctrlCalculo;
    }
    
  /*  public double calculaINSS(Object Funcionario) {
	double inss;
	double valSal = Double.parseDouble();
	if(valSal >= 937 && valSal <= 1659.38) {
		inss = valSal * 0.08;
	} else if( valSal >= 1659.39 && valSal <= 2765.66) {
		inss = valSal * 0.09;
	} else if (valSal >= 2765.67 && valSal <= 5531.31) {
		inss = valSal * 0.11;
	} else {
		inss = 608.45;
	}
	return inss;
}

public double calculaIRRF(String SalBruto) {
	double irrf;
	double valSal = Double.parseDouble(FuncDAO.getFDAO().getSalario().replace(",","."));
	if (valSal >= 937 && valSal <= 1787.77) {
		irrf = 0;
	} else if (valSal >= 1787.78 && valSal <= 2678.29) {
		irrf = 134.08 - descNumDependentes;
	} else if (valSal >= 2679.30 && valSal <= 3572.43) {
		irrf = 335.03 - descNumDependentes;
	} else if ( valSal >= 3572.44 && valSal <= 4463.81) {
		irrf = 602.96 - descNumDependentes;
	} else {
		irrf = 826.15 - descNumDependentes;
	}
	return irrf;
}

public double validaDescIRRF(double irrf) {
	double descIrrf;
	if (irrf < 0) {
		descIrrf = 0;
	} else {
		descIrrf = irrf;
	}
	return descIrrf;
}


public double calculaSalario() {
	
    double salLiquido = valSal + (((valSal / 220) * horasExtras) * 1.5) + (valorInsalubridade * 0 ou 1) + (valorPericulosidade * 0 ou 1) - (descontoVT * 0 ou 1)
							- inss - descIrrf - ((valSal / 30) * numFaltas);
	return salLiquido;
     } */
}
