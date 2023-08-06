package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherNameRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundTeacherException;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public List<TeacherResponseDTO> listAllTeachers() {
        return TeacherResponseDTO.fromAll(teacherRepository.findAll());
    }

    public TeacherResponseDTO addTeacher(TeacherNameRequestDTO teacherNameRequestDTO) {
        Teacher newTeacher = new Teacher(teacherNameRequestDTO.getTeacherName());
        Teacher teacher = teacherRepository.save(newTeacher);
        return TeacherResponseDTO.from(teacher);
    }

    public TeacherResponseDTO getTeacher(long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            throw new NotFoundTeacherException();
        }
        return TeacherResponseDTO.from(teacher.get());
    }

    public TeacherResponseDTO changeNameTeacher(long id, TeacherNameRequestDTO teacherNameRequestDTO) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            throw new NotFoundTeacherException();
        }
        Teacher changedTeacher = teacher.get();
        changedTeacher.setName(teacherNameRequestDTO.getTeacherName());
        return TeacherResponseDTO.from(teacherRepository.save(changedTeacher));
    }

    public TeacherResponseDTO deleteTeacher(long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            throw new NotFoundTeacherException();
        }
        teacherRepository.delete(teacher.get());
        return TeacherResponseDTO.from(teacher.get());
    }
}
