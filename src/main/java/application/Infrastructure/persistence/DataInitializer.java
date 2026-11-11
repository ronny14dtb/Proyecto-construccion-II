package application.Infrastructure.persistence;

import application.adapters.out.persistence.entities.BuyerEntity;
import application.adapters.out.persistence.repositories.jpa.BuyerJpaRepository; 
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(BuyerJpaRepository buyerRepository) {
        return args -> {
            // Verificamos si ya existe antes de intentar cualquier operación
            if (!buyerRepository.existsById(6L)) {
                BuyerEntity buyer = new BuyerEntity();
                buyer.setId(6L);
                buyer.setUserEmail("ronny@test.com");
                buyer.setPrimaryAddressDetails("Medellín, Colombia");
                buyerRepository.save(buyer);
                System.out.println(">>> Comprador con ID 6 creado automáticamente en la BD.");
            } else {
                System.out.println(">>> El comprador con ID 6 ya existe en la base de datos.");
            }
        };
    }
}