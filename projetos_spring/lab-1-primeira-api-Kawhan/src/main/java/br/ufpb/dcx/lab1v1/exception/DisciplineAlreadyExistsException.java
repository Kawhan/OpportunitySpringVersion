package br.ufpb.dcx.lab1v1.exception;


public class DisciplineAlreadyExistsException extends AlreadyExistsException{
    public DisciplineAlreadyExistsException() {
        super("Disciplina já existe!");
    }
}
