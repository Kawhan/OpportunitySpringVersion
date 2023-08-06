package br.ufpb.dcx.oppfyhub.opportunityhub.controler;

import br.ufpb.dcx.oppfyhub.opportunityhub.execption.HTTPErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.ufpb.dcx.lab1v1.dto.DetailsProblemDTO;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetailsProblemDTO> handlerGeneralError(ServletWebRequest request, Exception err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .detail(err.getMessage())
                .title(err.getCause().getMessage())
                .build();
        return new ResponseEntity<>(detailsProblemDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HTTPErrorException.class)
    public ResponseEntity<DetailsProblemDTO> handlerHTTPError(ServletWebRequest request, HTTPErrorException err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(err.getStatusCode().value())
                .detail(err.getDetails())
                .title(err.getTitle())
                .build();
        return new ResponseEntity<>(detailsProblemDTO, err.getStatusCode());
    }
}
