package controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.Disciplina;
import services.DisciplinaService;

@RestController
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@PostMapping("/v1/api/disciplinas")
	public  ResponseEntity<Disciplina> setDisciplina(@PathParam("nome") String nome, @PathParam("nota") double nota) {
		return new ResponseEntity<Disciplina>(disciplinaService.setDisciplina(nome, nota), HttpStatus.OK);
	}
	
	//teste
	
	@RequestMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> getDisciplina(@PathParam("id") int id) {
		Disciplina disciplina = disciplinaService.getDisciplina(id);
		if (disciplina != null) {
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);			
		}
		return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
	}
	
//	public ResponseEntity<Disciplina> getDisciplinas() {
//		return new ResponseEntity<Disciplina>(disciplinaService.getDisciplinas(), HttpStatus.OK);
//	}

}
