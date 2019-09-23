package lab2.psoft.Services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.psoft.Dao.DisciplinaRepository;
import lab2.psoft.Entities.Disciplina;

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
    private long id = 0;

    public DisciplinaService(DisciplinaRepository<Disciplina, Long> disciplinasDAO) {
        this.disciplinasDAO = disciplinasDAO;
    }

    @PostConstruct
    public void initDisciplina() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>(){};
        InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplinas.json");
        try {
            List<Disciplina> disciplinas = mapper.readValue(inputStream, typeReference);
            for (Disciplina d: disciplinas) {
            	this.setDisciplina(d);
            }
            
        } catch (IOException e) {
            System.out.println("Não foi possível salvar as disciplinas: " + e.getMessage());
        }
    }

    public Disciplina setDisciplina(Disciplina novaDisciplina) {
        novaDisciplina.setId(id);
        id++;
        return this.disciplinasDAO.save(novaDisciplina);
    }

    public Disciplina getDisciplina(long id) {
        return this.disciplinasDAO.getOne(id);
    }

    public List<Disciplina> getDisciplinas() {
        return this.disciplinasDAO.findAll();
    }

    public Disciplina atualizaDisciplina(long id, String novoNome) {
    	this.disciplinasDAO.getOne(id).setNome(novoNome);
        return this.disciplinasDAO.getOne(id);
    }

    public Disciplina atualizaNota(long id, double novaNota) {
        this.disciplinasDAO.getOne(id).setNota(novaNota);
        return this.disciplinasDAO.getOne(id);
    }

    public Disciplina removeDisciplina(long id) {
        if (this.disciplinasDAO.existsById(id)) {
            Disciplina disciplina = this.disciplinasDAO.getOne(id);
            this.disciplinasDAO.deleteById(id);;
            return disciplina;
        }
        return null;
    }

	public Disciplina atualizaLikes(long id) {
		this.disciplinasDAO.getOne(id).setLikes();
		return this.disciplinasDAO.getOne(id);
	}

	public Disciplina atualizaComentarios(long parseLong, String novoComentario) {
		this.disciplinasDAO.getOne(id).setComentario(novoComentario);
		return this.disciplinasDAO.getOne(id);
	}

	public List<Disciplina> rankingPorLikes() {
		List<Disciplina> disciplinas = this.disciplinasDAO.findAll();
		Collections.sort(disciplinas);
		return disciplinas;
	}

}
