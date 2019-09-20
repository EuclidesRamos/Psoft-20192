package lab2.psoft.Services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.psoft.Dao.DisciplinaRepository;
import lab2.psoft.Entities.Disciplina;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class DisciplinaService {

    private DisciplinaRepository disciplinasDAO;
    private Long id;

    public DisciplinaService(DisciplinaRepository<Disciplina, long> disciplinasDAO) {
        this.disciplinasDAO = disciplinasDAO;
        this.id = new Long(0);
    }

    public void initDisciplina() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>(){};
        InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplinas.json");
        try {
            List<Disciplina> disciplinas = mapper.readValue(inputStream, typeReference);
            this.disciplinasDAO.saveAll(disciplinas);
        } catch (IOException e) {
            System.out.println("Não foi possível salvar os alunos: " + e.getMessage());
        }
    }

    public Disciplina setDisciplina(Disciplina novaDisciplina) {
        novaDisciplina.setId(id);
        id++;
        return disciplinasDAO.save(novaDisciplina);
    }

    public Disciplina getDisciplina(Integer id) {
        return disciplinas.get(id);
    }

    public Map<Integer, Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Disciplina atualizaDisciplina(Integer id, String novoNome) {
        disciplinas.get(id).setNome(novoNome);
        return disciplinas.get(id);
    }

    public Disciplina atualizaNota(Integer id, double novaNota) {
        disciplinas.get(id).setNota(novaNota);
        return disciplinas.get(id);
    }

    public Disciplina removeDisciplina(Integer id) {
        if (disciplinas.containsKey(id)) {
            Disciplina disciplina = disciplinas.get(id);
            disciplinas.remove(id);
            return disciplina;
        }
        return null;
    }

    public List<Disciplina> ranking() {
        List<Disciplina> todasDisciplinas = new ArrayList<Disciplina>(disciplinas.values());
        Collections.sort(todasDisciplinas);
        return todasDisciplinas;
    }


}
