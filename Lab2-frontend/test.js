let assert = require('assert');

describe('métodos de Array', function() {
  it('reverse inverte ordem dos elementos', function(){
    let a = [1, 10, 3, 5];
    assert.deepEqual([5, 3, 10, 1], a.reverse());
  });
  it('map cria novo array a partir de função de mapeamento', function(){
    let a = [1, 10, 3, 5];
    assert.deepEqual([10, 100, 30, 50], a.map(e => 10 * e));
    assert.deepEqual([true, false, true, true], a.map(e => e % 2 != 0));
  });
  it('filter cria novo array com elementos filtrados', function(){
    let a = [1, 10, 3, 5];
    assert.deepEqual([1, 3, 5], a.filter(e => e % 2 != 0));
  });
});
