package br.com.cotiinformatica.api_financias.domain.interfaces;


import br.com.cotiinformatica.api_financias.domain.dto.CategoriaRequest;
import br.com.cotiinformatica.api_financias.domain.dto.CategoriaResponse;
import br.com.cotiinformatica.api_financias.domain.entities.Categoria;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CategoriaService {

    CategoriaResponse criar(CategoriaRequest request);

    CategoriaResponse alterar(UUID id, CategoriaRequest request);

    CategoriaResponse excluir(UUID id);

    Page<CategoriaResponse> consultar(int index, int size);

    CategoriaResponse consultarPorId(UUID id);
}
