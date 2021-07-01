package br.com.meli.dto;

import br.com.meli.apicrud.entity.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDTO {

    private String nome;

    public ProdutoDTO(String nome) {
        this.nome = nome;
    }

    public ProdutoDTO(Produto produto) {
        this.nome = produto.getNome();
    }

    public String getNome() {
        return nome;
    }

    public static ProdutoDTO converte(Produto produto) {
        return new ProdutoDTO(produto.getNome());
    }


    public static List<ProdutoDTO> converte(List<Produto> produtos) {
        return produtos.stream()
                .map(a -> new ProdutoDTO(a))
                .collect(Collectors.toList());
    }
}
