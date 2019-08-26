package psoft.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import psoft.entities.Disciplina;

@Service
public class DisciplinaService {
	
	private List<Disciplina> disciplinas = new ArrayList<>();
	private int id = 0;
	
	public Disciplina setDisciplina(String nome, double nota) {
		Disciplina novaDisciplina = new Disciplina(id, nome, nota);
		disciplinas.add(novaDisciplina);
		id++;
		return novaDisciplina;
	}
	
	public Disciplina getDisciplina(int id) {
		return disciplinas.get(id);
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
}
