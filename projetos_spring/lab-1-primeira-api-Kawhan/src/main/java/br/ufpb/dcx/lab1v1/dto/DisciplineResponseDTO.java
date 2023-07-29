package br.ufpb.dcx.lab1v1.dto;

import br.ufpb.dcx.lab1v1.entity.Discipline;
import lombok.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class DisciplineResponseDTO {
    private static final AtomicInteger count = new AtomicInteger(0);

    private Integer id = count.incrementAndGet();

    private String nameDiscipline = "";

    private Integer likes = 0;

    private Float grade;

    public static DisciplineResponseDTO from(Discipline discipline) {
        return DisciplineResponseDTO.builder()
                .id(discipline.getId())
                .nameDiscipline(discipline.getNameDiscipline())
                .likes(discipline.getLikes())
                .grade(discipline.getGrades())
                .build();
    }

    public static List<DisciplineResponseDTO> fromAll(List<Discipline> listDisciplines) {
        return listDisciplines.stream().map(DisciplineResponseDTO::from).collect(Collectors.toList());
    }
}
