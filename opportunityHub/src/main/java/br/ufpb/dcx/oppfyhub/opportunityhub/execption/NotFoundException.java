package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Builder
@Getter
@Setter
@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    private String title;
    private String details;

    public NotFoundException(String details, String title) {
        this.title = title;
        this.details = details;
    }
}
