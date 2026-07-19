package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * VISITOR — ConcreteElement
 *
 * Removidos: toXML() e getBandwidth() — operações que agora vivem em
 * Visitors concretos. MP3 nunca mais precisará ser modificado para
 * acomodar novos requisitos da equipe de Ciência de Dados.
 */
public class MP3 implements PlaylistItem {

    private String nome;
    private double tamanhoMegaBytes;

    public MP3(String nome, double tamanho) {
        this.nome = nome;
        this.tamanhoMegaBytes = tamanho;
    }

    public String getNome() {
        return this.nome;
    }

    public double getTamanhoMegaBytes() {
        return this.tamanhoMegaBytes;
    }

    @Override
    public void accept(PlaylistVisitor visitor) {
        visitor.visit(this);
    }
}