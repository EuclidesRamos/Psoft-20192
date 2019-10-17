let assert = require('assert');
let disciplina = require('./scoord').disciplina;
let turma = require('./scoord').turma;

describe('factory Disciplina', function() {
  let d0;

    before(async () => {
        d0 = disciplina('prog1', 'Programação 1', 4, []);
    })

    it('deve criar disciplinas distintas a cada invocação', function(){
        d0 = disciplina('prog1', 'Programação 1', 4, []);
        d1 = disciplina('prog1', 'Programação 1', 4, []);
        d2 = disciplina('prog1', 'Programação 1', 4, []);
        assert.notEqual(d0, d1);
        assert.notEqual(d0, d2);
        assert.notEqual(d1, d2);
    });

    it('deve reter os dados de inicialização', function(){
        assert.equal('prog1', d0.id());
        assert.equal('Programação 1', d0.get_nome());
        assert.equal(4, d0.creditos);
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('deve permitir atualização de nome', function(){
        d0.set_nome('Programação de Computadores I')
        assert.equal('prog1', d0.id());
        assert.equal('Programação de Computadores I', d0.get_nome());
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('não deve permitir atualização de id via set_id', function(){
        assert.throws(function () {
            d0.set_id('outro')
        }, TypeError);
        assert.equal('prog1', d0.id());
    });

});


describe('factory Turma', function () {
  let t0;

  before(async () => {
      t0 = turma('prog1', '1');
  })

  it('deve criar turmas distintas a cada invocação', function () {
      t1 = turma('prog1', '1');
      t2 = turma('prog1', '1');
      t3 = turma('prog1', '1');

      assert.notEqual(t1, t2);
      assert.notEqual(t1, t3);
      assert.notEqual(t2, t3);
  });

  it('deve reter os dados de inicialização', function () {
      assert.equal('prog1', t0.get_disciplina());
      assert.equal('1', t0.get_periodo());
  });

  it('deve permitir atualização de professor', function () {
    t0.set_professor('Dalton');
    assert.equal('prog1', t0.get_disciplina());
    assert.equal("Dalton", t0.get_professor());
  });

  it('deve permitir atualização de status', function () {
      t0.set_status('ativa');
      assert.equal('prog1', t0.get_disciplina());
      assert.equal('ativa', t0.get_status());
  });

  it('deve permitir matricular estudante', function () {
      
  });
});