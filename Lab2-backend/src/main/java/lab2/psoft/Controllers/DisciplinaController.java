package lab2.psoft.Controllers;

import lab2.psoft.Entities.Disciplina;
import lab2.psoft.Services.DisciplinaService;
import lab2.psoft.Services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class DisciplinaController {

	@Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/api/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplina(@PathVariable("id") String id) {
        Optional<Disciplina> disciplina = disciplinaService.getDisciplina(Long.parseLong(id));
        if (disciplina.isPresent()) {
            return new ResponseEntity<Disciplina>(disciplina.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/disciplinas")
    public ResponseEntity<List<Disciplina>> getDisciplinas() {
        return new ResponseEntity<List<Disciplina>>(disciplinaService.getDisciplinas(), HttpStatus.OK);
    }

    @PutMapping("/api/disciplinas/nota/{id}")
    public ResponseEntity<Disciplina> atualizaDisciplinaNota(@PathVariable("id") String id, @RequestBody Disciplina disciplinaNota) {
        Optional<Disciplina> disciplina = disciplinaService.atualizaNota(Long.parseLong(id), disciplinaNota.getNota());
        if (disciplina.isPresent()) {
            return new ResponseEntity<Disciplina>(disciplina.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/api/disciplinas/likes/{id}")
    public ResponseEntity<Disciplina> atualizaDisciplinaLikes(@PathVariable("id") String id) {
        Optional<Disciplina>  disciplina = disciplinaService.atualizaLikes(Long.parseLong(id));
        if (disciplina.isPresent()) {
            return new ResponseEntity<Disciplina>(disciplina.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/api/disciplinas/comentarios/{id}")
    public ResponseEntity<Disciplina> atualizaDisciplinaComentarios(@PathVariable("id") String id, @RequestBody Disciplina disciplinaComentario) {
        Optional<Disciplina>  disciplina = disciplinaService.atualizaComentarios(Long.parseLong(id), disciplinaComentario.getComentarios());
        if (disciplina.isPresent()) {
            return new ResponseEntity<Disciplina>(disciplina.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/api/disciplinas/ranking/notas")
    public ResponseEntity<List<Disciplina>> rankingDisciplinasPorNotas() {
        return new ResponseEntity<List<Disciplina>>(disciplinaService.rankingPorNotas(), HttpStatus.OK);
    }
    
    @GetMapping("/v1/api/disciplinas/ranking/likes")
    public ResponseEntity<List<Disciplina>> rankingDisciplinasPorLikes() {
        return new ResponseEntity<List<Disciplina>>(disciplinaService.rankingPorLikes(), HttpStatus.OK);
    }

}
