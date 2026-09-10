package br.com.cotiinformatica.api_financias.application.controllers;

import br.com.cotiinformatica.api_financias.domain.dto.CategoriaRequest;
import br.com.cotiinformatica.api_financias.domain.dto.CategoriaResponse;
import br.com.cotiinformatica.api_financias.domain.dto.MovimentacaoRequest;
import br.com.cotiinformatica.api_financias.domain.dto.MovimentacaoResponse;
import br.com.cotiinformatica.api_financias.domain.entities.Movimentacao;
import br.com.cotiinformatica.api_financias.domain.interfaces.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<MovimentacaoResponse> post(@RequestBody MovimentacaoRequest request) {
        var response = movimentacaoService.criar(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<MovimentacaoResponse> put(@PathVariable UUID id, @RequestBody MovimentacaoRequest request) {
        var response = movimentacaoService.alterar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MovimentacaoResponse> delete(@PathVariable UUID id) {
        var response = movimentacaoService.excluir(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<Page<MovimentacaoResponse>> getAll(@RequestParam int index, @RequestParam int size) {
        var response = movimentacaoService.consultar(index, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovimentacaoResponse> getById(@PathVariable UUID id) {
        var response = movimentacaoService.consultarPorId(id);
        return ResponseEntity.ok(response);
    }
}
