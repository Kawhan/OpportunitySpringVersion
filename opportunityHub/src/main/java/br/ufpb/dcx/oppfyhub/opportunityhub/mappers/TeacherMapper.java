package br.ufpb.dcx.oppfyhub.opportunityhub.mappers;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherNameRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    @Autowired
    ModelMapper modelMapper;

    public Teacher updateTeacherFromDTO(TeacherNameRequestDTO teacherNameRequestDTO, Teacher TeacherToUpdate) {
        modelMapper.map(teacherNameRequestDTO, TeacherToUpdate);
        return TeacherToUpdate;
    }
}
