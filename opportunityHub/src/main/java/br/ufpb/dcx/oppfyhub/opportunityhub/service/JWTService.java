package br.ufpb.dcx.oppfyhub.opportunityhub.service;


import br.ufpb.dcx.oppfyhub.opportunityhub.execption.InvalidTokenException;
import br.ufpb.dcx.oppfyhub.opportunityhub.filter.FilterTokenJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService {
    public static final Key TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    public String generateToken(String email) {
        return Jwts.builder().setHeaderParam("typ", "JWT")
                .setSubject(email)
                .signWith(TOKEN_KEY, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();// 3 min
    }

    public String getTokenSubject(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException();
        }

        // Extraindo apenas o token do cabeçalho.
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
