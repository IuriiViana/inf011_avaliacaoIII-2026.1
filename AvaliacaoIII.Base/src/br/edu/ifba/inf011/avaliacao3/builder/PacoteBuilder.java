package br.edu.ifba.inf011.avaliacao3.builder;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.avaliacao3.composite.ProdutoComercial;
import br.edu.ifba.inf011.model.comercial.Pacote;

/**
 * BUILDER — Builder (e Product = Pacote)
 *
 * Problema: construtores aninhados gigantes tornavam a criação de promoções
 * propícia a erros — new Pacote("SciFi", new Pacote(...), new Serie(...), ...)
 *
 * Solução: API fluente que acumula estado e só constrói o Pacote em build().
 * O construtor de Pacote fica protegido do cliente, que passa a escrever:
 *
 *   new PacoteBuilder()
 *       .comNome("Trilogia Matrix")
 *       .comDesconto(0.10)
 *       .adicionar(filmeMatrix)
 *       .adicionar(filmeReloaded)
 *       .build();
 *
 * O builder é reutilizável: build() reseta o estado interno para um novo produto.
 */
public class PacoteBuilder {

    private String titulo = "";
    private Double desconto = 0.10;
    private List<ProdutoComercial> itens = new ArrayList<>();

    public PacoteBuilder comNome(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public PacoteBuilder comDesconto(Double desconto) {
        this.desconto = desconto;
        return this;
    }

    public PacoteBuilder adicionar(ProdutoComercial produto) {
        this.itens.add(produto);
        return this;
    }

    public Pacote build() {
        Pacote pacote = new Pacote(this.titulo, this.desconto);
        this.itens.forEach(pacote::adicionar);
        // reset para permitir reuso do builder
        this.titulo = "";
        this.desconto = 0.10;
        this.itens = new ArrayList<>();
        return pacote;
    }
}