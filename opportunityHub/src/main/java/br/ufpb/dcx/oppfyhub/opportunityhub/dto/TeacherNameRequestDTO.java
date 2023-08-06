package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherNameRequestDTO {
    @JsonProperty("teacher_name")
    @NotNull
    private String teacherName;
}
