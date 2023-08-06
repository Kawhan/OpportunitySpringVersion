package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

public class JobNotFoundException extends NotFoundException{
    public JobNotFoundException(){
        super("Job not found", "Job not registered in the system");
    }
}
