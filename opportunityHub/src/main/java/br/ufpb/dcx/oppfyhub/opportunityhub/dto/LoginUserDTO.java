package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginUserDTO {
    private String email;
    private String password;

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoginUserDTO other = (LoginUserDTO) obj;
        return Objects.equals(email, other.email);
    }
}
