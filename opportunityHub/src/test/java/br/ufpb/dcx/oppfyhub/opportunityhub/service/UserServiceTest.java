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
}
