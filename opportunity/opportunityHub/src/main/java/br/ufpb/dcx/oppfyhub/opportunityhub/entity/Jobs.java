package br.ufpb.dcx.oppfyhub.opportunityhub.entity;

import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jobs {
    private Integer numberVacancies;
    private Integer hoursWeek;
    private Double scholarshipValue;
    @CreationTimestamp
    private LocalDate registrationData;
    private String benefits;
    private String titleJob;
    private String pdfLink;
    private LocalDate closingDate;
    @ManyToOne
    private Teacher teacher;
    @Enumerated(EnumType.STRING)
    private TypeJob typeJob;
    private String nameProject;
    private String linkJob;
}
