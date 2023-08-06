package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotFoundTeacherException extends NotFoundException{
    public NotFoundTeacherException() {
        super("Teacher not found", "Teacher not registered in the system");
    }
}
