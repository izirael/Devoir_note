package com.forrage.app;

import com.forrage.app.model.TypeDevis;
import com.forrage.app.repository.TypeDevisRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForrageApplication {
	public static void main(String[] args) {
		SpringApplication.run(ForrageApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(TypeDevisRepository typeDevisRepository) {
		return args -> {
			if (typeDevisRepository.count() == 0) {
				TypeDevis etude = new TypeDevis();
				etude.setNom("Etude");
				typeDevisRepository.save(etude);

				TypeDevis forage = new TypeDevis();
				forage.setNom("Forage");
				typeDevisRepository.save(forage);
			}
		};
	}
}
