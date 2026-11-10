package application.adapters.out.persistence;

// Imports de User y Buyer (Los que se habían borrado)
import application.adapters.out.persistence.mappers.BuyerPersistenceMapper;
import application.adapters.out.persistence.mappers.InventoryPersistenceMapper;
import application.adapters.out.persistence.mappers.InvoicePersistenceMapper;
import application.adapters.out.persistence.mappers.OrderPersistenceMapper;
import application.adapters.out.persistence.mappers.UserPersistenceMapper;
import application.adapters.out.persistence.repositories.BuyerJpaRepository;
import application.adapters.out.persistence.repositories.InventoryJpaRepository;
import application.adapters.out.persistence.repositories.InvoiceJpaRepository;
import application.adapters.out.persistence.repositories.OrderJpaRepository;
import application.adapters.out.persistence.repositories.UserJpaRepository;
import application.domain.ports.out.BuyerRepositoryPort;
import application.domain.ports.out.InventoryRepositoryPort;
import application.domain.ports.out.InvoiceRepositoryPort;
import application.domain.ports.out.OrderRepositoryPort;
import application.domain.ports.out.UserRepositoryPort;

// Imports de Product (Seguirán en rojo hasta que crees los archivos)
import application.adapters.out.persistence.mappers.ProductPersistenceMapper;
import application.adapters.out.persistence.repositories.ProductJpaRepository;
import application.domain.ports.out.ProductRepositoryPort;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

    @Bean
    public UserRepositoryPort userRepositoryPort(UserJpaRepository userJpaRepository, UserPersistenceMapper userPersistenceMapper) {
        return new MySQLUserRepositoryAdapter(userJpaRepository, userPersistenceMapper);
    }

    @Bean
    public BuyerRepositoryPort buyerRepositoryPort(BuyerJpaRepository buyerJpaRepository, BuyerPersistenceMapper buyerPersistenceMapper) {
        return new MySQLBuyerRepositoryAdapter(buyerJpaRepository, buyerPersistenceMapper);
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(ProductJpaRepository productJpaRepository, ProductPersistenceMapper productPersistenceMapper) {
        return new MySQLProductRepositoryAdapter(productJpaRepository, productPersistenceMapper);
    }
    @Bean
    public InventoryRepositoryPort inventoryRepositoryPort(InventoryJpaRepository inventoryJpaRepository, InventoryPersistenceMapper inventoryPersistenceMapper) {
        return new MySQLInventoryRepositoryAdapter(inventoryJpaRepository, inventoryPersistenceMapper);
    }
    @Bean
    public InvoiceRepositoryPort invoiceRepositoryPort(InvoiceJpaRepository invoiceJpaRepository, InvoicePersistenceMapper invoicePersistenceMapper) {
        return new MySQLInvoiceRepositoryAdapter(invoiceJpaRepository, invoicePersistenceMapper);
        
    }
    @Bean
    public OrderRepositoryPort orderRepositoryPort(OrderJpaRepository orderJpaRepository, OrderPersistenceMapper orderPersistenceMapper) {
        return new MySQLOrderRepositoryAdapter(orderJpaRepository, orderPersistenceMapper);
    }
}