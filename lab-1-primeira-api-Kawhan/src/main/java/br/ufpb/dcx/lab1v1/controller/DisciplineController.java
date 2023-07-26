package br.ufpb.dcx.lab1v1.controller;

import br.ufpb.dcx.lab1v1.dto.DisciplineResponseDTO;
import br.ufpb.dcx.lab1v1.dto.DisciplineNameRequestDTO;
import br.ufpb.dcx.lab1v1.dto.DisciplineGradeRequestDTO;
import br.ufpb.dcx.lab1v1.service.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DisciplineController {
    @Autowired
    DisciplineService disciplineService;

    @PostMapping("/v1/api/disciplines")
    public ResponseEntity<DisciplineResponseDTO> addNewDisciplines(@RequestBody @Valid DisciplineNameRequestDTO disciplineNameRequestDTO) {
        return new ResponseEntity<DisciplineResponseDTO>(disciplineService.addDiscipline(disciplineNameRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/v1/api/disciplines")
    public ResponseEntity<List<DisciplineResponseDTO>> getDisciplines() {
        return new ResponseEntity<List<DisciplineResponseDTO>>(disciplineService.listAllDisciplines(), HttpStatus.OK);
    }

    @GetMapping("/v1/api/disciplines/{id}")
    public ResponseEntity<DisciplineResponseDTO> getIndividualDiscipline(@PathVariable Integer id) {
        return new ResponseEntity<DisciplineResponseDTO>(disciplineService.returnDiscipline(id), HttpStatus.OK);
    }

    @PatchMapping("/v1/api/disciplines/{id}/name")
    public ResponseEntity<DisciplineResponseDTO> changeNameDiscipline(@PathVariable Integer id, @RequestBody @Valid DisciplineNameRequestDTO disciplineNameRequestDTO) {
        return new ResponseEntity<DisciplineResponseDTO>(disciplineService.changeNameDiscipline(id, disciplineNameRequestDTO), HttpStatus.OK);
    }

    @PatchMapping("/v1/api/disciplines/{id}/grade")
    public ResponseEntity<DisciplineResponseDTO> addGradeDiscipline(@PathVariable Integer id, @RequestBody @Valid DisciplineGradeRequestDTO disciplineGradeRequestDTO) {
        return new ResponseEntity<DisciplineResponseDTO>(disciplineService.addGradeDiscipline(id, disciplineGradeRequestDTO), HttpStatus.OK);
    }

    @PatchMapping("/v1/api/disciplines/{id}/like")
    public ResponseEntity<DisciplineResponseDTO> addLikeDiscipline(@PathVariable Integer id) {
        return new ResponseEntity<DisciplineResponseDTO>(disciplineService.addLikeDiscipline(id), HttpStatus.OK);
    }

    @PostMapping("/v1/api/disciplines/{id}/remove")
    public ResponseEntity<DisciplineResponseDTO> removeDiscipline(@PathVariable Integer id) {
        return new ResponseEntity<DisciplineResponseDTO>(disciplineService.removeDiscipline(id), HttpStatus.OK);
    }

    @GetMapping("v1/api/disciplines/ranking")
    public ResponseEntity<List<DisciplineResponseDTO>> getDisciplineForHighestAvg() {
        return new ResponseEntity<List<DisciplineResponseDTO>>(disciplineService.getDisciplineForHighestAvg(), HttpStatus.OK);
    }


}
