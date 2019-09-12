package psoft.services;

import java.util.*;

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

	public Disciplina atualizaDisciplina(Integer id, String novoNome) {
		disciplinas.get(id).setNome(novoNome);
		return disciplinas.get(id);
	}

	public Disciplina atualizaNota(Integer id, double novaNota) {
	    disciplinas.get(id).setNota(novaNota);
	    return disciplinas.get(id);
    }

    public Disciplina removeDisciplina(Integer id) {
	    if (disciplinas.containsKey(id)) {
	        Disciplina disciplina = disciplinas.get(id);
	        disciplinas.remove(id);
	        return disciplina;
        }
	    return null;
	}

    public List<Disciplina> ranking() {
	    List<Disciplina> todasDisciplinas = new ArrayList<Disciplina>(disciplinas.values());
	    Collections.sort(todasDisciplinas);
	    return todasDisciplinas;
    }
}
