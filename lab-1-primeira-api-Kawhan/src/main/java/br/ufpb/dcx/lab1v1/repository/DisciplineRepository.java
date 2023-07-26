package br.ufpb.dcx.lab1v1.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpb.dcx.lab1v1.entity.Discipline;
import org.springframework.stereotype.Repository;


@Repository
public class DisciplineRepository {
    private Map<Integer, Discipline> disciplines;

    public DisciplineRepository() {
        this.disciplines =  new HashMap<>();
    }

    public Discipline addDiscipline(String nameDiscipline) {
        Discipline newDiscipline = new Discipline();

        newDiscipline.setNameDiscipline(nameDiscipline);

        this.verifyDiscipline(newDiscipline);

        this.disciplines.put(newDiscipline.getId(), newDiscipline);

        return newDiscipline;
    }


    public List<Discipline> listAllDisciplines() {
        List<Discipline> listDisciplines = new ArrayList<>(this.disciplines.values());

        return listDisciplines;
    }

    public Discipline changeNameDiscipline(Integer id, String novo_nome) {
        Discipline discipline = this.returnDiscipline(id);
        discipline.setNameDiscipline(novo_nome);

        return discipline;
    }

    public Discipline addGradeInDiscipline(Integer id, Float grade){
        Discipline discipline = this.returnDiscipline(id);
        discipline.addGrades(grade);

        return discipline;
    }

    public Discipline addLikeDiscipline(Integer id) {
        Discipline discipline = this.returnDiscipline(id);
        discipline.addLike();

        return discipline;
    }


    public Discipline returnDiscipline(Integer id) {
        Discipline discipline = disciplines.get(id);

        if (discipline != null) {
            return discipline;
        }

        throw new RuntimeException("Disciplina não encontrada");
    }

    public Discipline removeDiscipline(Integer id_disciplina) {
        Discipline discipline = this.returnDiscipline(id_disciplina);

        this.disciplines.remove(id_disciplina, discipline);

        return discipline;
    }

    public List<Discipline> getDisciplineForHighestAverage() {
        List<Discipline> sortedDisciplines = new ArrayList<>(this.disciplines.values());

        Collections.sort(sortedDisciplines, new Comparator<Discipline>() {
            @Override
            public int compare(Discipline discipline1, Discipline discipline2) {
                Float media1 = discipline1.getGrades();
                Float media2 = discipline2.getGrades();
                return media2.compareTo(media1);
            }
        });

        return sortedDisciplines;
    }

    private void verifyDiscipline(Discipline discipline) {
        for (Discipline disciplineCreated : this.disciplines.values()) {
            if (disciplineCreated.getNameDiscipline().equals(discipline.getNameDiscipline())) {
                throw new RuntimeException("Disciplina já cadastrada");
            }
        }
    }
}
