package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

public class TeacherAlreadyExistsException extends AlreadyExistsException{
    public TeacherAlreadyExistsException() {
        super("Teacher already exists", "Teacher already registered in the system");
    }
}
