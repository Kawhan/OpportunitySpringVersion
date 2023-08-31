package br.ufpb.dcx.oppfyhub.opportunityhub.controler;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/teachers")
public class UserController {
    @Autowired
    UserService userService;


    // Gets

    @GetMapping("{id}")

    public UserResponseDTO getTeacher(@PathVariable long id) {
        return userService.getUser(id);
    }

    @GetMapping("/auth/usuarios/{email}")
    @ResponseStatus(code=HttpStatus.OK)
    public UserResponseDTO getUser(@PathVariable String email,
                                   @RequestHeader("Authorization") String header) {
       return userService.getUserByEmail(email, header);
    }



    // Posts

    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public UserResponseDTO addUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.addUser(userRequestDTO);
    }



    // Deletes
    @DeleteMapping("{id}/delete")
    @ResponseStatus(code=HttpStatus.OK)
    public UserResponseDTO deleteTeacher(@PathVariable long id) {
        return userService.deleteUser(id);
    }

}
