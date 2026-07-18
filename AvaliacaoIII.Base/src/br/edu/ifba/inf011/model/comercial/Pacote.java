package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.avaliacao3.composite.ProdutoComercial;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * COMPOSITE — Composite (raiz da árvore de produtos)
 * VISITOR   — ConcreteElement
 *
 * Antes: só suportava List<Filme>, impedindo aninhamento de pacotes e séries.
 *
 * Depois: guarda List<ProdutoComercial>, aceitando Filme, Episodio, Serie e
 * outros Pacotes — árvore de profundidade ilimitada. Preço e duração são
 * calculados recursivamente sobre os filhos, exatamente como o cliente
 * (o carrinho) esperaria de qualquer produto avulso.
 */
public class Pacote implements ProdutoComercial {

	private String titulo;
	private Double desconto;
	private List<ProdutoComercial> itens;

	/** Construtor usado pelo PacoteBuilder — não instanciar diretamente no cliente. */
	public Pacote(String titulo, Double desconto) {
		this.titulo = titulo;
		this.desconto = desconto;
		this.itens = new ArrayList<>();
	}

	public void adicionar(ProdutoComercial produto) {
		this.itens.add(produto);
	}

	public List<ProdutoComercial> getItens() {
		return this.itens;
	}

	@Override
	public String getTitulo() {
		return this.titulo;
	}

	/** Soma recursiva dos preços dos filhos, com desconto aplicado no topo. */
	@Override
	public Double getPreco() {
		double soma = this.itens.stream().mapToDouble(ProdutoComercial::getPreco).sum();
		return soma * (1.0 - this.desconto);
	}

	/** Soma recursiva das durações — funciona para sub-pacotes e séries. */
	@Override
	public Integer getDuracao() {
		return this.itens.stream().mapToInt(ProdutoComercial::getDuracao).sum();
	}

	/**
	 * VISITOR — o Visitor decide como tratar um Pacote (ex: XML aninhado,
	 * soma de banda, listagem de nomes). Pacote não conhece nenhuma dessas operações.
	 */
	@Override
	public void accept(PlaylistVisitor visitor) {
		visitor.visit(this);
	}
}