package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class TeacherResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String TeacherName;

    public static TeacherResponseDTO from(Teacher teacher) {
        return TeacherResponseDTO.builder()
                .id(teacher.getId())
                .TeacherName(teacher.getName())
                .build();
    }

    public static List<TeacherResponseDTO> fromAll(List<Teacher> ListOfTeacher) {
        return ListOfTeacher.stream().map(TeacherResponseDTO::from).collect(Collectors.toList());
    }
}
