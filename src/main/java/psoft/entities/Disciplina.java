package psoft.entities;

import com.fasterxml.jackson.annotation.JsonCreator;


public class Disciplina {

	private Integer id;
	private String nome;
	private double nota;

	@JsonCreator
	public Disciplina(String nome, double nota) {
		super();
		this.nome = nome;
		this.nota = nota;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public double getNota() {
		return this.nota;
	}

}
