package br.com.cotiinformatica.api_financias.domain.services;

import br.com.cotiinformatica.api_financias.domain.dto.CategoriaRequest;
import br.com.cotiinformatica.api_financias.domain.dto.CategoriaResponse;
import br.com.cotiinformatica.api_financias.domain.entities.Categoria;
import br.com.cotiinformatica.api_financias.domain.interfaces.CategoriaService;
import br.com.cotiinformatica.api_financias.infrastructure.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public CategoriaResponse criar(CategoriaRequest request) {
        var categoria = new Categoria();
        categoria.setNome(request.nome());
        categoriaRepository.save(categoria);
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome()
        );
    }

    @Override
    public CategoriaResponse alterar(UUID id, CategoriaRequest request) {
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada para edição."));

        categoria.setNome(request.nome());
        categoriaRepository.save(categoria);

        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome()
        );
    }

    @Override
    public CategoriaResponse excluir(UUID id) {

        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada para exclusão."));
        categoriaRepository.delete(categoria);

        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome()
        );
    }

    @Override
    public Page<CategoriaResponse> consultar(int index, int size) {
        if (size > 25) size = 25;
        var paginacao = PageRequest.of(index, size, Sort.by("nome").ascending());

        var categorias = categoriaRepository.findAll(paginacao);

        return categorias.map(
                categoria -> new CategoriaResponse(
                        categoria.getId(),
                        categoria.getNome()
                )
        );
    }

    @Override
    public CategoriaResponse consultarPorId(UUID id) {
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));

        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome()
        );
    }
}
