package br.edu.ifba.inf011.model;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.CinemaTimelineBuilder;
import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.avaliacao1.timeline.builder.TimelineBuilder;
import br.edu.ifba.inf011.avaliacao3.builder.PacoteBuilder;
import br.edu.ifba.inf011.avaliacao3.visitor.RelatorioNomesVisitor;
import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.Playlist;
import br.edu.ifba.inf011.model.playlist.Video;

public class ClienteAval3 {

	public void runQuestaoI() {
		System.out.println("======== QUESTÃO I — Composite + Builder ========\n");

		TimelineBuilder builder = new CinemaTimelineBuilder();
		Timeline tl = builder.reset().addClassAdapterVideo("MainShot_4K.mov").build();

		// PacoteBuilder monta a Trilogia Matrix de forma legível e segura
		Pacote trilogiaMatrix = new PacoteBuilder()
				.comNome("Trilogia Matrix")
				.comDesconto(0.10)
				.adicionar(new Filme("Matrix", 20.0, tl))
				.adicionar(new Filme("Matrix Reloaded", 25.0, tl))
				.adicionar(new Filme("Matrix Revolutions", 15.0, tl))
				.build();

		// Serie é um Composite intermediário
		Serie blackMirror = new Serie("Black Mirror", 1);
		blackMirror.adicionarEpisodio(new Episodio("Hino Nacional", 10.0, 1, tl));
		blackMirror.adicionarEpisodio(new Episodio("Quinze Milhões de Méritos", 12.0, 2, tl));

		// Super Pacote: Pacote dentro de Pacote (árvore Composite de profundidade 2)
		Pacote colecaoSciFi = new PacoteBuilder()
				.comNome("Coleção Sci-Fi")
				.comDesconto(0.15)
				.adicionar(trilogiaMatrix)                          // Pacote
				.adicionar(blackMirror)                             // Serie
				.adicionar(new Filme("Blade Runner", 18.0, tl))    // Filme avulso
				.build();

		// Carrinho trata pacote simples e super-pacote da mesma forma (polimorfismo)
		System.out.println("Trilogia Matrix");
		System.out.println("  Preço:   R$ " + trilogiaMatrix.getPreco());
		System.out.println("  Duração: " + trilogiaMatrix.getDuracao() + "s\n");

		System.out.println("Coleção Sci-Fi (aninhado)");
		System.out.println("  Preço:   R$ " + colecaoSciFi.getPreco());
		System.out.println("  Duração: " + colecaoSciFi.getDuracao() + "s");
	}

	public void runQuestaoII() {
		System.out.println("\n======== QUESTÃO II — Visitor ========\n");

		TimelineBuilder builder = new CinemaTimelineBuilder();
		Timeline tl = builder.reset().addClassAdapterVideo("MainShot_4K.mov").build();

		Pacote trilogiaMatrix = new PacoteBuilder()
				.comNome("Trilogia Matrix")
				.comDesconto(0.10)
				.adicionar(new Filme("Matrix", 20.0, tl))
				.adicionar(new Filme("Matrix Reloaded", 25.0, tl))
				.adicionar(new Filme("Matrix Revolutions", 15.0, tl))
				.build();

		Playlist playlist = new Playlist();
		playlist.addItem(trilogiaMatrix);
		playlist.addItem(new MP3("Son Of A Gun", 1000.0));
		playlist.addItem(new Video("Trailer Blade Runner 2049", 500.0, "https://streaming.com/br2049"));

		// Operação 1 — getBandaTotal() usa LarguraBandaVisitor internamente
		System.out.println("Largura de Banda Total: " + playlist.getBandaTotal() + " MB");

		// Operação 2 — toXML() usa ExportadorXmlVisitor internamente
		System.out.println("\nExportação XML:");
		System.out.println(playlist.toXML());

		// Operação 3 — Novo relatório de nomes: Visitor direto, zero mudança nas classes
		System.out.println("Relatório de Nomes (Visitor direto):");
		RelatorioNomesVisitor relatorio = new RelatorioNomesVisitor();
		playlist.accept(relatorio);
		System.out.println(relatorio.getRelatorio());
	}

	public void run() {
		this.runQuestaoI();
		this.runQuestaoII();
	}

	public static void main(String[] args) {
		new ClienteAval3().run();
	}
}