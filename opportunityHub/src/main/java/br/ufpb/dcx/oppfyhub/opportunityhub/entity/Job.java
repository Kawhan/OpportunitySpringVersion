package br.ufpb.dcx.oppfyhub.opportunityhub.entity;

import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    Integer numberVacancies;
    Integer hoursWeek;
    Double scholarshipValue;
    @CreationTimestamp
    LocalDate registrationData;
    String benefits;
    String titleJob;
    String pdfLink;
    LocalDate closingDate;
    @ManyToOne
    Teacher teacher;
    @Enumerated(EnumType.STRING)
    TypeJob typeJob;
    String nameProject;
    String linkJob;

    public Job(Integer numberVacancies,
               Integer hoursWeek,
               Double scholarshipValue,
               String benefits,
               String titleJob,
               String pdfLink,
               LocalDate closingDate,
               Teacher teacher,
               TypeJob typeJob,
               String nameProject,
               String linkJob) {
        this.numberVacancies = numberVacancies;
        this.hoursWeek = hoursWeek;
        this.scholarshipValue = scholarshipValue;
        this.benefits = benefits;
        this.titleJob = titleJob;
        this.pdfLink = pdfLink;
        this.closingDate = closingDate;
        this.teacher = teacher;
        this.typeJob = typeJob;
        this.nameProject = nameProject;
        this.linkJob = linkJob;
    }
}
