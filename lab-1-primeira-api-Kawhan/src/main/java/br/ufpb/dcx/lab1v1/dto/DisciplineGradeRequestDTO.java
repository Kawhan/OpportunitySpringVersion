package br.ufpb.dcx.lab1v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DisciplineGradeRequestDTO {

    @JsonProperty("grade")
    @NotNull
    private Float grade;
}
