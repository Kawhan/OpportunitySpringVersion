package br.ufpb.dcx.oppfyhub.opportunityhub.dto;

import br.ufpb.dcx.oppfyhub.opportunityhub.enums.RoleUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("email")
    @NotNull
    private String email;

    @JsonProperty("password")
    @NotNull
    private String password;

    @JsonProperty("roleUser")
    @NotNull
    private RoleUser roleUser;
}
