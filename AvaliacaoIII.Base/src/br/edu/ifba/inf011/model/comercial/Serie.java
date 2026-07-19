package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.avaliacao3.composite.ProdutoComercial;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * COMPOSITE — Composite (agrupador de Episodios)
 * VISITOR   — ConcreteElement
 *
 * Serie agrega Episodios e delega o cálculo de preço/duração a eles.
 * Pode ser adicionada a um Pacote porque implementa ProdutoComercial,
 * e pode ser adicionada a uma Playlist porque ProdutoComercial extends PlaylistItem.
 */
public class Serie implements ProdutoComercial {

    private String titulo;
    private Integer temporada;
    private List<Episodio> episodios;

    public Serie(String titulo, Integer temporada) {
        this.titulo = titulo;
        this.temporada = temporada;   // corrigido: o construtor original esquecia de setar temporada
        this.episodios = new ArrayList<>();
    }

    public void adicionarEpisodio(Episodio episodio) {
        this.episodios.add(episodio);
    }

    public List<Episodio> getEpisodios() {
        return this.episodios;
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    public Integer getTemporada() {
        return this.temporada;
    }

    @Override
    public Double getPreco() {
        return this.episodios.stream().mapToDouble(Episodio::getPreco).sum() * 0.9;
    }

    @Override
    public Integer getDuracao() {
        return this.episodios.stream().mapToInt(Episodio::getDuracao).sum();
    }

    @Override
    public void accept(PlaylistVisitor visitor) {
        visitor.visit(this);
    }
}