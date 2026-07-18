package br.edu.ifba.inf011.avaliacao3.visitor;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.Video;

/**
 * VISITOR — ConcreteVisitor (operação: Relatório de Nomes)
 *
 * Demonstra OCP na prática: adicionado sem modificar nenhuma classe existente.
 */
public class RelatorioNomesVisitor implements PlaylistVisitor {

    private final List<String> nomes = new ArrayList<>();

    public String getRelatorio() {
        StringBuilder sb = new StringBuilder("=== Relatório de Elementos da Playlist ===\n");
        this.nomes.forEach(n -> sb.append("  ").append(n).append("\n"));
        return sb.toString();
    }

    @Override
    public void visit(MP3 mp3) {
        this.nomes.add("[MP3]     " + mp3.getNome());
    }

    @Override
    public void visit(Video video) {
        this.nomes.add("[Vídeo]   " + video.getNome());
    }

    @Override
    public void visit(Filme filme) {
        this.nomes.add("[Filme]   " + filme.getTitulo());
    }

    @Override
    public void visit(Episodio episodio) {
        this.nomes.add("[Episódio] " + episodio.getTitulo() + " (ep. " + episodio.getNumero() + ")");
    }

    @Override
    public void visit(Serie serie) {
        this.nomes.add("[Série]   " + serie.getTitulo() + " — Temporada " + serie.getTemporada());
        serie.getEpisodios().forEach(ep -> ep.accept(this));
    }

    @Override
    public void visit(Pacote pacote) {
        this.nomes.add("[Pacote]  " + pacote.getTitulo());
        pacote.getItens().forEach(item -> item.accept(this));
    }
}