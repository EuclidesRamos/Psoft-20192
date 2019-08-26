package psoft.entities;

import com.fasterxml.jackson.annotation.JsonCreator;


public class Disciplina {
	
	private int id;
	private String nome;
	private double nota;

	@JsonCreator
	public Disciplina(int id, String nome, double nota) {
		super();
		this.id = id;
		this.nome = nome;
		this.nota = nota;
	}

}
