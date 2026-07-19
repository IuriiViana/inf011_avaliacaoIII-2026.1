package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * VISITOR — ConcreteElement
 *
 * Removidos: toXML() e getBandwidth().
 * Corrigido: getLink() era private — os Visitors não conseguiam acessar.
 */
public class Video implements PlaylistItem {

	private String nome;
	private double tamanhoMegaBytes;
	private String link;

	public Video(String nome, double tamanho, String link) {
		this.nome = nome;
		this.tamanhoMegaBytes = tamanho;
		this.link = link;
	}

	public String getNome() {
		return this.nome;
	}

	public double getTamanhoMegaBytes() {
		return this.tamanhoMegaBytes;
	}

	public String getLink() {       // era private — corrigido para public
		return this.link;
	}

	@Override
	public void accept(PlaylistVisitor visitor) {
		visitor.visit(this);
	}
}