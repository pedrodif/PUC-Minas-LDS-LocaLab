package com.localab.api.Config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.seeder.enabled", havingValue = "true", matchIfMissing = true)
public class SeederConfig {
    // Configuração para habilitar/desabilitar o seeder
    // Use app.seeder.enabled=false no application.properties para desabilitar
}
