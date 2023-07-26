package br.ufpb.dcx.lab1v1.service;

import br.ufpb.dcx.lab1v1.dto.DisciplineResponseDTO;
import br.ufpb.dcx.lab1v1.dto.DisciplineNameRequestDTO;
import br.ufpb.dcx.lab1v1.dto.DisciplineGradeRequestDTO;
import br.ufpb.dcx.lab1v1.entity.Discipline;
import br.ufpb.dcx.lab1v1.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisciplineService {
    @Autowired
    DisciplineRepository disciplineRepository;

    public DisciplineResponseDTO addDiscipline(DisciplineNameRequestDTO disciplineNameRequestDTO) {
        return DisciplineResponseDTO.from(disciplineRepository.addDiscipline(disciplineNameRequestDTO.getNameDiscipline()));
    }
    public List<DisciplineResponseDTO> listAllDisciplines() {
        return DisciplineResponseDTO.fromAll(disciplineRepository.listAllDisciplines());
    }
    public DisciplineResponseDTO changeNameDiscipline(Integer id, DisciplineNameRequestDTO disciplineNameRequestDTO) {
        return DisciplineResponseDTO.from(disciplineRepository.changeNameDiscipline(id, disciplineNameRequestDTO.getNameDiscipline()));
    }
    public DisciplineResponseDTO returnDiscipline(Integer id) {
        return DisciplineResponseDTO.from(disciplineRepository.returnDiscipline(id));
    }
    public DisciplineResponseDTO addGradeDiscipline(Integer id, DisciplineGradeRequestDTO disciplineGradeDTO) {
        return DisciplineResponseDTO.from(disciplineRepository.addGradeInDiscipline(id, disciplineGradeDTO.getGrade()));
    }

    public DisciplineResponseDTO addLikeDiscipline(Integer id) {
        return DisciplineResponseDTO.from(disciplineRepository.addLikeDiscipline(id));
    }
    public DisciplineResponseDTO removeDiscipline(Integer id) {
        return DisciplineResponseDTO.from(disciplineRepository.removeDiscipline(id));
    };
    public List<DisciplineResponseDTO> getDisciplineForHighestAvg() {
        return DisciplineResponseDTO.fromAll(disciplineRepository.getDisciplineForHighestAverage());
    }
}
