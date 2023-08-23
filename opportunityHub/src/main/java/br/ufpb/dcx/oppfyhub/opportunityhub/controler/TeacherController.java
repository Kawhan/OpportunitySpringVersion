package br.ufpb.dcx.oppfyhub.opportunityhub.controler;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherNameRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1/api/teachers")
public class TeacherController {
    @Autowired
    TeacherService teacherService;


    // Gets
    @GetMapping
    @ResponseStatus(code=HttpStatus.OK)
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.listAllTeachers();
    }

    @GetMapping("{id}")
    @ResponseStatus(code=HttpStatus.OK)
    public TeacherResponseDTO getTeacher(@PathVariable long id) {
        return teacherService.getTeacher(id);
    }

    // Posts

    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public TeacherResponseDTO addTeacher(@RequestBody @Valid TeacherNameRequestDTO teacherNameRequestDTO) {
        return teacherService.addTeacher(teacherNameRequestDTO);
    }

    // Patch's

    @PatchMapping("{id}/name")
    @ResponseStatus(code=HttpStatus.OK)
    public TeacherResponseDTO changeNameTeacher(@PathVariable long id,@RequestBody @Valid TeacherNameRequestDTO teacherNameRequestDTO) {
        return teacherService.changeNameTeacher(id, teacherNameRequestDTO);
    }


    // Deletes
    @DeleteMapping("{id}/delete")
    @ResponseStatus(code=HttpStatus.OK)
    public TeacherResponseDTO deleteTeacher(@PathVariable long id) {
        return teacherService.deleteTeacher(id);
    }

}
