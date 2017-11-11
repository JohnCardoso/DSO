package br.ufsc.ine5605.trabfinal.objects;

public abstract class Pessoa {
    protected String nome;
    protected long cpf;

    public Pessoa(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
}