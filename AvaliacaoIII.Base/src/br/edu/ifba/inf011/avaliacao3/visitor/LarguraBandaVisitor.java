package br.edu.ifba.inf011.avaliacao3.visitor;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.Video;

/**
 * VISITOR — ConcreteVisitor (operação: Largura de Banda Total)
 *
 * Encapsula a lógica de cálculo de banda para cada tipo de elemento.
 * Para estruturas compostas (Serie, Pacote) delega recursivamente
 * aos filhos via accept() — o Visitor navega a árvore Composite.
 *
 * Adicioná-lo não exigiu uma linha de mudança em MP3, Video, Filme, etc.
 */
public class LarguraBandaVisitor implements PlaylistVisitor {

    private static final double BAND_PER_SECOND = 1.5; // MB/s
    private double total = 0.0;

    public Double getTotal() {
        return this.total;
    }

    @Override
    public void visit(MP3 mp3) {
        this.total += mp3.getTamanhoMegaBytes();
    }

    @Override
    public void visit(Video video) {
        this.total += video.getTamanhoMegaBytes();
    }

    @Override
    public void visit(Filme filme) {
        this.total += filme.getDuracao() * BAND_PER_SECOND;
    }

    @Override
    public void visit(Episodio episodio) {
        this.total += episodio.getDuracao() * BAND_PER_SECOND;
    }

    /** Delega para os episódios — o Visitor percorre a estrutura Composite. */
    @Override
    public void visit(Serie serie) {
        serie.getEpisodios().forEach(ep -> ep.accept(this));
    }

    /** Delega para os itens — funciona para pacotes aninhados também. */
    @Override
    public void visit(Pacote pacote) {
        pacote.getItens().forEach(item -> item.accept(this));
    }
}