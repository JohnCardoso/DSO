package br.ufsc.ine5605.trabfinal.controllers;

import br.ufsc.ine5605.trabfinal.display.TelaCadastraFunc;
import br.ufsc.ine5605.trabfinal.display.TelaCalculaSalario;
import br.ufsc.ine5605.trabfinal.display.TelaExibeSalario;
import br.ufsc.ine5605.trabfinal.display.TelaListarFunc;
import br.ufsc.ine5605.trabfinal.interfaces.ICrud;
import br.ufsc.ine5605.trabfinal.objects.Funcionario;
import br.ufsc.ine5605.trabfinal.persistence.FuncDAO;
import java.util.ArrayList;

public class ControladorFuncionario extends Controlador implements ICrud {
    private TelaCadastraFunc telaCadFunc;
    private TelaListarFunc telaListarFunc;
    private TelaCalculaSalario telaCalcSal;
    private TelaExibeSalario telaExibeSal;
    private static ControladorFuncionario ctrlFuncionario;
    
    public ControladorFuncionario() {
        super();
        telaCadFunc = new TelaCadastraFunc(this);
        telaListarFunc = new TelaListarFunc(this);
        telaCalcSal = new TelaCalculaSalario(this);
        telaExibeSal = new TelaExibeSalario(this);
	
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

    @Override
    public void cadastrar(Object funcionario) throws IllegalArgumentException {
        Funcionario func = (Funcionario) funcionario;
        double verSal = Double.parseDouble(func.getSalario());
        if (func.getNome().matches("[\\w+\\sà-ùÀ-Ù]{3,}")) {
            if (func.getMatricula().matches("[0-9]{6,6}") && func != null) {
                if (func.getSalario().matches("^\\d+(\\.\\d\\d)*$")) {
                    if(verSal >= 937) {
                        if(func.getDependente().matches("[0-9]{1,}")) {
                            if (!FuncDAO.getFDAO().getList().isEmpty()) {
                                if (!validaFuncionarioExiste(func.getMatricula())) {
                                    FuncDAO.getFDAO().insert(func);
                                    telaListarFunc.atualDados();
                                } else {
                                    throw new IllegalArgumentException("Funcionario já cadastrado no sistema");
                                }
                            } else {
                                FuncDAO.getFDAO().insert(func);
                                telaListarFunc.atualDados();
                            }
                        } else {
                            throw new IllegalArgumentException(" Nº de dependentes inválido,\ncaso não possua dependente coloque 0");
                        }
                    } else {
                         throw new IllegalArgumentException("Salário inferior ao mínimo previsto: R$ 937,00");
                    }
                } else {
                    throw new IllegalArgumentException("Verifique o preenchimento Salário inválido \nSomente dígitos e duas casas decimais após a vírgula");
                } 
            } else {
                throw new IllegalArgumentException("Verifique o preenchimento Matrícula inválida \n6 caracteres exigido! Somente dígitos");
            }
        } else {
            throw new IllegalArgumentException("Verifique o preenchimento Nome inválido");      
        }
    }
    
    public Funcionario buscarPelaMatricula(String numeroMatricula) throws IllegalArgumentException {
        if (validaFuncionarioExiste(numeroMatricula)) {
            for (Funcionario f : FuncDAO.getFDAO().getList()) {
                if (f.getMatricula().equals(numeroMatricula)) {
                    return f;
                }
            }
        }
        return null;
    }
    
    
    
    public void telaListarFunc() {
        telaListarFunc.setVisible(true);
    }
    
    public void telaCalcSal() {
        telaCalcSal.setVisible(true);
    }
    
    public void telaExibeSal() {
        telaExibeSal.setVisible(true);
    }
    
    public double recuperaSalario(String salario) throws Exception {
        Funcionario func = buscarPelaMatricula(salario);
        if (func != null) {          
            if (!FuncDAO.getFDAO().getList().isEmpty()) {
                return Double.parseDouble(buscarPelaMatricula(salario).getSalario().replace(",","."));              
            } else {
                throw new Exception("Não existem Funcionários cadastrados.");
            }       
        } else {
            throw new Exception ("Funcionário inexistente, digite outra matrícula.");
        }
    }
    
    public double descontoDependentes(String func) {
        return (Double.parseDouble(buscarPelaMatricula(func).getDependente()) * 189.59);
    }
    
    
    
    public int descontaVT(String func) {
        int descVT = 0;
        if(buscarPelaMatricula(func).isVt()) {
            descVT = 1;
        }
        return descVT;
    }
    
   public int bonificaInsalubridade(String func) {
       int acrescIns = 0;
       if(buscarPelaMatricula(func).isInsalubridade()) {
           acrescIns = 1;
       } else {
       }
       return acrescIns;
   }
   
   public int bonificaPericulosidade(String func) {
       int acrescPeric = 0;
       if(buscarPelaMatricula(func).isPericulosidade()) {
           acrescPeric = 1;
       }
       return acrescPeric;
   }
   
    public String validaFaltas(String faltas) throws IllegalArgumentException {
        if(faltas != null) {
            if(faltas.matches("[0-9]")) {
                return faltas;
            } else {
                throw new IllegalArgumentException ("Número de faltas devem ser dígitos \nCaso não possua faltas digite 0");
            }
        } else {
            throw new IllegalArgumentException ("Cédula nula");
        }
   }
   
    public String validaHoras (String horas) throws Exception {
        if(horas != null) {
            if(horas.matches("^\\d+(\\.\\d*)*$")) {
                return horas;
            } else {
                throw new Exception("Horas Extras obrigatóriamente devem ser dígitos");
            }
        } else {
            throw new Exception ("Cédula nula");
        }
   }

    public double calculaSalario(String faltas, String horasExtras, String salario, String acrescIns, String acrescPeric, String descVT, String inss, String descIrrf) throws Exception {
        double calcSal = recuperaSalario(salario) + (((recuperaSalario(salario) / 220) * Double.parseDouble(validaHoras(horasExtras))) * 1.5) + 
                    (((recuperaSalario(salario) / 220) * bonificaInsalubridade(acrescIns)) * 1.2) +
                    (((recuperaSalario(salario) / 220) * bonificaPericulosidade(acrescPeric)) * 1.3) - 
                    (((recuperaSalario(salario) / 220) * descontaVT(descVT)) * 0.06) - calculaINSS(inss) -
                    validaDescIRRF(descIrrf) - (((recuperaSalario(salario)) / 30) * Double.parseDouble(validaFaltas(faltas)));
                return calcSal;                        
            
    } 
    
    
    public double calculaINSS(String salBruto) throws Exception {
        double valSal = recuperaSalario(salBruto);
        double inss;
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
    
    public double calculaIRRF(String func) throws Exception {
	double irrf;
	double valSal = recuperaSalario(func);
	if (valSal >= 937 && valSal <= 1787.77) {
		irrf = 0;
	} else if (valSal >= 1787.78 && valSal <= 2678.29) {
		irrf = 134.08 - descontoDependentes(buscarPelaMatricula(func).getDependente());
	} else if (valSal >= 2679.30 && valSal <= 3572.43) {
		irrf = 335.03 - descontoDependentes(buscarPelaMatricula(func).getDependente());
	} else if ( valSal >= 3572.44 && valSal <= 4463.81) {
		irrf = 602.96 - descontoDependentes(buscarPelaMatricula(func).getDependente());
	} else {
		irrf = 826.15 - descontoDependentes(buscarPelaMatricula(func).getDependente());
	}
	return irrf;
    }
    
    public double validaDescIRRF(String valIrrf) throws Exception {
	double descIrrf;
	if (calculaIRRF(valIrrf) < 0) {
		descIrrf = 0;
	} else {
		descIrrf = calculaIRRF(valIrrf);
	}
	return descIrrf;
    }
    
   
}