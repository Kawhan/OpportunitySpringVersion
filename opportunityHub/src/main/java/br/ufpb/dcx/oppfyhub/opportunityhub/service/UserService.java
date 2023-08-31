package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.LoginUserDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.User;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotAuthorizedException;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundUserException;
import br.ufpb.dcx.oppfyhub.opportunityhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        User newUser = new User(
                userRequestDTO.getEmail(),
                userRequestDTO.getName(),
                userRequestDTO.getPassword(),
                userRequestDTO.getRoleUser()
        );
        User user = userRepository.save(newUser);
        return UserResponseDTO.from(user);
    }

    public UserResponseDTO getUserByEmail(String email, String authHeader) {
        Optional<User> userFound = userRepository.findByEmail(email);
        if (!userFound.isEmpty() && userHasPermission(authHeader, email)) {
            return UserResponseDTO.from(userFound.get());
        }
        throw new NotAuthorizedException();
    }

    public boolean validateUserPassword(LoginUserDTO user) {
        Optional<User> userFound = userRepository.findByEmail(user.getEmail());
        if (userFound.isPresent() && userFound.get().getPassword().equals(user.getPassword()))
            return true;
        return false;
    }

    public UserResponseDTO getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundUserException();
        }
        return UserResponseDTO.from(user.get());
    }

    private boolean userHasPermission(String authorizationHeader, String email) {
        String subject = jwtService.getTokenSubject(authorizationHeader);
        Optional<User> userFound = userRepository.findByEmail(subject);
        return userFound.isPresent() && userFound.get().getEmail().equals(email);
    }

    public UserResponseDTO deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundUserException();
        }
        userRepository.delete(user.get());
        return UserResponseDTO.from(user.get());
    }
}
