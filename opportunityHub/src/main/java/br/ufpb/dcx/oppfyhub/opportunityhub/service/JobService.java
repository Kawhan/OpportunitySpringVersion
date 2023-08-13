package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundJobException;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundTeacherException;
import br.ufpb.dcx.oppfyhub.opportunityhub.mappers.JobMapper;
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

    @Autowired
    JobMapper jobMapper;

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
            throw new NotFoundJobException();
        }
        return JobResponseDTO.from(job.get());
    }

    public JobResponseDTO changeInfoJob(long id, JobRequestDTO jobRequestDTO) {
        Optional<Job> job = jobRepository.findById(id);

        if (job.isEmpty()) {
            throw new NotFoundJobException();
        }

        Optional<Teacher> teacher = teacherRepository.findById(jobRequestDTO.getTeacherID());
        if (teacher.isEmpty()) {
            throw new NotFoundTeacherException();
        }
        Job updateJob = jobMapper.updateJobFromDto(jobRequestDTO, job.get());
        updateJob.setTeacher(teacher.get());
        jobRepository.save(updateJob);
        return JobResponseDTO.from(job.get());
    }
}
