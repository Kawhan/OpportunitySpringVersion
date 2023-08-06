package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Builder
@Getter
@Setter
@ResponseStatus(code= HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException{
    private String title;
    private String details;

    public AlreadyExistsException(String details, String title) {
        this.title = title;
        this.details = details;
    }
}
