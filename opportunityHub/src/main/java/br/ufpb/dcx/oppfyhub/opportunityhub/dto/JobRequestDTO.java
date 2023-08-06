package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class JobRequestDTO {
    @JsonProperty("number_vacancies")
    @NotNull
    private Integer numberVacancies;

    @JsonProperty("hours_week")
    @NotNull
    private Integer hoursWeek;

    @JsonProperty("scholarship_value")
    @NotNull
    private Double scholarshipValue;

    @JsonProperty("benefits")
    @NotNull
    private String benefits;

    @JsonProperty("title_job")
    @NotNull
    private String titleJob;

    @JsonProperty("pdf_link")
    @NotNull
    private String pdfLink;

    @JsonProperty("closing_date")
    @NotNull
    private LocalDate closingDate;

    @JsonProperty("teacher")
    @NotNull
    private Long teacherID;

    @JsonProperty("type_job")
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeJob typeJob;

    @JsonProperty("name_project")
    @NotNull
    private String nameProject;

    @JsonProperty("link_job")
    @NotNull
    private String linkJob;
}
