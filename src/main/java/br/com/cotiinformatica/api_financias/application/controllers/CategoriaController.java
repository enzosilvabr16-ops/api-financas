package br.com.cotiinformatica.api_financias.application.controllers;

import br.com.cotiinformatica.api_financias.domain.dto.CategoriaRequest;
import br.com.cotiinformatica.api_financias.domain.dto.CategoriaResponse;
import br.com.cotiinformatica.api_financias.domain.interfaces.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponse> post(@RequestBody CategoriaRequest request) {
        var response = categoriaService.criar(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoriaResponse> put(@PathVariable UUID id, @RequestBody CategoriaRequest request) {
        var response = categoriaService.alterar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CategoriaResponse> delete(@PathVariable UUID id) {
        var response = categoriaService.excluir(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaResponse>> getAll(@RequestParam int index,@RequestParam int size) {
        var response = categoriaService.consultar(index, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponse> consultarPorId(UUID id) {
        var response = categoriaService.consultarPorId(id);
        return ResponseEntity.ok(response);
    }
}
