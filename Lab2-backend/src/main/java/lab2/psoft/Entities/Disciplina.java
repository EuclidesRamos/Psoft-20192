package lab2.psoft.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Disciplina implements Comparable<Disciplina> {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private double nota;
    private String comentarios;
    private int likes;

    public Disciplina() {
        super();
    }

    public Disciplina(Long id, String nome, double nota, String comentarios, int likes) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.comentarios = comentarios;
        this.likes = likes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public double getNota() {
        return this.nota;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setComentario(String novoComentario) {
        this.comentarios += System.lineSeparator() + novoComentario;
    }

    public void setLikes() {
        this.likes += 1;
    }

    @Override
    public int compareTo(Disciplina object) {
        if (this.nota > object.getNota()) {
            return -1;
        }
        return 1;
    }

}
