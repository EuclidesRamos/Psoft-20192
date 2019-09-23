package lab2.psoft.util;

import java.util.Comparator;

import lab2.psoft.Entities.Disciplina;

public class OrdenaPorLikes implements Comparator<Disciplina> {

	@Override
	public int compare(Disciplina disciplina1, Disciplina disciplina2) {
		return (disciplina1.getLikes() - disciplina2.getLikes());
	}

}
