package lab2.psoft.Services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.psoft.Dao.DisciplinaRepository;
import lab2.psoft.Entities.Disciplina;

import lab2.psoft.util.OrdenaPorLikes;
import lab2.psoft.util.OrdenaPorNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.annotation.PostConstruct;

@Service
public class DisciplinaService {

	@Autowired
    private DisciplinaRepository<Disciplina, Long> disciplinasDAO;

    public DisciplinaService(DisciplinaRepository<Disciplina, Long> disciplinasDAO) {
    	super();
        this.disciplinasDAO = disciplinasDAO;
    }

    @PostConstruct
    public void initDisciplina() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>(){};
        InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplinas.json");
        try {
            List<Disciplina> disciplinas = mapper.readValue(inputStream, typeReference);
            this.disciplinasDAO.saveAll(disciplinas);
        } catch (IOException e) {
            System.out.println("Não foi possível salvar as disciplinas: " + e.getMessage());
        }
    }

    public Disciplina setDisciplina(Disciplina novaDisciplina) {
        return this.disciplinasDAO.save(novaDisciplina);
    }

    public Optional<Disciplina> getDisciplina(long id) {
        return this.disciplinasDAO.findById(id);
    }

    public List<Disciplina> getDisciplinas() {
        return this.disciplinasDAO.findAll();
    }

    public Optional<Disciplina> atualizaNota(long id, double novaNota) {
    	Optional<Disciplina> disciplina = this.disciplinasDAO.findById(id);
		if (disciplina.isPresent()) {
            disciplina.get().setNota(novaNota);
            this.disciplinasDAO.save(disciplina.get());
        }
        return disciplina;
    }

	public Optional<Disciplina> atualizaLikes(long id) {
		Optional<Disciplina> disciplina = this.disciplinasDAO.findById(id);
		if (disciplina.isPresent()) {
            disciplina.get().setLikes();;
            this.disciplinasDAO.save(disciplina.get());
        }
        return disciplina;
	}

	public Optional<Disciplina> atualizaComentarios(long id, String novoComentario) {
		Optional<Disciplina> disciplina = this.disciplinasDAO.findById(id);
		if (disciplina.isPresent()) {
            disciplina.get().setComentario(novoComentario);
            this.disciplinasDAO.save(disciplina.get());
        }
        return disciplina;
	}

	public List<Disciplina> rankingPorLikes() {
		List<Disciplina> disciplinas = this.disciplinasDAO.findAll();
		Collections.sort(disciplinas, new OrdenaPorLikes());
		return disciplinas;
	}

    public List<Disciplina> rankingPorNotas() {
        List<Disciplina> disciplinas = this.disciplinasDAO.findAll();
        Collections.sort(disciplinas, new OrdenaPorNota());
        return disciplinas;
    }
}
