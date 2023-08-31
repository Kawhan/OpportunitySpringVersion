package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.User;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundUserException;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.UserRepository;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userServiceTest;

    @Test
    public void testListAllTeachers() {
        // Criação de um mock da lista de professores
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Professor 1");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Professor 2");

        List<User> users = Arrays.asList(user1, user2);

        // Define o comportamento esperado do repositório mock quando o método findAll for chamado
        when(userRepository.findAll()).thenReturn(users);
        List<UserResponseDTO> responseDTOs = userServiceTest.listAllTeachers();

        // Verifica se a resposta do serviço é a esperada
        assertNotNull(responseDTOs);
        assertEquals(2, responseDTOs.size());

        UserResponseDTO responseDTO1 = responseDTOs.get(0);
        assertEquals(1L, responseDTO1.getId());
        assertEquals("Professor 1", responseDTO1.getTeacherName());

        UserResponseDTO responseDTO2 = responseDTOs.get(1);
        assertEquals(2L, responseDTO2.getId());
        assertEquals("Professor 2", responseDTO2.getTeacherName());

        // Verifica se o método findAll do repositório mock foi chamado uma vez
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void addTeacher() {
        UserRequestDTO requestDTO = new UserRequestDTO();

        requestDTO.setTeacherName("Novo professor");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Novo professor");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserResponseDTO responseDTO = userServiceTest.addUser(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(1L, responseDTO.getId());
        assertEquals("Novo professor", responseDTO.getTeacherName());

        // Verifica se o método save do repositório mock foi chamado uma vez com os parâmetros corretos
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetTeacherExistingId() {
        long teacherId = 1L;

        User user = new User();
        user.setId(teacherId);
        user.setName("Professor Existente");

        when(userRepository.findById(teacherId)).thenReturn(Optional.of(user));

        UserResponseDTO responseDTO = userServiceTest.getUser(teacherId);

        assertNotNull(responseDTO);
        assertEquals(teacherId, responseDTO.getId());
        assertEquals("Professor Existente", responseDTO.getTeacherName());

        verify(userRepository, times(1)).findById(teacherId);
    }

    @Test
    public void testGetTeacherNonExistingId() {
        // Dados de entrada para o teste
        long teacherId = 2L;

        // Configura o mock para retornar um Optional vazio
        when(userRepository.findById(teacherId)).thenReturn(Optional.empty());

        // Verifica se a exceção NotFoundTeacherException é lançada ao chamar o método
        assertThrows(NotFoundUserException.class, () -> {
            userServiceTest.getUser(teacherId);
        });

        // Verifica se o método findById do repositório mock foi chamado uma vez com o ID correto
        verify(userRepository, times(1)).findById(teacherId);
    }

    @Test
    void testChangeNameTeacherFound() {
        long teacherId = 1L;
        String newName = "New Teacher Name";

        // Mocking
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setTeacherName(newName);

        User existingUser = new User("Old Teacher Name");
        when(userRepository.findById(teacherId)).thenReturn(Optional.of(existingUser));

        User savedUser = new User(newName);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Calling the service method
        UserResponseDTO responseDTO = userServiceTest.changeNameUser(teacherId, requestDTO);

        // Assertions
        assertNotNull(responseDTO);
        assertEquals(newName, responseDTO.getTeacherName());
    }

    @Test
    void testChangeNameTeacherNotFound() {
        long teacherId = 1L;

        // Mocking
        when(userRepository.findById(teacherId)).thenReturn(Optional.empty());

        // Calling the service method and expecting an exception
        assertThrows(NotFoundUserException.class, () -> userServiceTest.changeNameUser(teacherId, new UserRequestDTO()));
    }

    @Test
    void testDeleteTeacher() {
        long teacherId = 1L;

        // Mocking
        User userToDelete = new User("Teacher to Delete");
        when(userRepository.findById(teacherId)).thenReturn(Optional.of(userToDelete));

        // Calling the service method
        userServiceTest.deleteUser(teacherId);

        // Verifying interactions
        verify(userRepository, times(1)).findById(teacherId); // Verify findById was called once
        verify(userRepository, times(1)).delete(userToDelete); // Verify delete was called once
    }

    @Test
    void testDeleteTeacherNotFound() {
        long teacherId = 1L;

        // Mocking
        when(userRepository.findById(teacherId)).thenReturn(Optional.empty());

        // Calling the service method and expecting an exception
        assertThrows(NotFoundUserException.class, () -> userServiceTest.deleteUser(teacherId));

        // Verifying interactions
        verify(userRepository, times(1)).findById(teacherId); // Verify findById was called once
        verify(userRepository, never()).delete(any(User.class)); // Verify delete was never called
    }
}
