package psoft.entities;

import com.fasterxml.jackson.annotation.JsonCreator;


public class Disciplina implements Comparable<Disciplina> {

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

    public void setNome(String nome) {
		this.nome = nome;
    }

    public void setNota(double nota) {
	    this.nota = nota;
    }

    @Override
    public int compareTo(Disciplina object) {
	    if (this.nota > object.getNota()) {
	        return -1;
        }
	    return 1;
    }
}
