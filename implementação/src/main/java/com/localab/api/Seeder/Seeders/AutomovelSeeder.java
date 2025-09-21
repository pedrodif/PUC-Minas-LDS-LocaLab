package com.localab.api.Seeder.Seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localab.api.Model.Entity.Automovel;
import com.localab.api.Repository.AutomovelRepository;

@Component
public class AutomovelSeeder {
    
    @Autowired
    private AutomovelRepository automovelRepository;
    
    public void seed() {
        System.out.println("🚗 Seedando automóveis...");
        
        createAutomovelIfNotExists("ABC123", 2020, "Toyota", "Corolla", "ABC-1234");
        createAutomovelIfNotExists("DEF456", 2021, "Honda", "Civic", "DEF-5678");
        createAutomovelIfNotExists("GHI789", 2019, "Ford", "Focus", "GHI-9012");
        createAutomovelIfNotExists("JKL012", 2022, "Volkswagen", "Golf", "JKL-3456");
        createAutomovelIfNotExists("MNO345", 2020, "Chevrolet", "Cruze", "MNO-7890");
        createAutomovelIfNotExists("PQR678", 2021, "Nissan", "Sentra", "PQR-1234");
        
        System.out.println("✅ Automóveis seedados com sucesso!");
    }
    
    private void createAutomovelIfNotExists(String matricula, int ano, String marca, String modelo, String placa) {
        if (!automovelRepository.findByMatricula(matricula).isPresent()) {
            Automovel automovel = new Automovel();
            automovel.setMatricula(matricula);
            automovel.setAno(ano);
            automovel.setMarca(marca);
            automovel.setModelo(modelo);
            automovel.setPlaca(placa);
            
            automovelRepository.save(automovel);
            System.out.println("  ✓ Automóvel criado: " + marca + " " + modelo + " (" + placa + ")");
        } else {
            System.out.println("  - Automóvel já existe: " + marca + " " + modelo);
        }
    }
}
