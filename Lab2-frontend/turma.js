exports.turma = turma

factoryTurma(disciplina, periodo) {
	let _disciplina = disciplina;
	let _periodo = periodo;
	let _professor;
	let _estudantes = [];
	let _status;

	return {
		getDisciplina: () => _disciplina,
		getPeriodo: () => _periodo,
		getProfessor: () => _professor,
		setProfessor: (novoProfessor) => _professor = novoProfessor,
		matricularEstudante: (novoEstudante) => if (_status == "concluida") {
				return "Disciplina já concluída";
			} else {
				_estudantes.forEach((element) => {
					if (element.getMatricula() == novoEstudante.getMatricula()) {
						return "Aluno ja matriculado";	
					}
				});
				_estudantes.push(novoEstudante);
			},
		desmatricularEstudante: (estudante) => if (_estudantes.includes(estudante)) {
				_estudantes.pop(estudante);	
			},
		setStatus: (novoStatus) => if (novoStatus == "planejada" || novoStatus == "ativa" || novoStatus == "concluida") {
				_status = novoStatus;
			},
	};
}
