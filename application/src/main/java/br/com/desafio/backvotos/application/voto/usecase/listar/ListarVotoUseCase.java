package br.com.desafio.backvotos.application.voto.usecase.listar;

import br.com.desafio.backvotos.application.UseCase;
import br.com.desafio.backvotos.application.voto.dto.VotoOutput;

import java.util.List;

public interface ListarVotoUseCase extends UseCase<String, List<VotoOutput>> {
}
