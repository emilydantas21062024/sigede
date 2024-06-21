package br.com.projecao.sigede.excecao;

import org.springframework.http.HttpStatus;


public class AplicacaoException extends RuntimeException {

    private final HttpStatus status;

    public AplicacaoException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}