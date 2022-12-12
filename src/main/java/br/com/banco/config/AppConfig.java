package br.com.banco.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(value = "classpath:envs.properties", ignoreResourceNotFound = true)
public class AppConfig {

    @Value("${MODULO_CLIENTE_BASE_URL}")
    private String moduloClienteBaseUrl;
}
