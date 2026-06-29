package Kani0dev.ATM;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class GerenciadorDeDiagnosticosTecnicosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorDeDiagnosticosTecnicosApplication.class, args);
    }

    @Bean
    CommandLineRunner logDatabaseUrl(Environment env) {
        return args -> {
            var url = env.getProperty("spring.datasource.url");
            System.out.println("=== Conexao com o banco: " + url + " ===");
        };
    }

}
