package com.localab.api.Seeder.Seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localab.api.Model.Entity.Empresa;
import com.localab.api.Model.Entity.Usuario;
import com.localab.api.Model.Type.UsuarioType;
import com.localab.api.Repository.EmpresaRepository;
import com.localab.api.Repository.UserRepository;

@Component
public class UsuarioSeeder {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    public void seed() {
        System.out.println("👥 Populando usuários...");
        
        // Seed funcionários de banco
        seedFuncionariosBanco();
        
        // Seed funcionários de locadora
        seedFuncionariosLocadora();
        
        // Seed usuários contratantes
        seedUsuariosContratantes();
        
        System.out.println("✅ Usuários populados com sucesso!");
    }
    
    private void seedFuncionariosBanco() {
        System.out.println("  🏦 Populando funcionários de banco...");
        
        // Seu Barriga - Irmãos Limão
        createUsuarioIfNotExists(
            "Seu Barriga",
            "Rua dos Bancos, 123 - Centro",
            "Gerente Bancário",
            "seu.barriga@irmaoslimao.com",
            "123456",
            "12.345.678-9",
            "123.456.789-01",
            UsuarioType.FUNCIONARIO_BANCO,
            "12.345.678/0001-90" // Irmãos Limão
        );
        
        // Irmão Limão - UaiBank
        createUsuarioIfNotExists(
            "Irmão Limão",
            "Avenida Financeira, 456 - Centro",
            "Diretor Financeiro",
            "irmao.limao@uaibank.com",
            "654321",
            "98.765.432-1",
            "987.654.321-09",
            UsuarioType.FUNCIONARIO_BANCO,
            "98.765.432/0001-10" // UaiBank
        );
    }
    
    private void seedFuncionariosLocadora() {
        System.out.println("  🚗 Populando funcionários de locadora...");
        
        // Seu Madruga - Locabrisa
        createUsuarioIfNotExists(
            "Seu Madruga",
            "Rua das Locadoras, 789 - Centro",
            "Atendente de Locação",
            "seu.madruga@locabrisa.com",
            "madruga123",
            "11.222.333-4",
            "111.222.333-44",
            UsuarioType.FUNCIONARIO_LOCADORA,
            "11.222.333/0001-44" // Locabrisa
        );
        
        // Tripa Seca - Bo-racha
        createUsuarioIfNotExists(
            "Tripa Seca",
            "Avenida dos Carros, 321 - Centro",
            "Gerente de Locação",
            "tripa.seca@boracha.com",
            "tripa456",
            "55.666.777-8",
            "555.666.777-88",
            UsuarioType.FUNCIONARIO_LOCADORA,
            "55.666.777/0001-88" // Bo-racha
        );
    }
    
    private void seedUsuariosContratantes() {
        System.out.println("  👤 Populando usuários contratantes...");
        
        // Dick Vigarista - Valmorina
        createUsuarioIfNotExists(
            "Dick Vigarista",
            "Rua dos Contratantes, 555 - Centro",
            "Empresário",
            "dick.vigarista@valmorina.com",
            "dick123",
            "99.888.777-6",
            "999.888.777-66",
            UsuarioType.CONTRATANTE,
            "99.888.777/0001-66" // Valmorina
        );
        
        // Mutley - Valmorina
        createUsuarioIfNotExists(
            "Mutley",
            "Rua dos Contratantes, 555 - Centro",
            "Assistente",
            "mutley@valmorina.com",
            "mutley456",
            "44.333.222-1",
            "444.333.222-11",
            UsuarioType.CONTRATANTE,
            "99.888.777/0001-66" // Valmorina
        );
    }
    
    private void createUsuarioIfNotExists(String nome, String endereco, String profissao, 
                                        String email, String senha, String rg, String cpf, 
                                        UsuarioType tipo, String cnpjEmpresa) {
        
        if (!userRepository.findByEmail(email).isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEndereco(endereco);
            usuario.setProfissao(profissao);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setRg(rg);
            usuario.setCpf(cpf);
            usuario.setTipo(tipo);
            
            // Buscar empresa pelo CNPJ
            Empresa empresa = empresaRepository.findByCnpj(cnpjEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com CNPJ: " + cnpjEmpresa));
            
            usuario.setEmpresa(empresa);
            
            userRepository.save(usuario);
            System.out.println("    ✓ Usuário criado: " + nome + " (" + tipo + ") - " + empresa.getRazaoSocial());
        } else {
            System.out.println("    - Usuário já existe: " + nome);
        }
    }
}
