package com.localab.api.Seeder.Seeders;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localab.api.Model.Entity.Automovel;
import com.localab.api.Model.Entity.Contrato;
import com.localab.api.Model.Entity.Empresa;
import com.localab.api.Model.Entity.Usuario;
import com.localab.api.Model.Type.FaseAprovacao;
import com.localab.api.Model.Type.Propriedade;
import com.localab.api.Model.Type.UsuarioType;
import com.localab.api.Repository.AutomovelRepository;
import com.localab.api.Repository.ContratoRepository;
import com.localab.api.Repository.EmpresaRepository;
import com.localab.api.Repository.UserRepository;

@Component
public class ContratoSeeder {
    
    @Autowired
    private ContratoRepository contratoRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AutomovelRepository automovelRepository;
    
    public void seed() {
        System.out.println("📋 Seedando contratos...");
        
        // Buscar empresas, usuários e automóveis necessários
        List<Empresa> empresas = empresaRepository.findAll();
        List<Usuario> usuarios = userRepository.findAll();
        List<Automovel> automoveis = automovelRepository.findAll();
        
        // Filtrar usuários por tipo
        List<Usuario> funcionariosBanco = usuarios.stream()
            .filter(u -> u.getTipo() == UsuarioType.FUNCIONARIO_BANCO)
            .toList();
        
        List<Usuario> funcionariosLocadora = usuarios.stream()
            .filter(u -> u.getTipo() == UsuarioType.FUNCIONARIO_LOCADORA)
            .toList();
        
        List<Usuario> contratantes = usuarios.stream()
            .filter(u -> u.getTipo() == UsuarioType.CONTRATANTE)
            .toList();
        
        // Buscar empresas específicas
        Empresa irmaosLimao = empresaRepository.findByCnpj("12.345.678/0001-90")
            .orElseThrow(() -> new RuntimeException("Empresa Irmãos Limão não encontrada"));
        
        Empresa uaiBank = empresaRepository.findByCnpj("98.765.432/0001-10")
            .orElseThrow(() -> new RuntimeException("Empresa UaiBank não encontrada"));
        
        Empresa locabrisa = empresaRepository.findByCnpj("11.222.333/0001-44")
            .orElseThrow(() -> new RuntimeException("Empresa Locabrisa não encontrada"));
        
        Empresa boracha = empresaRepository.findByCnpj("55.666.777/0001-88")
            .orElseThrow(() -> new RuntimeException("Empresa Bo-racha não encontrada"));
        
        // Buscar usuários específicos
        Usuario seuBarriga = userRepository.findByEmail("seu.barriga@irmaoslimao.com")
            .orElseThrow(() -> new RuntimeException("Usuário Seu Barriga não encontrado"));
        
        Usuario irmaoLimao = userRepository.findByEmail("irmao.limao@uaibank.com")
            .orElseThrow(() -> new RuntimeException("Usuário Irmão Limão não encontrado"));
        
        Usuario seuMadruga = userRepository.findByEmail("seu.madruga@locabrisa.com")
            .orElseThrow(() -> new RuntimeException("Usuário Seu Madruga não encontrado"));
        
        Usuario tripaSeca = userRepository.findByEmail("tripa.seca@boracha.com")
            .orElseThrow(() -> new RuntimeException("Usuário Tripa Seca não encontrado"));
        
        Usuario dickVigarista = userRepository.findByEmail("dick.vigarista@valmorina.com")
            .orElseThrow(() -> new RuntimeException("Usuário Dick Vigarista não encontrado"));
        
        Usuario mutley = userRepository.findByEmail("mutley@valmorina.com")
            .orElseThrow(() -> new RuntimeException("Usuário Mutley não encontrado"));
        
        // Criar contratos
        createContratoIfNotExists(
            "Contrato 1 - Irmãos Limão x Locabrisa",
            15000.00,
            LocalDate.now().plusDays(30),
            LocalDate.now().plusDays(400),
            irmaosLimao, // financiador
            seuBarriga,  // locador (funcionário do banco)
            locabrisa,   // locadora
            automoveis.get(0), // primeiro automóvel
            Propriedade.BANCO,
            FaseAprovacao.APROVADO
        );
        
        createContratoIfNotExists(
            "Contrato 2 - UaiBank x Bo-racha",
            25000.00,
            LocalDate.now().plusDays(15),
            LocalDate.now().plusDays(365),
            uaiBank,     // financiador
            irmaoLimao,  // locador (funcionário do banco)
            boracha,     // locadora
            automoveis.get(1), // segundo automóvel
            Propriedade.BANCO,
            FaseAprovacao.AGUARDANDO_LOCADOR
        );
        
        createContratoIfNotExists(
            "Contrato 3 - Locabrisa x Valmorina",
            12000.00,
            LocalDate.now().plusDays(45),
            LocalDate.now().plusDays(300),
            null,        // sem financiador (propriedade da locadora)
            seuMadruga,  // locador (funcionário da locadora)
            locabrisa,   // locadora
            automoveis.get(2), // terceiro automóvel
            Propriedade.LOCADORA,
            FaseAprovacao.APROVADO
        );
        
        createContratoIfNotExists(
            "Contrato 4 - Bo-racha x Valmorina",
            18000.00,
            LocalDate.now().plusDays(60),
            LocalDate.now().plusDays(180),
            null,        // sem financiador (propriedade da locadora)
            tripaSeca,   // locador (funcionário da locadora)
            boracha,     // locadora
            automoveis.get(3), // quarto automóvel
            Propriedade.LOCADORA,
            FaseAprovacao.AGUARDANDO_FINANCIADOR
        );
        
        createContratoIfNotExists(
            "Contrato 5 - Valmorina x Locabrisa",
            8000.00,
            LocalDate.now().plusDays(90),
            LocalDate.now().plusDays(120),
            null,        // sem financiador (propriedade do contratante)
            dickVigarista, // locador (contratante)
            locabrisa,   // locadora
            automoveis.get(4), // quinto automóvel
            Propriedade.CONTRATANTE,
            FaseAprovacao.RECUSADO
        );
        
        createContratoIfNotExists(
            "Contrato 6 - Valmorina x Bo-racha",
            22000.00,
            LocalDate.now().plusDays(120),
            LocalDate.now().plusDays(500),
            null,        // sem financiador (propriedade do contratante)
            mutley,      // locador (contratante)
            boracha,     // locadora
            automoveis.get(5), // sexto automóvel
            Propriedade.CONTRATANTE,
            FaseAprovacao.APROVADO
        );
        
        System.out.println("✅ Contratos seedados com sucesso!");
    }
    
    private void createContratoIfNotExists(String descricao, double valorTotal, 
                                         LocalDate dataInicial, LocalDate dataFinal,
                                         Empresa financiador, Usuario locador, Empresa locadora, Automovel automovel,
                                         Propriedade propriedade, FaseAprovacao faseAprovacao) {
        
        // Verificar se já existe um contrato similar
        boolean contratoExiste = contratoRepository.findAll().stream()
            .anyMatch(c -> c.getLocador().getId().equals(locador.getId()) && 
                          c.getLocadora().getId() == locadora.getId() &&
                          c.getDataInicial().equals(dataInicial));
        
        if (!contratoExiste) {
            Contrato contrato = new Contrato();
            contrato.setValorTotal(valorTotal);
            contrato.setDataInicial(dataInicial);
            contrato.setDataFinal(dataFinal);
            contrato.setFinanciador(financiador);
            contrato.setLocador(locador);
            contrato.setLocadora(locadora);
            contrato.setAutomovel(automovel);
            contrato.setPropriedade(propriedade);
            contrato.setFaseAprovacao(faseAprovacao);
            
            contratoRepository.save(contrato);
            System.out.println("  ✓ Contrato criado: " + descricao + " - R$ " + valorTotal + " - " + automovel.getMarca() + " " + automovel.getModelo());
        } else {
            System.out.println("  - Contrato já existe: " + descricao);
        }
    }
}
