package br.ufpb.dcx.oppfyhub.opportunityhub.controler;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherNameRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/v1/api/teachers")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping
    @ResponseStatus(code=HttpStatus.OK)
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.listAllTeachers();
    }

    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public TeacherResponseDTO addTeacher(@RequestBody @Valid TeacherNameRequestDTO teacherNameRequestDTO) {
        return teacherService.addTeacher(teacherNameRequestDTO);
    }

    @GetMapping("{id}")
    @ResponseStatus(code=HttpStatus.OK)
    public TeacherResponseDTO getTeacher(@PathVariable long id) {
        return teacherService.getTeacher(id);
    }

    @PatchMapping("{id}/name")
    @ResponseStatus(code=HttpStatus.OK)
    public TeacherResponseDTO changeNameTeacher(@PathVariable long id,@RequestBody @Valid TeacherNameRequestDTO teacherNameRequestDTO) {
        return teacherService.changeNameTeacher(id, teacherNameRequestDTO);
    }

    @PostMapping("{id}/delete")
    @ResponseStatus(code=HttpStatus.OK)
    public TeacherResponseDTO deleteTeacher(@PathVariable long id) {
        return teacherService.deleteTeacher(id);
    }
}
