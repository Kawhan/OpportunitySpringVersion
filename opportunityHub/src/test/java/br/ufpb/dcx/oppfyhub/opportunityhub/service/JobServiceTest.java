package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.JobResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Job;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import br.ufpb.dcx.oppfyhub.opportunityhub.enums.TypeJob;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundTeacherException;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.JobRepository;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class JobServiceTest {
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @Mock
    private TeacherRepository teacherRepository;

    @Test
    public void getAllJobsTest() {
        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);
        teacher1.setName("Professor 1");

        TypeJob typeJob = TypeJob.PROJETO_DE_EXTENSAO;
        Job newJob = new Job(30, 30, 300.00, LocalDate.now(), "Nenhum", "Vaga phoebus teste 1",
                "Teste", LocalDate.of(2023, 8, 5), teacher1, typeJob,
                "Ayty", "Teste");
        newJob.setId(1L);

        TypeJob typeJob2 = TypeJob.PROJETO_DE_EXTENSAO;

        Job newJob2 = new Job(30, 30, 300.00, LocalDate.now(), "Nenhum", "Vaga phoebus",
                "Teste", LocalDate.of(2023, 8, 5), teacher1, typeJob2,
                "Ayty", "Teste");
        newJob2.setId(2L);

        List<Job> listJobs = Arrays.asList(newJob, newJob2);

        when(jobRepository.findAll()).thenReturn(listJobs);
        List<JobResponseDTO> responseDTOS = jobService.getAllJobs();

        assertNotNull(responseDTOS);
        assertEquals(2, responseDTOS.size());

        JobResponseDTO responseDTO1 = responseDTOS.get(0);
        assertEquals(1L, responseDTO1.getId());
        assertEquals("Ayty", responseDTO1.getNameProject());
        assertEquals(30, responseDTO1.getNumberVacancies());
        assertEquals(30, responseDTO1.getHoursWeek());
        assertEquals(300.00, responseDTO1.getScholarshipValue());
        assertEquals("Nenhum", responseDTO1.getBenefits());
        assertEquals("Vaga phoebus teste 1", responseDTO1.getTitleJob());


        JobResponseDTO responseDTO2 = responseDTOS.get(1);
        assertEquals(2L, responseDTO2.getId());
        assertEquals("Ayty", responseDTO2.getNameProject());
        assertEquals(30, responseDTO2.getNumberVacancies());
        assertEquals(30, responseDTO2.getHoursWeek());
        assertEquals(300.00, responseDTO2.getScholarshipValue());
        assertEquals("Nenhum", responseDTO2.getBenefits());
        assertEquals("Vaga phoebus", responseDTO2.getTitleJob());

        verify(jobRepository, times(1)).findAll();
    }

    @Test
    public void testCreateJobSuccess() {
        // Mocking Teacher and JobRequestDTO
        Teacher teacher = new Teacher();
        JobRequestDTO jobRequestDTO = new JobRequestDTO();
        jobRequestDTO.setTeacherID(1L);
        jobRequestDTO.setNumberVacancies(10);
        // Set other properties of jobRequestDTO

        // Mocking the behavior of teacherRepository
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        // Mocking the behavior of jobRepository
        Job savedJob = new Job(); // create a mock saved Job object
        when(jobRepository.save(any(Job.class))).thenReturn(savedJob);

        // Call the method under test
        JobResponseDTO result = jobService.createJob(jobRequestDTO);

        // Assertions
        assertNotNull(result);
        // You can add more assertions based on the expected behavior

        // Verify interactions
        verify(teacherRepository, times(1)).findById(1L);
        verify(jobRepository, times(1)).save(any(Job.class));
    }

    @Test
    public void testCreateJobTeacherNotFound() {
        // Mocking JobRequestDTO
        JobRequestDTO jobRequestDTO = new JobRequestDTO();
        jobRequestDTO.setTeacherID(1L);
        jobRequestDTO.setNumberVacancies(10);
        // Set other properties of jobRequestDTO

        // Mocking the behavior of teacherRepository
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the method under test and expect an exception
        assertThrows(NotFoundTeacherException.class, () -> jobService.createJob(jobRequestDTO));

        // Verify interactions
        verify(teacherRepository, times(1)).findById(1L);
        verify(jobRepository, never()).save(any(Job.class));
    }
}
