package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotFoundUserException extends NotFoundException{
    public NotFoundUserException() {
        super("Teacher not found", "Teacher not registered in the system");
    }
}
