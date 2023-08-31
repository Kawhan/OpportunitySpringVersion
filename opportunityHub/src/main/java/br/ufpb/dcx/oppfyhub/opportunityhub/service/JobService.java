package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobTitleRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.User;
import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundJobException;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundUserException;
import br.ufpb.dcx.oppfyhub.opportunityhub.mappers.JobMapper;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.JobRepository;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobMapper jobMapper;

    public List<JobResponseDTO> getAllJobs() {
        return JobResponseDTO.fromAll(jobRepository.findAll());
    }

    public JobResponseDTO createJob(JobRequestDTO jobRequestDTO) {
        Optional<User> teacher = userRepository.findById(jobRequestDTO.getTeacherID());
        if (teacher.isEmpty()) {
            throw new NotFoundUserException();
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

    public List<JobResponseDTO> getJobByTitleJob(String titleJob) {
        return JobResponseDTO.fromAll(jobRepository.findByTitleJobStartingWith(titleJob));
    }

    public List<JobResponseDTO> getJobsByTypeJob(TypeJob typeJob) {
        return JobResponseDTO.fromAll(jobRepository.findByTypeJob(typeJob));
    }

    public JobResponseDTO changeInfoJob(long id, JobRequestDTO jobRequestDTO) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new NotFoundJobException();
        }
        Optional<User> teacher = userRepository.findById(jobRequestDTO.getTeacherID());
        if (teacher.isEmpty()) {
            throw new NotFoundUserException();
        }
        Job updateJob = jobMapper.updateJobFromDto(jobRequestDTO, job.get());
        updateJob.setUser(teacher.get());
        jobRepository.save(updateJob);
        return JobResponseDTO.from(job.get());
    }

    public JobResponseDTO changeTitleJob(long id, JobTitleRequestDTO jobTitleRequestDTO) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new NotFoundJobException();
        }
        job.get().setTitleJob(jobTitleRequestDTO.getTitleJob());
        jobRepository.save(job.get());
        return JobResponseDTO.from(job.get());
    }

    public JobResponseDTO deleteJob(long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new NotFoundJobException();
        }
        jobRepository.delete(job.get());
        return JobResponseDTO.from(job.get());
    }
}
