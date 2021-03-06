package br.ufsc.ine5605.trabfinal.controllers;

import br.ufsc.ine5605.trabfinal.display.TelaCadastraFunc;
import br.ufsc.ine5605.trabfinal.display.TelaCalculaSalario;
import br.ufsc.ine5605.trabfinal.display.TelaListarFunc;
import br.ufsc.ine5605.trabfinal.interfaces.ICrud;
import br.ufsc.ine5605.trabfinal.objects.Funcionario;
import br.ufsc.ine5605.trabfinal.persistence.FuncDAO;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ControladorFuncionario extends Controlador implements ICrud {
    private TelaCadastraFunc telaCadFunc;
    private TelaListarFunc telaListarFunc;
    private TelaCalculaSalario telaCalcSal;
    
    private static ControladorFuncionario ctrlFuncionario;
    
    public ControladorFuncionario() throws IllegalArgumentException {
        super();
        telaCadFunc = new TelaCadastraFunc(this);
        telaListarFunc = new TelaListarFunc(this);
        telaCalcSal = new TelaCalculaSalario(this);
	
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
                if (listarFuncionarios().get(i).getMatricula().equals(numeroMat)) {
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
                            throw new IllegalArgumentException("Nº de dependentes inválido,\ncaso não possua dependente coloque 0");
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
        throw new IllegalArgumentException("Matricula inválida");
        
    }
    
    
    public void telaListarFunc() {
        telaListarFunc.setVisible(true);
    }
    
    public void telaCalcSal() {
        telaCalcSal.setVisible(true);
    }
    
    public double recuperaSalario(String matricula) throws Exception {
        
        Funcionario func = buscarPelaMatricula(matricula);
        if (func != null) {          
            if (!FuncDAO.getFDAO().getList().isEmpty()) {
                return Double.parseDouble(func.getSalario().replace(",","."));
                
            } else {
                throw new Exception("Não existem Funcionários cadastrados.");
            }       
        } else {
            throw new Exception ("Funcionário inexistente, digite outra matrícula.");
        }
    }
    
    public double descontoDependentes(String matricula) {
        return (Double.parseDouble(buscarPelaMatricula(matricula).getDependente()) * 189.59);
    }
    
    
    
    public int descontaVT(String matricula) {
        int descVT = 0;
        if(buscarPelaMatricula(matricula).isVt()) {
            descVT = 1;
        }
        return descVT;
    }
    
   public int bonificaInsalubridade(String matricula) {
       int acrescIns = 0;
       if(buscarPelaMatricula(matricula).isInsalubridade()) {
           acrescIns = 1;
       } else {
       }
       return acrescIns;
   }
   
   public int bonificaPericulosidade(String matricula) {
       int acrescPeric = 0;
       if(buscarPelaMatricula(matricula).isPericulosidade()) {
           acrescPeric = 1;
       }
       return acrescPeric;
   }
   
    public String validaFaltas(String faltas) throws IllegalArgumentException {
        if(faltas != null) {
            if(faltas.matches("[0-9]{1,2}")) {
                return faltas;
            } else {
                throw new IllegalArgumentException ("Número de faltas devem ser dígitos \nCaso não possua faltas digite 0");
            }
        } else {
            throw new IllegalArgumentException ("Cédula nula");
        }
   }
   
    public String validaHoras (String horas) throws IllegalArgumentException {
        if(horas != null) {
            if(horas.matches("^\\d+(\\.\\d*)*$")) {
                return horas;
            } else {
                throw new IllegalArgumentException("Horas Extras obrigatoriamente devem ser dígitos");
            }
        } else {
            throw new IllegalArgumentException ("Cédula nula");
        }
   }

    public String calculaSalario(String matricula, String faltas, String horasExtras) throws Exception {
        
        final double salBruto = this.recuperaSalario(matricula);
        
        double falta = Double.parseDouble(this.validaFaltas(faltas));
        double horas = Double.parseDouble(this.validaHoras(horasExtras));
        double calcSal = salBruto + (((salBruto/220) * horas) * 1.5) - (((salBruto) / 30) * falta) + (((salBruto / 220) * this.bonificaInsalubridade(matricula)) * 1.2)
                    + (((salBruto / 220) * this.bonificaPericulosidade(matricula)) * 1.3) - (((salBruto / 220) * this.descontaVT(matricula)) * 0.06) -
                    this.calculaINSS(matricula) - this.validaDescIRRF(matricula);
        if(calcSal < 0.00) {
            calcSal = 00.00;
            return new DecimalFormat("0.00").format(calcSal);
        }
        return new DecimalFormat("#.00").format(calcSal);
    } 
    
    
    public double calculaINSS(final String matricula) throws Exception {
        final double valSal = this.recuperaSalario(matricula);
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
    
    public String salvaMatricula(String matricula) {
        return matricula;
    }
    
    public String salvaFalta(String falta) {
        return falta;
    }
    
    public String salvaHoras(String hora) {
        return hora;
    }
    
    public double calculaIRRF(final String matricula) throws Exception {
        
	double irrf;
	final double valSal = this.recuperaSalario(matricula);
	if (valSal >= 937 && valSal <= 1787.77) {
		irrf = 0;
	} else if (valSal >= 1787.78 && valSal <= 2678.29) {
		irrf = 134.08 - this.descontoDependentes(buscarPelaMatricula(matricula).getMatricula());
	} else if (valSal >= 2679.30 && valSal <= 3572.43) {
		irrf = 335.03 - this.descontoDependentes(buscarPelaMatricula(matricula).getMatricula());
	} else if ( valSal >= 3572.44 && valSal <= 4463.81) {
		irrf = 602.96 - this.descontoDependentes(buscarPelaMatricula(matricula).getMatricula());
	} else {
		irrf = 826.15 - this.descontoDependentes(buscarPelaMatricula(matricula).getMatricula());
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