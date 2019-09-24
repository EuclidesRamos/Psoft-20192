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

    public Optional<Disciplina> atualizaDisciplina(long id, String novoNome) {
        if (this.disciplinasDAO.findById(id).isPresent()) {
            this.disciplinasDAO.findById(id).get().setNome(novoNome);
        }
        return this.disciplinasDAO.findById(id);
    }

    public Optional<Disciplina> atualizaNota(long id, double novaNota) {
        if (this.disciplinasDAO.findById(id).isPresent()) {
            this.disciplinasDAO.findById(id).get().setNota(novaNota);
        }
        return this.disciplinasDAO.findById(id);
    }

    public Disciplina removeDisciplina(long id) {
        if (this.disciplinasDAO.existsById(id)) {
            Disciplina disciplina = this.disciplinasDAO.getOne(id);
            this.disciplinasDAO.deleteById(id);;
            return disciplina;
        }
        return null;
    }

	public Optional<Disciplina> atualizaLikes(long id) {
        if (this.disciplinasDAO.findById(id).isPresent()) {
            this.disciplinasDAO.findById(id).get().setLikes();
        }
        return this.disciplinasDAO.findById(id);
	}

	public Optional<Disciplina> atualizaComentarios(long id, String novoComentario) {
        if (this.disciplinasDAO.findById(id).isPresent()) {
            this.disciplinasDAO.findById(id).get().setComentario(novoComentario);
        }
        return this.disciplinasDAO.findById(id);
	}

	public List<Disciplina> rankingPorLikes() {
		List<Disciplina> disciplinas = this.disciplinasDAO.findAll();
		Comparator<Disciplina> estrategia = new OrdenaPorLikes();
		Collections.sort(disciplinas, estrategia);
		return disciplinas;
	}

    public List<Disciplina> rankingPorNotas() {
        List<Disciplina> disciplinas = this.disciplinasDAO.findAll();
        Comparator<Disciplina> estrategia = new OrdenaPorNota();
        Collections.sort(disciplinas, estrategia);
        return disciplinas;
    }
}
