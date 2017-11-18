package br.ufsc.ine5605.trabfinal.objects;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable {

    private static final long serialVersionUID = 5881105998438722668L;
    
    private String matricula;
    private String salario;
    private String dependente;
    private boolean vt;
    private boolean insalubridade;
    private boolean periculosidade;

    public Funcionario(String nome, String matricula, String salario, String dependente, boolean vt, boolean insalubridade, boolean periculosidade) {
        super(nome);
        this.matricula = matricula;
        this.salario = salario;
        this.dependente = dependente;
        this.vt = vt;
        this.insalubridade = insalubridade;
        this.periculosidade = periculosidade;
    }

    public boolean isVt() {
        return vt;
    }

    public void setVt(boolean vt) {
        this.vt = vt;
    }

    public boolean isInsalubridade() {
        return insalubridade;
    }

    public void setInsalubridade(boolean insalubridade) {
        this.insalubridade = insalubridade;
    }

    public boolean isPericulosidade() {
        return periculosidade;
    }

    public void setPericulosidade(boolean periculosidade) {
        this.periculosidade = periculosidade;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getDependente() {
        return dependente;
    }

    public void setDependente(String dependente) {
        this.dependente = dependente;
    }

    
}