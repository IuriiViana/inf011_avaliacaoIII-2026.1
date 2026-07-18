package br.edu.ifba.inf011.model.comercial;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.avaliacao3.composite.ProdutoComercial;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * COMPOSITE — Leaf
 * VISITOR   — ConcreteElement
 */
public class Episodio implements ProdutoComercial {

	private String titulo;
	private Double preco;
	private Integer numero;
	private Timeline timeline;

	public Episodio(String titulo, Double preco, Integer numero, Timeline timeline) {
		this.titulo = titulo;
		this.preco = preco;
		this.numero = numero;
		this.timeline = timeline;
	}

	@Override
	public String getTitulo() {
		return this.titulo;
	}

	public Integer getNumero() {
		return this.numero;
	}

	@Override
	public Double getPreco() {
		return this.preco;
	}

	@Override
	public Integer getDuracao() {
		return this.timeline.getDurationInSeconds();
	}

	@Override
	public void accept(PlaylistVisitor visitor) {
		visitor.visit(this);
	}
}