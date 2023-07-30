package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

public class TeacherAlreadyExistsException extends AlreadyExistsException{
    public TeacherAlreadyExistsException() {
        super("O professor já foi cadastrado");
    }
}
