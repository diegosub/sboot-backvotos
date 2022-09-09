package br.com.desafio.backvotos.infrastructure;

import java.util.Collection;
import java.util.List;

import br.com.desafio.backvotos.infrastructure.pauta.persistence.PautaRepository;
import br.com.desafio.backvotos.infrastructure.resultado.persistence.ResultadoRepository;
import br.com.desafio.backvotos.infrastructure.voto.persistence.VotoRepository;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class CleanUpExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(final ExtensionContext context) {
        final var appContext = SpringExtension.getApplicationContext(context);

        cleanUp(List.of(
                appContext.getBean(PautaRepository.class),
                appContext.getBean(VotoRepository.class),
                appContext.getBean(ResultadoRepository.class)
        ));
    }

    private void cleanUp(final Collection<CrudRepository> repositories) {
        repositories.forEach(CrudRepository::deleteAll);
    }
}