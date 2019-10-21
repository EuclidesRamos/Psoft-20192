class Disciplina {
	
	Object.defineProperty(this, 'creditos', {
                set: function (valor) { this.status = valor; },
                get: function () {return this.status; }
        });
	
	constructor(id, nome, creditos, preRequisitos) {
		this.id = id;
		this.nome = nome;
		this.creditos = creditos;
		this.preRequisitos = preRequisitos;
	}

	id() {
		return this.id;
	}

	get_nome() {
		return this.nome;
	}

	set_nome(novoNome) {
		this.nome = novoNome;
	}
}

class Turma {
	
	constructor(disciplina, periodo) {
		this.disciplina = disciplina;
		this.periodo = periodo;
		this.professor = "";
		this.estudantes = [];
		this.status = "";
	}

	get_disciplina() {
		return this.disciplina;
	}
}




exports.Disciplina = Disciplina;
