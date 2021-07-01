package br.com.meli.apicrud.apicrud;

import br.com.meli.apicrud.entity.Produto;
import br.com.meli.dao.ProdutoDAO;
import br.com.meli.dto.ProdutoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ApiCrudController {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    // Primeiro teste de API no Spring
    @GetMapping
    @RequestMapping("/{codigo}/{nome}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Produto> obterProdutoPorCodigo(@PathVariable String codigo, @PathVariable String nome) {
        Produto produto = new Produto(codigo, nome);
        return new ResponseEntity<Produto>(produto, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public List<ProdutoDTO> obterProdutos() {
        List<Produto> produtos = produtoDAO.getListaProdutos();
        List<ProdutoDTO> dtos = ProdutoDTO.converte(produtos);
        return dtos;
    }

    @GetMapping
    @RequestMapping("/{codigo}")
    public ProdutoDTO obterProduto(@PathVariable String codigo) {
        Produto produto = produtoDAO.get(codigo);
        ProdutoDTO produtoDTO = ProdutoDTO.converte(produto);
        return produtoDTO;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> adicionarProduto(@RequestBody Produto produto, UriComponentsBuilder uriBuilder) {
        ProdutoDTO produtoDTO = ProdutoDTO.converte(produto);
        produtoDAO.adicionarProduto(produto);
        System.out.println("Chegou: " + produtoDTO.getNome());
        URI uri = uriBuilder.path("produtos/{codigo}").buildAndExpand(produto.getCodigo()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    @RequestMapping("/alterar/{codigo}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Produto> alterarProdutoPorCodigo(@PathVariable String codigo, @RequestBody Produto produto) {
        produtoDAO.alterarProduto(codigo, produto.getNome());
        return new ResponseEntity<Produto>(produto, HttpStatus.ACCEPTED);
    }


    @DeleteMapping
    @RequestMapping("/deletar/{codigo}/{nome}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Produto> deletarProduto(@PathVariable String codigo, @PathVariable String nome) {
        Produto produtoDel = produtoDAO.get(codigo);
        if(produtoDAO.getListaProdutos().contains(produtoDel)) {
            produtoDAO.getListaProdutos().remove(produtoDel);
        }
        return new ResponseEntity<Produto>(HttpStatus.ACCEPTED);
    }

}
