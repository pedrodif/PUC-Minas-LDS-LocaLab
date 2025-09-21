package com.localab.api.Seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.localab.api.Seeder.Seeders.EmpresaSeeder;
import com.localab.api.Seeder.Seeders.UsuarioSeeder;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    
    @Autowired
    private EmpresaSeeder empresaSeeder;
    
    @Autowired
    private UsuarioSeeder usuarioSeeder;
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("🌱 Iniciando seed do banco de dados...");
        
        // Seed das empresas primeiro (necessário para os usuários)
        empresaSeeder.seed();
        
        // Seed dos usuários
        usuarioSeeder.seed();
        
        System.out.println("✅ Seed do banco de dados concluído!");
    }
}
