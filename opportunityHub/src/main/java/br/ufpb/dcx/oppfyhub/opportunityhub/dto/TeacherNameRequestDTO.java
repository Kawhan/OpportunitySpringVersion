package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TeacherNameRequestDTO {
    @JsonProperty("teacher_name")
    @NotNull
    private String TeacherName;
}
