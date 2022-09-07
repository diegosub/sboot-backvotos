package br.com.desafio.backvotos.domain.voto;

import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.domain.validation.Validator;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;

public class VotoValidation extends Validator {

    private final Voto voto;

    private final String MSG_ERRO_VALIDACAO = "Erro na validação do voto";

    protected VotoValidation(final Voto voto, final ValidationHandler handler) {
        super(handler);
        this.voto = voto;
    }

    @Override
    public void validate() {
        validarPauta();
        validarCpf();
        validarValor();

        if(!this.validationHandler().getErrors().isEmpty()) {
            throw DomainException.with(MSG_ERRO_VALIDACAO, this.validationHandler().getErrors());
        }
    }

    private void validarPauta() {
        final var idPauta = this.voto.getIdPauta();

        if(idPauta == null) {
            this.validationHandler().append("'idPauta' não pode ser null");
        }

        if(idPauta != null && idPauta.trim().equals("")) {
            this.validationHandler().append("'idPauta' não pode ser empty");
        }
    }

    private void validarCpf() {
        final var cpf = this.voto.getCpf();

        if(cpf == null) {
            this.validationHandler().append("'cpf' não pode ser null");
        }

        if(cpf != null && cpf.trim().equals("")) {
            this.validationHandler().append("'cpf' não pode ser empty");
        }
    }

    private void validarValor() {
        final var valor = this.voto.getValor();

        if(valor == null) {
            this.validationHandler().append("'valor' não pode ser null");
        }
    }
}
