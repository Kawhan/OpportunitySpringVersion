package br.ufpb.dcx.lab1v1.exception;

public class DisciplineNotFoundException extends NotFoundException{
    public DisciplineNotFoundException(){
        super("Disciplina não encontrada!");
    }

}
