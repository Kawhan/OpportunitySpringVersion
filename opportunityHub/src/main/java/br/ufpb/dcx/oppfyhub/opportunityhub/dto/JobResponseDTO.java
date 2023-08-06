package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
public class JobResponseDTO {
    private Integer numberVacancies;
    private Integer hoursWeek;
    private Double scholarshipValue;
    @CreationTimestamp
    private LocalDate registrationData;
    private LocalDate openingDate;
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

    public static JobResponseDTO from(Job job) {
        return JobResponseDTO
                .builder()
                .numberVacancies(job.getNumberVacancies())
                .hoursWeek(job.getHoursWeek())
                .scholarshipValue(job.getScholarshipValue())
                .openingDate(job.getOpeningDate())
                .registrationData(job.getRegistrationData())
                .benefits(job.getBenefits())
                .titleJob(job.getTitleJob())
                .pdfLink(job.getPdfLink())
                .closingDate(job.getClosingDate())
                .teacher(job.getTeacher())
                .typeJob(job.getTypeJob())
                .nameProject(job.getNameProject())
                .linkJob(job.getLinkJob())
                .build();
    }

    public static List<JobResponseDTO> fromAll(List<Job> jobs) {
        return jobs.stream().map(JobResponseDTO::from).toList();
    }
}