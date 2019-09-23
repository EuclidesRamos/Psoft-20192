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

@RestController
public class DisciplinaController {

	@Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/v1/api/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplina(@PathVariable("id") String id) {
        Disciplina disciplina = disciplinaService.getDisciplina(Long.parseLong(id));
        if (disciplina != null) {
            return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
        }
        return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/v1/api/disciplinas")
    public ResponseEntity<List<Disciplina>> getDisciplinas() {
        return new ResponseEntity<List<Disciplina>>(disciplinaService.getDisciplinas(), HttpStatus.OK);
    }

    @PutMapping("/v1/api/disciplinas/nota/{id}")
    public ResponseEntity<Disciplina> atualizaDisciplinaNota(@PathVariable("id") String id, @RequestBody String novaNota) {
        Disciplina disciplina = disciplinaService.atualizaNota(Long.parseLong(id), Double.parseDouble(novaNota));
        if (disciplina != null) {
            return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
        }
        return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/v1/api/disciplinas/likes/{id}")
    public ResponseEntity<Disciplina> atualizaDisciplinaLikes(@PathVariable("id") String id) {
        Disciplina disciplina = disciplinaService.atualizaLikes(Long.parseLong(id));
        if (disciplina != null) {
            return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
        }
        return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/v1/api/disciplinas/comentarios/{id}")
    public ResponseEntity<Disciplina> atualizaDisciplinaComentarios(@PathVariable("id") String id, @RequestBody String comentario) {
        Disciplina disciplina = disciplinaService.atualizaComentarios(Long.parseLong(id), comentario);
        if (disciplina != null) {
            return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
        }
        return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/v1/api/disciplinas/ranking/likes")
    public ResponseEntity<List<Disciplina>> rankingDisciplinas() {
        return new ResponseEntity<List<Disciplina>>(disciplinaService.ranking(), HttpStatus.OK);
    }
    
    @GetMapping("/v1/api/disciplinas/ranking/likes")
    public ResponseEntity<List<Disciplina>> rankingDisciplinas() {
        return new ResponseEntity<List<Disciplina>>(disciplinaService.rankingPorLikes(), HttpStatus.OK);
    }

}
