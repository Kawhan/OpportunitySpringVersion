package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherNameRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public List<TeacherResponseDTO> ListAllTeachers () {
        return TeacherResponseDTO.fromAll(teacherRepository.findAll());
    }

    public TeacherResponseDTO AddTeacher(TeacherNameRequestDTO teacherNameRequestDTO) {
        Teacher newTeacher = new Teacher(teacherNameRequestDTO.getTeacherName());
        return TeacherResponseDTO.from(teacherRepository.save(newTeacher));
    }
}
