package com.unimed.avaliacao.entidade;

public class Beneficiario {
    private int id;

    private String nome;

    private String cpf;

    private String email;

    private int Idade;

    private Plano plano;


    public Beneficiario() {
    }

    public Beneficiario(int id, String nome, String cpf, String email, int idade, Plano plano) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        Idade = idade;
        this.plano = plano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return Idade;
    }

    public void setIdade(int idade) {
        Idade = idade;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
}
