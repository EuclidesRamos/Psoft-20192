package lab2.psoft.util;


import java.util.Comparator;

import lab2.psoft.Entities.Disciplina;

public class OrdenaPorNota implements Comparator<Disciplina>{

	@Override
	public int compare(Disciplina disciplina1, Disciplina disciplina2) {
		return (int) (disciplina1.getNota() - disciplina2.getNota());
	}

}
