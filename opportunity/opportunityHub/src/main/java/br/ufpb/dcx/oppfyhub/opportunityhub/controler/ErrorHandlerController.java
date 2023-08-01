package br.ufpb.dcx.oppfyhub.opportunityhub.controler;

import br.ufpb.dcx.oppfyhub.opportunityhub.execption.NotFoundTeacherException;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.TeacherAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.ufpb.dcx.lab1v1.dto.DetailsProblemDTO;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(TeacherAlreadyExistsException.class)
    @ResponseStatus(code= HttpStatus.CONFLICT)
    public DetailsProblemDTO handlerTeacherAlreadyExistsException(ServletWebRequest request, TeacherAlreadyExistsException err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(HttpStatus.CONFLICT.value())
                .detail(err.getDetails())
                .title(err.getTitle())
                .build();

        return detailsProblemDTO;
    }

    @ExceptionHandler(NotFoundTeacherException.class)
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public DetailsProblemDTO handlerNotFoundTeacherException(ServletWebRequest request, NotFoundTeacherException err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(HttpStatus.NOT_FOUND.value())
                .detail(err.getDetails())
                .title(err.getTitle())
                .build();

        return detailsProblemDTO;
    }
}
