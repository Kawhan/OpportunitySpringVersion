package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.User;
import br.ufpb.dcx.oppfyhub.opportunityhub.enums.RoleUser;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.UserAlreadyExistsException;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


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

    @Mock
    private JWTService jwtService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testRegisterUserSuccess() {
        UserRequestDTO userRequestDTO = new UserRequestDTO("testUser", "test@gmail.com", "123", RoleUser.PROFESSOR);

        when(userRepository.findByEmail(userRequestDTO.getEmail())).thenReturn(Optional.empty());

        when(userRepository.save(any(User.class))).thenReturn(new User());

        UserResponseDTO resultDTO = userServiceTest.registerUser(userRequestDTO);

        assertNotNull(resultDTO);
        assertEquals("testUser", userRequestDTO.getName());
        assertEquals("test@gmail.com", userRequestDTO.getEmail());
        assertEquals(RoleUser.PROFESSOR, userRequestDTO.getRoleUser());
        assertEquals("123", userRequestDTO.getPassword());

        verify(userRepository, times(1)).findByEmail(userRequestDTO.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testRegisterUser_UserAlreadyExists() {
        UserRequestDTO userRequestDTO = new UserRequestDTO("existing@example.com", "Existing User", "password", RoleUser.PROFESSOR);

        when(userRepository.findByEmail(userRequestDTO.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistsException.class, () -> userServiceTest.registerUser(userRequestDTO));

        verify(userRepository, never()).save(any(User.class));
    }
}
