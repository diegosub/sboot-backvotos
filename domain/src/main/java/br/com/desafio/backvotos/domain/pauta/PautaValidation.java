package br.com.desafio.backvotos.domain.pauta;

import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.domain.validation.Validator;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;

public class PautaValidation extends Validator {

    private final Pauta pauta;
    private final int MIN_NOME_LENGTH = 3;
    private final int MAX_NOME_LENGTH = 255;

    private final String MSG_ERRO_VALIDACAO = "Erro na validação do voto";

    protected PautaValidation(final Pauta pauta, final ValidationHandler handler) {
        super(handler);
        this.pauta = pauta;
    }

    @Override
    public void validate() {
        validateName();
        validateData();

        if(!this.validationHandler().getErrors().isEmpty()) {
            throw DomainException.with(MSG_ERRO_VALIDACAO, this.validationHandler().getErrors());
        }
    }

    private void validateName() {
        final var nome = this.pauta.getNome();

        if(nome == null) {
            this.validationHandler().append("'nome' não pode ser null");
        }

        if(nome != null && nome.trim().isEmpty()) {
            this.validationHandler().append("'nome' não pode ser empty");
        }

        if(nome != null && (nome.trim().length() > MAX_NOME_LENGTH || nome.trim().length() < MIN_NOME_LENGTH)) {
            this.validationHandler().append("'nome' deve conter entre 3 e 255 caracteres");
        }
    }

    private void validateData() {
        final var dataInicio = this.pauta.getDataInicio();
        final var dataFim = this.pauta.getDataFim();

        if((dataFim != null && dataInicio == null) || (dataFim == null && dataInicio != null)) {
            this.validationHandler().append("Caso uma data esteja preenchida, a outra não pode ser nula.");
        }

        if(dataInicio != null && dataFim != null && dataFim.isBefore(dataInicio)) {
            this.validationHandler().append("Data Fim não pode ser maior que a Data Inicio.");
        }
    }
}
