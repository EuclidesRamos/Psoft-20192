let disciplinas = [];
const URL = 'https://lab01-projsw-ufcg.herokuapp.com/api';
let $mensagem = document.querySelector("#mensagem");

function fetch_disciplinas() {
    $mensagem.innerText = "Buscando as disciplinas...";
    fetch(URL + "/disciplinas")
    .then(response => response.json())
    .then(dados => {
        let $disciplinas = document.querySelector("div");
        $disciplinas.innerHTML = '';
        dados.forEach((element, index) => {
            let $p = document.createElement("p");
            $disciplinas.appendChild($p);
            $p.innerText = "Disciplina: " + dados[index].nome + ", nota: " + dados[index].nota;
        });
    });
    setTimeout(function () {
        $mensagem.innerText = '';
    }, 1000);
}

function salvaDados() {
    let nome = document.querySelector("#nome").value;
    let nota = document.querySelector("#nota").value;

    $mensagem.innerText = 'Salvando Disciplina no Sistema...';

    fetch(URL + "/disciplinas", 
    {
        'method':'POST',
        'body':`{"nome": "${nome}", "nota":${nota}}`,
        'headers': {'Content-Type': 'application/json'}
    })
    .then(response => response.json())
    .then(dados => {
        console.log(dados);
        fetch_disciplinas();
    })
}

(function init() {
    console.log("Começando a execução...");
    let $button = document.querySelector("button");
    $button.addEventListener('click', salvaDados);
    
    fetch_disciplinas();
}());

