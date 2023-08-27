package br.ufpb.dcx.oppfyhub.opportunityhub.controler;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobTitleRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("v1/api/jobs")
public class JobController {
    @Autowired
    JobService jobService;

    // Gets

    @GetMapping
    @ResponseStatus(code= HttpStatus.OK)
    public List<JobResponseDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("{id}")
    @ResponseStatus(code=HttpStatus.OK)
    public JobResponseDTO getJob(@PathVariable long id) {
        return jobService.getJob(id);
    }

    @GetMapping("{titleJob}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<JobResponseDTO> getJobByTitleJob(@RequestParam @Valid String titleJob) {
        return jobService.getJobByTitleJob(titleJob);
    }

    // Posts

    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public JobResponseDTO createJob(@RequestBody @Valid JobRequestDTO jobRequestDTO) {
        return jobService.createJob(jobRequestDTO);
    }

    @PutMapping("{id}/change")
    @ResponseStatus(code=HttpStatus.OK)
    public JobResponseDTO changeInfoJob(@PathVariable long id, @RequestBody @Valid JobRequestDTO jobRequestDTO) {
        return jobService.changeInfoJob(id, jobRequestDTO);
    }

    // Patches
    @PatchMapping("{id}/title")
    @ResponseStatus(code=HttpStatus.OK)
    public JobResponseDTO changeTitleJob(@PathVariable long id, @RequestBody @Valid JobTitleRequestDTO jobTitleRequestDTO) {
        return jobService.changeTitleJob(id, jobTitleRequestDTO);
    }


    // Deletes
    @DeleteMapping("{id}/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public JobResponseDTO deleteJob(@PathVariable long id) {
        return jobService.deleteJob(id);
    }


}
