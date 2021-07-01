package br.com.meli.dao;

import br.com.meli.apicrud.entity.Produto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProdutoDAO {

    private static List<Produto> produtos = new ArrayList<Produto>(
            Arrays.asList(
                    new Produto("ML12345", "Tênis"),
                    new Produto("ML78674", "Bolsa"),
                    new Produto("ML986438", "Notebook"),
                    new Produto("ML564738", "Mochila"),
                    new Produto("Ml98712", "Televisão")
            )
    );


    public List<Produto> getListaProdutos(){
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto get(String codigo) {
        Produto produtoRes = new Produto();
        for (Produto p : produtos) {
            if(p.getCodigo().equals(codigo)) {
                produtoRes = p;
            }
        }
        return produtoRes;
    }

    public void alterarProduto(String codigo, String novoNome) {
        for (Produto p : produtos) {
            if(p.getCodigo().equals(codigo)) {
                p.setNome(novoNome);
            }
        }
    }
}
