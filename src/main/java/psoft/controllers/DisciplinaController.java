package psoft.controllers;


import java.util.Map;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import psoft.entities.Disciplina;
import psoft.services.DisciplinaService;

@RestController
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@PostMapping("/v1/api/disciplinas")
	public  ResponseEntity<Disciplina> setDisciplina(@RequestBody Disciplina disciplina) {
		return new ResponseEntity<Disciplina>(disciplinaService.setDisciplina(disciplina), HttpStatus.OK);
	}

	// @PathParam("nome") String nome, @PathParam("nota") double nota
	// @RequestBody Disciplina disciplina
	
	@GetMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> getDisciplina(@PathVariable("id") String id) {
		Disciplina disciplina = disciplinaService.getDisciplina(Integer.parseInt(id));
		if (disciplina != null) {
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);			
		}
		return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/v1/api/disciplinas")
	public ResponseEntity<Map<Integer, Disciplina>> getDisciplinas() {
		return new ResponseEntity<Map<Integer, Disciplina>>(disciplinaService.getDisciplinas(), HttpStatus.OK);
	}

	@PutMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> atualizaDisciplina(@PathVariable("id") String id, @RequestBody String novoNome) {
		Disciplina disciplina = disciplinaService.atualizaDisciplina(Integer.parseInt(id), novoNome);
		if (disciplina != null) {
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		}
		return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
	}

}
