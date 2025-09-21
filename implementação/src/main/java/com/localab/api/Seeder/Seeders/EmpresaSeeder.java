package com.localab.api.Seeder.Seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localab.api.Model.Entity.Empresa;
import com.localab.api.Repository.EmpresaRepository;

@Component
public class EmpresaSeeder {
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    public void seed() {
        System.out.println("🏢 Populando empresas...");
        
        // Instituições Financeiras
        createEmpresaIfNotExists("12.345.678/0001-90", "Irmãos Limão", true);
        createEmpresaIfNotExists("98.765.432/0001-10", "UaiBank", true);
        
        // Locadoras
        createEmpresaIfNotExists("11.222.333/0001-44", "Locabrisa", false);
        createEmpresaIfNotExists("55.666.777/0001-88", "Bo-racha", false);
        
        // Empresa que contrata
        createEmpresaIfNotExists("99.888.777/0001-66", "Valmorina", false);
        
        System.out.println("✅ Empresas populadas com sucesso!");
    }
    
    private void createEmpresaIfNotExists(String cnpj, String razaoSocial, boolean instituicaoFinanceira) {
        if (!empresaRepository.findByCnpj(cnpj).isPresent()) {
            Empresa empresa = new Empresa();
            empresa.setCnpj(cnpj);
            empresa.setRazaoSocial(razaoSocial);
            empresa.setInstituicaoFinanceira(instituicaoFinanceira);
            
            empresaRepository.save(empresa);
            System.out.println("  ✓ Empresa criada: " + razaoSocial + " (" + cnpj + ")");
        } else {
            System.out.println("  - Empresa já existe: " + razaoSocial);
        }
    }
}
