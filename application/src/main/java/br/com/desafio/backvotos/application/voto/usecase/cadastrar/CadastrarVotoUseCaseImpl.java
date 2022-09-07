package br.com.desafio.backvotos.application.voto.usecase.cadastrar;

import java.time.Instant;
import br.com.desafio.backvotos.application.voto.dto.CadastrarVotoInput;
import br.com.desafio.backvotos.application.voto.dto.VotoOutput;
import br.com.desafio.backvotos.domain.exception.DomainException;
import br.com.desafio.backvotos.domain.exception.NotFoundException;
import br.com.desafio.backvotos.domain.pauta.Pauta;
import br.com.desafio.backvotos.domain.pauta.PautaGateway;
import br.com.desafio.backvotos.domain.validation.handler.ValidationHandler;
import br.com.desafio.backvotos.domain.voto.Voto;
import br.com.desafio.backvotos.domain.voto.VotoGateway;

public class CadastrarVotoUseCaseImpl implements CadastrarVotoUseCase {

    private final VotoGateway votoGateway;
    private final PautaGateway pautaGateway;

    public CadastrarVotoUseCaseImpl(final VotoGateway votoGateway, final PautaGateway pautaGateway) {
        this.votoGateway = votoGateway;
        this.pautaGateway = pautaGateway;
    }

    @Override
    public VotoOutput execute(CadastrarVotoInput cadastrarVotoInput) {
        var idPauta = cadastrarVotoInput.idPauta();
        var cpf = cadastrarVotoInput.cpf();
        var valor = cadastrarVotoInput.valor();

        this.validarPauta(idPauta);
        this.validarCpf(cpf);
        this.validarVoto(cpf, idPauta);

        var voto = Voto.create(idPauta, cpf, valor);
        voto.validate(new ValidationHandler());

        var retorno = votoGateway.insert(voto);
        return VotoOutput.toOutput(retorno);
    }

    private void validarPauta(String idPauta) {
        var pauta = pautaGateway.getById(idPauta)
                .orElseThrow(() -> NotFoundException.with(Pauta.class, idPauta));

        if(pauta.getDataFim() == null || pauta.getDataFim().isBefore(Instant.now())) {
            throw DomainException.with("A sessão já foi finalizada.");
        }
    }

    private void validarCpf(String cpf) {
        var isValidCpf = votoGateway.isValidCpf(cpf);

        if(!isValidCpf) {
            throw DomainException.with("Associado não tem permissão para votar.");
        }
    }

    private void validarVoto(String cpf, String idPauta) {
        var count = votoGateway.getCountCpfByIdPauta(cpf, idPauta);

        if(count > 0) {
            throw DomainException.with("O Associado já votou nessa pauta.");
        }
    }
}
