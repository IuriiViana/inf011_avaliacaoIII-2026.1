package br.edu.ifba.inf011.model.comercial;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.avaliacao3.composite.ProdutoComercial;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * COMPOSITE — Leaf
 * VISITOR   — ConcreteElement
 *
 * Não possui filhos — apenas delega duração para a Timeline interna (domínio
 * técnico) e expõe preço/título (domínio comercial). O método accept() aciona
 * o double-dispatch do Visitor sem que Filme precise conhecer a operação.
 */
public class Filme implements ProdutoComercial {

	private String titulo;
	private Double preco;
	private Timeline timeline;

	public Filme(String titulo, Double preco, Timeline timeline) {
		this.titulo = titulo;
		this.preco = preco;
		this.timeline = timeline;
	}

	@Override
	public String getTitulo() {
		return this.titulo;
	}

	@Override
	public Double getPreco() {
		return this.preco;
	}

	/** Delega ao domínio técnico — Filme não conhece codecs. */
	@Override
	public Integer getDuracao() {
		return this.timeline.getDurationInSeconds();
	}

	/** VISITOR — double-dispatch: chama o visit correto no Visitor. */
	@Override
	public void accept(PlaylistVisitor visitor) {
		visitor.visit(this);
	}
}