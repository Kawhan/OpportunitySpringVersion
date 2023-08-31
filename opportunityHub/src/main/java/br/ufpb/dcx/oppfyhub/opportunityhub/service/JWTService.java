package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.LoginUserDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.ResponseLoginDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.InvalidLoginException;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.InvalidTokenException;
import br.ufpb.dcx.oppfyhub.opportunityhub.filter.FilterTokenJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Autowired
    private UserService userService;
    public static final String TOKEN_KEY = "wdsjfhkwbfdgwuierhweij";

    public ResponseLoginDTO authenticate(LoginUserDTO usuario) {
        if (!userService.validateUserPassword(usuario)) {
            throw new InvalidLoginException();
        }

        String token = generateToken(usuario.getEmail());
        return new ResponseLoginDTO(token);
    }

    private String generateToken(String email) {
        return Jwts.builder().setHeaderParam("typ", "JWT")
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();// 3 min
    }

    public String getTokenSubject(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException();
        }

        // Extraindo apenas o token do cabecalho.
        String token = authorizationHeader.substring(FilterTokenJWT.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new InvalidTokenException();
        }
        return subject;
    }
}
