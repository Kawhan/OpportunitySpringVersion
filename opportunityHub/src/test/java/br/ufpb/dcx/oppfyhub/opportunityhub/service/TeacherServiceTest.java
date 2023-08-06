package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherNameRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.TeacherResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.Teacher;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundTeacherException;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.TeacherRepository;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherServiceTest;

    @Test
    public void testListAllTeachers() {
        // Criação de um mock da lista de professores
        Teacher teacher1 = new Teacher();
        teacher1.setId(1L);
        teacher1.setName("Professor 1");

        Teacher teacher2 = new Teacher();
        teacher2.setId(2L);
        teacher2.setName("Professor 2");

        List<Teacher> teachers = Arrays.asList(teacher1, teacher2);

        // Define o comportamento esperado do repositório mock quando o método findAll for chamado
        when(teacherRepository.findAll()).thenReturn(teachers);
        List<TeacherResponseDTO> responseDTOs = teacherServiceTest.listAllTeachers();

        // Verifica se a resposta do serviço é a esperada
        assertNotNull(responseDTOs);
        assertEquals(2, responseDTOs.size());

        TeacherResponseDTO responseDTO1 = responseDTOs.get(0);
        assertEquals(1L, responseDTO1.getId());
        assertEquals("Professor 1", responseDTO1.getTeacherName());

        TeacherResponseDTO responseDTO2 = responseDTOs.get(1);
        assertEquals(2L, responseDTO2.getId());
        assertEquals("Professor 2", responseDTO2.getTeacherName());

        // Verifica se o método findAll do repositório mock foi chamado uma vez
        verify(teacherRepository, times(1)).findAll();
    }

    @Test
    public void addTeacher() {
        TeacherNameRequestDTO requestDTO = new TeacherNameRequestDTO();

        requestDTO.setTeacherName("Novo professor");

        Teacher savedTeacher = new Teacher();
        savedTeacher.setId(1L);
        savedTeacher.setName("Novo professor");

        when(teacherRepository.save(any(Teacher.class))).thenReturn(savedTeacher);

        TeacherResponseDTO responseDTO = teacherServiceTest.addTeacher(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(1L, responseDTO.getId());
        assertEquals("Novo professor", responseDTO.getTeacherName());

        // Verifica se o método save do repositório mock foi chamado uma vez com os parâmetros corretos
        verify(teacherRepository, times(1)).save(any(Teacher.class));
    }

    @Test
    public void testGetTeacherExistingId() {
        long teacherId = 1L;

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setName("Professor Existente");

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));

        TeacherResponseDTO responseDTO = teacherServiceTest.getTeacher(teacherId);

        assertNotNull(responseDTO);
        assertEquals(teacherId, responseDTO.getId());
        assertEquals("Professor Existente", responseDTO.getTeacherName());

        verify(teacherRepository, times(1)).findById(teacherId);
    }

    @Test
    public void testGetTeacherNonExistingId() {
        // Dados de entrada para o teste
        long teacherId = 2L;

        // Configura o mock para retornar um Optional vazio
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.empty());

        // Verifica se a exceção NotFoundTeacherException é lançada ao chamar o método
        assertThrows(NotFoundTeacherException.class, () -> {
            teacherServiceTest.getTeacher(teacherId);
        });

        // Verifica se o método findById do repositório mock foi chamado uma vez com o ID correto
        verify(teacherRepository, times(1)).findById(teacherId);
    }
}
