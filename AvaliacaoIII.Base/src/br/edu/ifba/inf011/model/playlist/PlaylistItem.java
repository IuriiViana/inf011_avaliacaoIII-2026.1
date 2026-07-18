package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * VISITOR — Element
 *
 * Antes: interface com toXML() e getBandwidth() embutidos — forçava mudança
 * em todas as classes a cada nova operação (violação de OCP e SRP).
 *
 * Depois: apenas accept(), que delega o trabalho ao Visitor concreto.
 * Novas operações analíticas = novas implementações de PlaylistVisitor.
 * As classes de domínio nunca precisam ser modificadas.
 */
public interface PlaylistItem {
	void accept(PlaylistVisitor visitor);
}