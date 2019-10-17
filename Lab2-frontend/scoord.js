exports.disciplina = disciplina;
exports.turma = turma;
exports.professor = professor;
exports.estudante = estudante;

function disciplina(id, nome, creditos, preRequisitos) {
	let _id = id;
	let _nome = nome;

	return {
		id: () => _id,
		get_nome: () => _nome,
		creditos: creditos,
		pre_requisitos: preRequisitos,
		set_nome: (novoNome) => _nome = novoNome
	};
};

function turma(disciplina, periodo) {
	let _disciplina = disciplina;
	let _periodo = periodo;
	let _professor;
	let _estudantes = [];
	let _status;

	return {
		get_disciplina: () => _disciplina,
		get_periodo: () => _periodo,
		get_professor: () => _professor,
		get_status: () => _status,
		get_estudantes: () => _estudantes,
		set_professor: (novoProfessor) => _professor = novoProfessor,
		matricular_estudante: function (novoEstudante) {
			if (_status == "concluida") {
				return "Impossivel matricular estudante, disciplina concluída";
			} else {
				_estudantes.forEach((element) => {
					if (element.get_matricula() == novoEstudante.get_matricula()) {
						return "Aluno ja matriculado";	
					}
				});
				_estudantes.push(novoEstudante);
				return "Aluno matriculado com sucesso!";
			}
		},
		desmatricular_estudante: function (estudante) {
			if (_estudantes.includes(estudante)) {
				_estudantes.pop(estudante);	
			}
		},
		set_status: function (novoStatus) {
			if (novoStatus == "planejada" || novoStatus == "ativa" || novoStatus == "concluida") {
				_status = novoStatus;
			}
		}
	};
};

function professor(matricula, nome, email, cpf, url_de_foto) {
	let _matricula = matricula;
	let _nome = nome;
	let _email = email;
	let _cpf = cpf;
	let _url_de_foto = url_de_foto;
	let turmas = [];

	return {
		get_matricula: () => _matricula,
		get_nome: () => _nome,
		get_email: () => _email,
		get_cpf: () => _cpf,
		get_url_de_foto: () => _url_de_foto,
		set_nome: (novoNome) => _nome = novoNome,
		aloca_turma: (turma) => turmas.push(turma),
		turmas: function (semestre) {
			turmas.forEach((element, index) => {
				let result = [];
				if (element.get_periodo() == semestre) {
					result.push(element);
				}
				return result;
			});
		}
	};
};

function estudante(matricula, nome, email, cpf, url_de_foto) {
	let _matricula = matricula;
	let _nome = nome;
	let _email = email;
	let _cpf = cpf;
	let _url_de_foto = url_de_foto;
	let turmas = [];

	return {
		get_matricula: () => _matricula,
		get_nome: () => _nome,
		get_email: () => _email,
		get_cpf: () => _cpf,
		get_url_de_foto: () => _url_de_foto,
		set_nome: (novoNome) => _nome = novoNome,
		matricula: (turma) => turmas.push(turma),
		turmas: function (semestre) {
			turmas.forEach((element, index) => {
				let result = [];
				if (element.get_periodo() == semestre) {
					result.push(element);
				}
				return result;
			});
		}
	};
};


