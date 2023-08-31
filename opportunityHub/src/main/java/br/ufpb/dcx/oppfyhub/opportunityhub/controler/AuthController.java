package br.ufpb.dcx.oppfyhub.opportunityhub.controler;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.LoginUserDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.ResponseLoginDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.AuthenticationIntermediary;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationIntermediary authenticationIntermediary;

    @PostMapping("auth/login")
    @ResponseStatus(code= HttpStatus.OK)
    public ResponseLoginDTO auth(@RequestBody LoginUserDTO user) {
        return authenticationIntermediary.authenticate(user);
    }



}
