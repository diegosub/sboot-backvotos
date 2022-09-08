package br.com.desafio.backvotos.domain.resultado;

import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.validation.Validator;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;

public class ResultadoValidation extends Validator {

    private final Resultado resultado;
    private final String MSG_ERRO_VALIDACAO = "Erro na validação do resultado";

    protected ResultadoValidation(final Resultado resultado, final ValidationHandler handler) {
        super(handler);
        this.resultado = resultado;
    }

    @Override
    public void validate() {
        validatePauta();

        if(!this.validationHandler().getErrors().isEmpty()) {
            throw DomainException.with(MSG_ERRO_VALIDACAO, this.validationHandler().getErrors());
        }
    }

    private void validatePauta() {
        final var idPauta = this.resultado.getIdPauta();

        if(idPauta == null) {
            this.validationHandler().append("'idPauta' não pode ser null");
        }

        if(idPauta != null && idPauta.trim().isEmpty()) {
            this.validationHandler().append("'idPauta' não pode ser empty");
        }
    }
}
