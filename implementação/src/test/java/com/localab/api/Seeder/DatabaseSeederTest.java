package com.localab.api.Seeder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.localab.api.Model.Entity.Empresa;
import com.localab.api.Model.Entity.Usuario;
import com.localab.api.Model.Type.UsuarioType;
import com.localab.api.Repository.EmpresaRepository;
import com.localab.api.Repository.UserRepository;

@SpringBootTest
@TestPropertySource(properties = "app.seeder.enabled=true")
public class DatabaseSeederTest {
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void testEmpresasForamCriadas() {
        // Verificar se todas as empresas foram criadas
        assertEquals(5, empresaRepository.count());
        
        // Verificar empresas específicas
        assertTrue(empresaRepository.findByCnpj("12.345.678/0001-90").isPresent());
        assertTrue(empresaRepository.findByCnpj("98.765.432/0001-10").isPresent());
        assertTrue(empresaRepository.findByCnpj("11.222.333/0001-44").isPresent());
        assertTrue(empresaRepository.findByCnpj("55.666.777/0001-88").isPresent());
        assertTrue(empresaRepository.findByCnpj("99.888.777/0001-66").isPresent());
        
        // Verificar instituições financeiras
        assertEquals(2, empresaRepository.findByInstituicaoFinanceira(true).size());
    }
    
    @Test
    public void testUsuariosForamCriados() {
        // Verificar se todos os usuários foram criados
        assertEquals(6, userRepository.count());
        
        // Verificar funcionários de banco
        assertEquals(2, userRepository.findByTipo(UsuarioType.FUNCIONARIO_BANCO).size());
        
        // Verificar funcionários de locadora
        assertEquals(2, userRepository.findByTipo(UsuarioType.FUNCIONARIO_LOCADORA).size());
        
        // Verificar usuários contratantes
        assertEquals(2, userRepository.findByTipo(UsuarioType.CONTRATANTE).size());
    }
    
    @Test
    public void testUsuariosTemEmpresas() {
        // Verificar se todos os usuários têm empresas associadas
        userRepository.findAll().forEach(usuario -> {
            assertNotNull(usuario.getEmpresa(), "Usuário " + usuario.getNome() + " deve ter uma empresa associada");
        });
    }
    
    @Test
    public void testUsuariosEspecificos() {
        // Verificar usuários específicos
        assertTrue(userRepository.findByEmail("seu.barriga@irmaoslimao.com").isPresent());
        assertTrue(userRepository.findByEmail("irmao.limao@uaibank.com").isPresent());
        assertTrue(userRepository.findByEmail("seu.madruga@locabrisa.com").isPresent());
        assertTrue(userRepository.findByEmail("tripa.seca@boracha.com").isPresent());
        assertTrue(userRepository.findByEmail("dick.vigarista@valmorina.com").isPresent());
        assertTrue(userRepository.findByEmail("mutley@valmorina.com").isPresent());
    }
}
