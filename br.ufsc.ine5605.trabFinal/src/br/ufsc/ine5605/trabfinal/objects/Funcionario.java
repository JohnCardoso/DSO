/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabfinal.objects;

public class Funcionario extends Pessoa {
	private double salario;
	private int dependente;
	private boolean vt;
	private boolean insalubridade;
	private boolean periculosidade;

	public Funcionario(String nome, long cpf, double salario, int dependente, boolean vt, boolean insalubridade,
			boolean periculosidade) {
		super(nome, cpf);
		this.salario = salario;
		this.dependente = dependente;
		this.vt = vt;
		this.insalubridade = insalubridade;
		this.periculosidade = periculosidade;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getDependente() {
		return dependente;
	}

	public void setDependente(int dependente) {
		this.dependente = dependente;
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

}