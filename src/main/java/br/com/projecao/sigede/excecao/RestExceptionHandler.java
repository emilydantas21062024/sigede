package br.com.projecao.sigede.excecao;

import br.com.projecao.sigede.dto.ErroDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  RestExceptionHandler
 *  Classe de configuração para tratamento de exceções
 */
@ControllerAdvice
public class RestExceptionHandler {

    /**
     *   Método que trata exceções do tipo AplicacaoException
     *
     * @param ex - Exceção do tipo AplicacaoException
     * @return ResponseEntity<ErroDto> - Objeto de resposta com a mensagem de erro
     */
    @ExceptionHandler(value = { AplicacaoException.class })
    @ResponseBody
    public ResponseEntity<ErroDto> handleException(AplicacaoException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErroDto(ex.getMessage()));
    }
}