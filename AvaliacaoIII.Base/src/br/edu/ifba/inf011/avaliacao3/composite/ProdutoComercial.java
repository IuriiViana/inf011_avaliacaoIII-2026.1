package br.edu.ifba.inf011.avaliacao3.composite;

import br.edu.ifba.inf011.model.playlist.PlaylistItem;

/**
 * COMPOSITE — Component
 *
 * Interface comum a folhas (Filme, Episodio) e composições (Pacote, Serie).
 * Estende PlaylistItem para que qualquer ProdutoComercial possa ser adicionado
 * a uma Playlist sem casting — integrando Composite e Visitor.
 */
public interface ProdutoComercial extends PlaylistItem {
    String getTitulo();
    Double getPreco();
    Integer getDuracao();
}