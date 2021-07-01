package br.com.meli.apicrud.entity;

public class Produto {

    private String codigo;
    private String nome;


    public Produto(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Produto() {}


    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
