exports.disciplina = disciplina;

function factoryDisciplina(abreviacao, nome, creditos, preRequisitos) {
	let _abreviacao = abreviacao;
	let _nome = nome;
	let _creditos = creditos;
	let _preRequisitos = preRequisitos;
	return {
		getAbreviacao: () => _abreviacao,
		getNome: () => _nome,
		getCreditos: () => _creditos,
		getPreRequisitos: () => _preRequisitos
	};
};




