package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
@Builder
public class TeacherResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String teacherName;

    public static TeacherResponseDTO from(Teacher teacher) {
        return TeacherResponseDTO.builder()
                .id(teacher.getId())
                .teacherName(teacher.getName())
                .build();
    }

    public static List<TeacherResponseDTO> fromAll(List<Teacher> listOfTeacher) {
        return listOfTeacher.stream().map(TeacherResponseDTO::from).toList();
    }
}
