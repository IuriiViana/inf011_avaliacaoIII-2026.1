package br.edu.ifba.inf011.model.playlist;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.avaliacao3.visitor.ExportadorXmlVisitor;
import br.edu.ifba.inf011.avaliacao3.visitor.LarguraBandaVisitor;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * Playlist orquestra a iteração sobre os itens e expõe accept() para que
 * qualquer Visitor possa percorrer todos os elementos.
 *
 * Os métodos toXML() e getBandaTotal() são mantidos por compatibilidade com o
 * código cliente existente — agora delegam internamente aos Visitors corretos,
 * sem nenhuma lógica embutida na própria Playlist.
 */
public class Playlist {

	private final List<PlaylistItem> items = new ArrayList<>();

	public void addItem(PlaylistItem item) {
		this.items.add(item);
	}

	/** Ponto de extensão: qualquer futuro Visitor pode percorrer a playlist aqui. */
	public void accept(PlaylistVisitor visitor) {
		this.items.forEach(item -> item.accept(visitor));
	}

	/** Compatibilidade com código existente — usa ExportadorXmlVisitor internamente. */
	public String toXML() {
		ExportadorXmlVisitor visitor = new ExportadorXmlVisitor();
		this.accept(visitor);
		return "<playlist>\n" + visitor.getXml() + "</playlist>\n";
	}

	/** Compatibilidade com código existente — usa LarguraBandaVisitor internamente. */
	public Double getBandaTotal() {
		LarguraBandaVisitor visitor = new LarguraBandaVisitor();
		this.accept(visitor);
		return visitor.getTotal();
	}
}