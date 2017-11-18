package br.ufsc.ine5605.trabfinal.objects;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 3493086819578079101L;

    protected String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}