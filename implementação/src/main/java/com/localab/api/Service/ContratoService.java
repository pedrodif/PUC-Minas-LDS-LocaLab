package com.localab.api.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.localab.api.DTO.ContratoDTO;
import com.localab.api.DTO.ContratoRequestDTO;
import com.localab.api.DTO.EmpresaDTO;
import com.localab.api.DTO.UsuarioDTO;
import com.localab.api.DTO.AutomovelDTO;
import com.localab.api.Interface.ContratoMapper;
import com.localab.api.Interface.ContratoRequestMapper;
import com.localab.api.Interface.EmpresaMapper;
import com.localab.api.Interface.UsuarioMapper;
import com.localab.api.Interface.AutomovelMapper;
import com.localab.api.Model.Entity.Automovel;
import com.localab.api.Model.Entity.Contrato;
import com.localab.api.Model.Entity.Empresa;
import com.localab.api.Model.Entity.Usuario;
import com.localab.api.Repository.AutomovelRepository;
import com.localab.api.Repository.ContratoRepository;
import com.localab.api.Repository.EmpresaRepository;
import com.localab.api.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class ContratoService {
    
    @Autowired
    private ContratoRepository contratoRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AutomovelRepository automovelRepository;
    
    @Autowired
    private ContratoMapper contratoMapper;
    
    @Autowired
    private ContratoRequestMapper contratoRequestMapper;
    
    @Autowired
    private EmpresaMapper empresaMapper;
    
    @Autowired
    private UsuarioMapper usuarioMapper;
    
    @Autowired
    private AutomovelMapper automovelMapper;
    
    public List<ContratoDTO> listarTodos() {
        List<Contrato> contratos = contratoRepository.findAll();
        return contratos.stream()
                .map(this::mapearContratoCompleto)
                .toList();
    }
    
    public List<ContratoDTO> listarPorUsuario(Long usuarioId) {
        List<Contrato> contratos = contratoRepository.findByLocadorId(usuarioId);
        return contratos.stream()
                .map(this::mapearContratoCompleto)
                .toList();
    }
    
    public ContratoDTO criar(ContratoRequestDTO contratoRequestDTO) {
        // Buscar entidades relacionadas
        Empresa financiador = null;
        if (contratoRequestDTO.getFinanciadorId() != null) {
            financiador = empresaRepository.findById(contratoRequestDTO.getFinanciadorId())
                    .orElseThrow(() -> new EntityNotFoundException("Financiador não encontrado com ID: " + contratoRequestDTO.getFinanciadorId()));
        }
        
        Usuario locador = userRepository.findById(contratoRequestDTO.getLocadorId())
                .orElseThrow(() -> new EntityNotFoundException("Locador não encontrado com ID: " + contratoRequestDTO.getLocadorId()));
        
        Empresa locadora = empresaRepository.findById(contratoRequestDTO.getLocadoraId())
                .orElseThrow(() -> new EntityNotFoundException("Locadora não encontrada com ID: " + contratoRequestDTO.getLocadoraId()));
        
        Automovel automovel = automovelRepository.findById(contratoRequestDTO.getAutomovelId())
                .orElseThrow(() -> new EntityNotFoundException("Automóvel não encontrado com ID: " + contratoRequestDTO.getAutomovelId()));
        
        // Converter DTO para entidade
        Contrato contrato = contratoRequestMapper.toEntity(contratoRequestDTO);
        contrato.setFinanciador(financiador);
        contrato.setLocador(locador);
        contrato.setLocadora(locadora);
        contrato.setAutomovel(automovel);
        
        // Salvar
        Contrato contratoSalvo = contratoRepository.save(contrato);
        
        return mapearContratoCompleto(contratoSalvo);
    }
    
    public Optional<ContratoDTO> atualizar(Long id, ContratoRequestDTO contratoRequestDTO) {
        return contratoRepository.findById(id)
                .map(contrato -> {
                    // Atualizar dados básicos
                    contrato.setValorTotal(contratoRequestDTO.getValorTotal());
                    contrato.setDataInicial(contratoRequestDTO.getDataInicial());
                    contrato.setDataFinal(contratoRequestDTO.getDataFinal());
                    contrato.setPropriedade(contratoRequestDTO.getPropriedade());
                    contrato.setFaseAprovacao(contratoRequestDTO.getFaseAprovacao());
                    
                    // Atualizar relacionamentos se necessário
                    if (contratoRequestDTO.getFinanciadorId() != null) {
                        Empresa financiador = empresaRepository.findById(contratoRequestDTO.getFinanciadorId())
                                .orElseThrow(() -> new EntityNotFoundException("Financiador não encontrado com ID: " + contratoRequestDTO.getFinanciadorId()));
                        contrato.setFinanciador(financiador);
                    }
                    
                    if (contratoRequestDTO.getLocadorId() != null) {
                        Usuario locador = userRepository.findById(contratoRequestDTO.getLocadorId())
                                .orElseThrow(() -> new EntityNotFoundException("Locador não encontrado com ID: " + contratoRequestDTO.getLocadorId()));
                        contrato.setLocador(locador);
                    }
                    
                    if (contratoRequestDTO.getLocadoraId() != null) {
                        Empresa locadora = empresaRepository.findById(contratoRequestDTO.getLocadoraId())
                                .orElseThrow(() -> new EntityNotFoundException("Locadora não encontrada com ID: " + contratoRequestDTO.getLocadoraId()));
                        contrato.setLocadora(locadora);
                    }
                    
                    if (contratoRequestDTO.getAutomovelId() != null) {
                        Automovel automovel = automovelRepository.findById(contratoRequestDTO.getAutomovelId())
                                .orElseThrow(() -> new EntityNotFoundException("Automóvel não encontrado com ID: " + contratoRequestDTO.getAutomovelId()));
                        contrato.setAutomovel(automovel);
                    }
                    
                    Contrato contratoAtualizado = contratoRepository.save(contrato);
                    return mapearContratoCompleto(contratoAtualizado);
                });
    }
    
    public boolean deletar(Long id) {
        if (contratoRepository.existsById(id)) {
            contratoRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private ContratoDTO mapearContratoCompleto(Contrato contrato) {
        ContratoDTO contratoDTO = contratoMapper.toDTO(contrato);
        
        // Mapear financiador
        if (contrato.getFinanciador() != null) {
            contratoDTO.setFinanciador(empresaMapper.toDTO(contrato.getFinanciador()));
        }
        
        // Mapear locador
        if (contrato.getLocador() != null) {
            contratoDTO.setLocador(usuarioMapper.toDTO(contrato.getLocador()));
        }
        
        // Mapear locadora
        if (contrato.getLocadora() != null) {
            contratoDTO.setLocadora(empresaMapper.toDTO(contrato.getLocadora()));
        }
        
        // Mapear automóvel
        if (contrato.getAutomovel() != null) {
            contratoDTO.setAutomovel(automovelMapper.toDTO(contrato.getAutomovel()));
        }
        
        return contratoDTO;
    }
}
