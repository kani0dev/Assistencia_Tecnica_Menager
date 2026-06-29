package Kani0dev.ATM;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class GerenciadorDeDiagnosticosTecnicosApplication {
    
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> 
            System.setProperty(entry.getKey(), entry.getValue())
        );
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
