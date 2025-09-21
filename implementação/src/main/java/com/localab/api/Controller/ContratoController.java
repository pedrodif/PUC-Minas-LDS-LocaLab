package com.localab.api.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.localab.api.DTO.ContratoDTO;
import com.localab.api.DTO.ContratoRequestDTO;
import com.localab.api.Service.ContratoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/contratos")
@Tag(name = "Contratos", description = "API para gerenciamento de contratos do sistema LocaLab")
public class ContratoController {
    
    @Autowired
    private ContratoService contratoService;
    
    @GetMapping
    @Operation(summary = "Listar todos os contratos", description = "Retorna uma lista com todos os contratos cadastrados no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de contratos retornada com sucesso")
    })
    public ResponseEntity<List<ContratoDTO>> listarTodos() {
        List<ContratoDTO> contratos = contratoService.listarTodos();
        return ResponseEntity.ok(contratos);
    }
    
    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar contratos por usuário", description = "Retorna contratos de um usuário específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de contratos do usuário retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<List<ContratoDTO>> listarPorUsuario(
            @Parameter(description = "ID do usuário") @PathVariable Long usuarioId) {
        List<ContratoDTO> contratos = contratoService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(contratos);
    }
    
    @PostMapping
    @Operation(summary = "Criar novo contrato", description = "Cria um novo contrato no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contrato criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ContratoDTO> criar(@RequestBody ContratoRequestDTO contratoRequestDTO) {
        try {
            ContratoDTO contratoCriado = contratoService.criar(contratoRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(contratoCriado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar contrato", description = "Atualiza os dados de um contrato existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrato atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ContratoDTO> atualizar(
            @Parameter(description = "ID do contrato") @PathVariable Long id, 
            @RequestBody ContratoRequestDTO contratoRequestDTO) {
        Optional<ContratoDTO> contratoAtualizado = contratoService.atualizar(id, contratoRequestDTO);
        return contratoAtualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar contrato", description = "Remove um contrato do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contrato deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contrato não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do contrato") @PathVariable Long id) {
        boolean deletado = contratoService.deletar(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
