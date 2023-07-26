package br.ufpb.dcx.lab1v1.entity;

import br.ufpb.dcx.lab1v1.dto.DisciplineResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

@NoArgsConstructor
@Setter
@Getter
public class Discipline {

    private static final AtomicInteger count = new AtomicInteger(0);

    private Integer id = count.incrementAndGet();

    private String nameDiscipline;

    private Integer likes = 0;

    private List<Float> grades = new ArrayList<>();

    public Discipline(String nameDiscipline) {
        this.id = count.incrementAndGet();
        this.nameDiscipline = nameDiscipline;
        this.likes = 0;
        this.grades = new ArrayList<>();
    }

    public void addLike(){
        this.likes += 1;
    }

    public Float addGrades(Float grade){
        if (grade < 0 || grade > 10){
            throw new IllegalArgumentException("Nota invalida!");
        }

        this.grades.add(grade);

        return this.getGrades();
    }

    public Float getGrades() {
        if ( this.grades.size() == 0) {
            return 0.0f;
        }

        Float media = 0.0f;
        for (Float nota : this.grades) {
            media += nota;
        }
        media /= this.grades.size();
        return media;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Discipline discipline = (Discipline) obj;
        return Objects.equals(nameDiscipline, discipline.nameDiscipline) &&
                Objects.equals(likes, discipline.likes) &&
                Objects.equals(grades, discipline.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}