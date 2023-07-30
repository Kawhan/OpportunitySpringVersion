package br.ufpb.dcx.oppfyhub.opportunityhub.controler;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherNameRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class TeacherControler {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/teachers")
    @ResponseStatus(code=HttpStatus.OK)
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.ListAllTeachers();
    }

    @PostMapping("/teachers")
    @ResponseStatus(code=HttpStatus.CREATED)
    public TeacherResponseDTO addTeacher(@RequestBody @Valid TeacherNameRequestDTO teacherNameRequestDTO) {
        return teacherService.AddTeacher(teacherNameRequestDTO);
    }
}
