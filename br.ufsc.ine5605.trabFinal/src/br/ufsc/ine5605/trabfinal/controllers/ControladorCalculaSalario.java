package br.ufsc.ine5605.trabfinal.controllers;

//import br.ufsc.ine5605.trabfinal.display.TelaCalculaSalario;
import br.ufsc.ine5605.trabfinal.objects.Funcionario;
import br.ufsc.ine5605.trabfinal.persistence.FuncDAO;

public class ControladorCalculaSalario extends Controlador {
  //  private TelaCalculaSalario telaCalcSal;
    private static ControladorCalculaSalario ctrlCalcular;
    private ControladorFuncionario ctrlFunc;

    public ControladorCalculaSalario() {
        super();
        //telaCalcSal = new TelaCalculaSalario(this);
		
    }

    @Override
    public void inicia() {
//        telaCalcSal.setVisible(true);
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

  /*  public void recebendoDados(String matricula, String faltas, String horasExtras) throws Exception {
        if(ctrlFunc.validaFuncionarioExiste(matricula)) {
            if(faltas.matches("[0-9]")) {
                if(horasExtras.matches("^\\d+(\\.\\d\\d)*$")) {
                  return "text";
                } else {
                    throw new IllegalArgumentException("Horas Extras obrigatóriamente devem ser dígitos");
                }
            } else {
                throw new IllegalArgumentException("Número de faltas devem ser dígitos \nCaso não possua faltas digite 0");
            }
        } else {
            throw new IllegalArgumentException ("Funcionário Inexistente, digite outra Matrícula");
        }
    } */
    
    

}