package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.JobNotFoundException;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundTeacherException;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.JobRepository;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public List<JobResponseDTO> getAllJobs() {
        return JobResponseDTO.fromAll(jobRepository.findAll());
    }

    public JobResponseDTO createJob(JobRequestDTO jobRequestDTO) {
        Optional<Teacher> teacher = teacherRepository.findById(jobRequestDTO.getTeacherID());
        if (teacher.isEmpty()) {
            throw new NotFoundTeacherException();
        }
        Job newJob = new Job(
                jobRequestDTO.getNumberVacancies(),
                jobRequestDTO.getHoursWeek(),
                jobRequestDTO.getScholarshipValue(),
                jobRequestDTO.getOpeningDate(),
                jobRequestDTO.getBenefits(),
                jobRequestDTO.getTitleJob(),
                jobRequestDTO.getPdfLink(),
                jobRequestDTO.getClosingDate(),
                teacher.get(),
                jobRequestDTO.getTypeJob(),
                jobRequestDTO.getNameProject(),
                jobRequestDTO.getLinkJob()
        );
        Job job = jobRepository.save(newJob);
        return JobResponseDTO.from(job);
    }

    public JobResponseDTO getJob(long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }
        return JobResponseDTO.from(job.get());
    }

    public JobResponseDTO changeInfoJob(long id, JobRequestDTO jobRequestDTO) {
        Optional<Job> job = jobRepository.findById(id);

        if (job.isEmpty()) {
            throw new JobNotFoundException();
        }

        Optional<Teacher> teacher = teacherRepository.findById(jobRequestDTO.getTeacherID());
        if (teacher.isEmpty()) {
            throw new NotFoundTeacherException();
        }

        job.get().setNumberVacancies(jobRequestDTO.getNumberVacancies());
        job.get().setHoursWeek(jobRequestDTO.getHoursWeek());
        job.get().setScholarshipValue(jobRequestDTO.getScholarshipValue());
        job.get().setOpeningDate(jobRequestDTO.getOpeningDate());
        job.get().setBenefits(jobRequestDTO.getBenefits());
        job.get().setTypeJob(jobRequestDTO.getTypeJob());
        job.get().setPdfLink(jobRequestDTO.getPdfLink());
        job.get().setClosingDate(jobRequestDTO.getClosingDate());
        job.get().setTeacher(teacher.get());
        job.get().setTypeJob(jobRequestDTO.getTypeJob());
        job.get().setNameProject(jobRequestDTO.getNameProject());
        job.get().setLinkJob(jobRequestDTO.getLinkJob());
        jobRepository.save(job.get());
        return JobResponseDTO.from(job.get());
    }
}
