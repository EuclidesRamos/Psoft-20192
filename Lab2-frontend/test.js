let assert = require('assert');
let disciplina = require('./scoord').disciplina;
let turma = require('./scoord').turma;

describe('factory Disciplina', function() {
  let d0;

    before(async () => {
        d0 = disciplina('prog1', 'Programação 1', 4, []);
    });

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
  let e0;

  before(async () => {
      t0 = turma('prog1', '1');
      e0 = estudante('123', 'Fulano', 'funalo@ufcg', 'site-top/img.jpg');
  });

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
      t0.matricular_estudante(e0);
      assert.equal(e0.get_matricula(), t0.get_estudantes()[0]); 
  });

  it('não deve permitir matricular estudante já cadastrado', function () {
      t0.matricular_estudante(e0);
      assert.equal('Estudante ja matriculado', t0.matricular_estudante(e0));
  });

  it('não deve permitir matricular estudante em disciplina concluída', function () {
      t0.set_status('concluida');
      assert.equal('Impossivel matricular estudante, disciplina concluída', t0.matricular_estudante(e0));
  });

  it('deve permitir desmatricular estudante', function () {
      t0.matricular_estudante(e0);
      t0.desmatricular_estudante(e0);
      assert.deepEqual([], t0.get_estudantes());
  });

  it('não deve permitir setar status invalido', function () {
      t0.set_status('ativa');
      t0.set_status('em construcao');
      assert.equal('ativa', t0.get_status());
  });
});

describe('factory Professor', function () {
    let p0;
    let t0;

    before(async () => {
        t0 = turma('prog1', '1');
        p0 = professor('111', 'Dalton', 'dalton@ufcg', '222', 'banco-de-fotos/foto-de-dalton.jpg');
    })

    it('deve criar professores distintos a cada invocação', function () {
      p1 = professor('111', 'Dalton', 'dalton@ufcg', '222', 'banco-de-fotos/foto-de-dalton.jpg');
      p2 = professor('111', 'Dalton', 'dalton@ufcg', '222', 'banco-de-fotos/foto-de-dalton.jpg');
      p3 = professor('111', 'Dalton', 'dalton@ufcg', '222', 'banco-de-fotos/foto-de-dalton.jpg');

      assert.notEqual(p1, p2);
      assert.notEqual(p1, p3);
      assert.notEqual(p2, p3);
  });

  it('deve reter os dados de inicialização', function () {
      assert.equal('111', p0.get_matricula());
      assert.equal('Dalton', p0.get_nome());
  });

  it('deve permitir atualização de nome', function () {
    p0.set_nome('Dalton Serey');
    assert.equal('111', p0.get_matricula());
    assert.equal("Dalton Serey", p0.get_nome());
  });

  it('deve permitir alocar turma', function () {
      p0.aloca_turma(t0);
      assert.equal('prog1', p0.turmas('1')[0]);
  })

  it('deve permitir a filtragem de busca das turmas pelo período', function () {
      let t1 = turma('prog2', '2');
      let t2 = turma('ic', '1');
      let t3 = turma('psoft', '4');
      p0.aloca_turma(t0);
      p0.aloca_turma(t1);
      p0.aloca_turma(t2);
      p0.aloca_turma(t3);
      assert.deepEqual([t0, t2], p0.turmas('1'));
  }) 

});


describe('factory Estudante', function () {
    let e0;
    let t0;

    before(async () => {
        t0 = turma('prog1', '1');
        e0 = estudante('123', 'Fulano', 'fulano@ufcg', '456', 'site-top/foto.jpg');
    })

    it('deve criar estudantes distintos a cada invocação', function () {
      e1 = estudante('123', 'Fulano', 'fulano@ufcg', '456', 'site-top/foto.jpg');
      e2 = estudante('123', 'Fulano', 'fulano@ufcg', '456', 'site-top/foto.jpg');
      e3 = estudante('123', 'Fulano', 'fulano@ufcg', '456', 'site-top/foto.jpg');

      assert.notEqual(e1, e2);
      assert.notEqual(e1, e3);
      assert.notEqual(e2, e3);
  });

  it('deve reter os dados de inicialização', function () {
      assert.equal('123', e0.get_matricula());
      assert.equal('Fulano', e0.get_nome());
  });

  it('deve permitir atualização de nome', function () {
    e0.set_nome('Fulano de Tal');
    assert.equal('123', e0.get_matricula());
    assert.equal("Fulano de Tal", e0.get_nome());
  });

  it('deve permitir alocar turma', function () {
      e0.aloca_turma(t0);
      assert.equal('prog1', e0.turmas('1')[0]);
  })

  it('deve permitir a filtragem de busca das turmas pelo período', function () {
      let t1 = turma('prog2', '2');
      let t2 = turma('ic', '1');
      let t3 = turma('psoft', '4');
      e0.aloca_turma(t0);
      e0.aloca_turma(t1);
      e0.aloca_turma(t2);
      e0.aloca_turma(t3);
      assert.deepEqual([t0, t2], e0.turmas('1'));
  }) 

});