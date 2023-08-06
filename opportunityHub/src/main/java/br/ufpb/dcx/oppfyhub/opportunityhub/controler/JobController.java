package br.ufpb.dcx.oppfyhub.opportunityhub.controler;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/jobs")
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping
    @ResponseStatus(code= HttpStatus.OK)
    public List<JobResponseDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public JobResponseDTO createJob(@RequestBody JobRequestDTO jobRequestDTO) {
        return jobService.createJob(jobRequestDTO);
    }
}
