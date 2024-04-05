package br.com.fiap.tiulanchesproducao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title="API Tiu Lanches - Produção", version = "24.01.20", description = "Tech Challenge para conclusão Pós Graduação Software Architecture pela FIAP"),
		servers = { @Server(url="http://localhost:8082") }
)
public class TiulanchesProducaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiulanchesProducaoApplication.class, args);
	}

}
