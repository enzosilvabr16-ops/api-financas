package br.com.cotiinformatica.api_financias.domain.interfaces;

import br.com.cotiinformatica.api_financias.domain.dto.CategoriaRequest;
import br.com.cotiinformatica.api_financias.domain.dto.CategoriaResponse;
import br.com.cotiinformatica.api_financias.domain.dto.MovimentacaoRequest;
import br.com.cotiinformatica.api_financias.domain.dto.MovimentacaoResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface MovimentacaoService {

    MovimentacaoResponse criar(MovimentacaoRequest request);

    MovimentacaoResponse alterar(UUID id, MovimentacaoRequest request);

    MovimentacaoResponse excluir(UUID id);

    Page<MovimentacaoResponse> consultar(int index, int size);

    MovimentacaoResponse consultarPorId(UUID id);
}
