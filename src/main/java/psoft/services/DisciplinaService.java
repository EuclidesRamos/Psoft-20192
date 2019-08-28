package psoft.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import psoft.entities.Disciplina;

@Service
public class DisciplinaService {
	
	private Map<Integer, Disciplina> disciplinas = new HashMap<>();
	private Integer id = 0;

	public Disciplina setDisciplina(Disciplina novaDisciplina) {
		novaDisciplina.setId(id);
		disciplinas.put(id, novaDisciplina);
		id++;
		return novaDisciplina;
	}
	
	public Disciplina getDisciplina(Integer id) {
		return disciplinas.get(id);
	}

	public Map<Integer, Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public Disciplina atualizaDisciplina(int id, String novoNome) {
		disciplinas.get(id).setNome(novoNome);
		return disciplinas.get(id);
	}
}
