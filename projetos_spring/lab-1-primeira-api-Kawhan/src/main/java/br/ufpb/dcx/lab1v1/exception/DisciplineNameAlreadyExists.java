package br.ufpb.dcx.lab1v1.exception;

public class DisciplineNameAlreadyExists extends AlreadyExistsException{
    public DisciplineNameAlreadyExists(){
        super("Nome de disciplina já cadastrado em outra disciplina");
    }
}
