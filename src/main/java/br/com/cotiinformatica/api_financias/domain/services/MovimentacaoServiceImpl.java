package br.com.cotiinformatica.api_financias.domain.services;

import br.com.cotiinformatica.api_financias.domain.dto.CategoriaResponse;
import br.com.cotiinformatica.api_financias.domain.dto.MovimentacaoRequest;
import br.com.cotiinformatica.api_financias.domain.dto.MovimentacaoResponse;
import br.com.cotiinformatica.api_financias.domain.entities.Categoria;
import br.com.cotiinformatica.api_financias.domain.entities.Movimentacao;
import br.com.cotiinformatica.api_financias.domain.enums.TipoMovimentacao;
import br.com.cotiinformatica.api_financias.domain.interfaces.MovimentacaoService;
import br.com.cotiinformatica.api_financias.infrastructure.repositories.CategoriaRepository;
import br.com.cotiinformatica.api_financias.infrastructure.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public MovimentacaoResponse criar(MovimentacaoRequest request) {

        var movimentacao = new Movimentacao();

        movimentacao.setDescricao(request.descricao());
        movimentacao.setDataHora(request.dataHora());
        movimentacao.setValor(BigDecimal.valueOf(request.valor()));
        movimentacao.setTipo(
                TipoMovimentacao.valueOf(request.tipo().toUpperCase())
        );

        var categorias = categoriaRepository.findAllById(
                Arrays.asList(request.categoriasId())
        );

        movimentacao.setCategorias(categorias);

        movimentacaoRepository.save(movimentacao);

        return converterParaResponse(movimentacao);
    }

    @Override
    public MovimentacaoResponse alterar(
            UUID id,
            MovimentacaoRequest request) {

        var movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Movimentação não encontrada para edição."
                        ));

        movimentacao.setDescricao(request.descricao());
        movimentacao.setDataHora(request.dataHora());
        movimentacao.setValor(BigDecimal.valueOf(request.valor()));
        movimentacao.setTipo(
                TipoMovimentacao.valueOf(request.tipo().toUpperCase())
        );

        var categorias = categoriaRepository.findAllById(
                Arrays.asList(request.categoriasId())
        );

        movimentacao.setCategorias(categorias);

        movimentacaoRepository.save(movimentacao);

        return converterParaResponse(movimentacao);
    }

    @Override
    public MovimentacaoResponse excluir(UUID id) {

        var movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Movimentação não encontrada para exclusão."
                        ));

        var response = converterParaResponse(movimentacao);

        movimentacaoRepository.delete(movimentacao);

        return response;
    }

    @Override
    public Page<MovimentacaoResponse> consultar(int index, int size) {

        if (size > 25) {
            size = 25;
        }

        var paginacao = PageRequest.of(
                index,
                size,
                Sort.by("dataHora").descending()
        );

        return movimentacaoRepository
                .findAll(paginacao)
                .map(this::converterParaResponse);
    }

    @Override
    public MovimentacaoResponse consultarPorId(UUID id) {

        var movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Movimentação não encontrada."
                        ));

        return converterParaResponse(movimentacao);
    }

    private MovimentacaoResponse converterParaResponse(
            Movimentacao movimentacao) {

        var categorias = movimentacao.getCategorias()
                .stream()
                .map(categoria -> new CategoriaResponse(
                        categoria.getId(),
                        categoria.getNome()
                ))
                .toList();

        return new MovimentacaoResponse(
                movimentacao.getId(),
                movimentacao.getDescricao(),
                movimentacao.getValor().doubleValue(),
                movimentacao.getDataHora(),
                movimentacao.getTipo().toString(),
                categorias
        );
    }
}