package application.adapters.out.persistence;

// Imports de User y Buyer (Los que se habían borrado)
import application.adapters.out.persistence.mappers.BuyerPersistenceMapper;
import application.adapters.out.persistence.mappers.InventoryPersistenceMapper;
import application.adapters.out.persistence.mappers.InvoicePersistenceMapper;
import application.adapters.out.persistence.mappers.OrderPersistenceMapper;
import application.adapters.out.persistence.mappers.RefundPersistenceMapper;
import application.adapters.out.persistence.mappers.SellerPersistenceMapper;
import application.adapters.out.persistence.mappers.ShipmentPersistenceMapper;
import application.adapters.out.persistence.mappers.ShoppingCartPersistenceMapper;
import application.adapters.out.persistence.mappers.UserPersistenceMapper;
import application.adapters.out.persistence.mappers.WarehousePersistenceMapper;
import application.adapters.out.persistence.repositories.jpa.BuyerJpaRepository;
import application.adapters.out.persistence.repositories.jpa.InventoryJpaRepository;
import application.adapters.out.persistence.repositories.jpa.InvoiceJpaRepository;
import application.adapters.out.persistence.repositories.jpa.OrderJpaRepository;
import application.adapters.out.persistence.repositories.jpa.ProductJpaRepository;
import application.adapters.out.persistence.repositories.jpa.RefundJpaRepository;
import application.adapters.out.persistence.repositories.jpa.SellerJpaRepository;
import application.adapters.out.persistence.repositories.jpa.ShipmentJpaRepository;
import application.adapters.out.persistence.repositories.jpa.UserJpaRepository;
import application.adapters.out.persistence.repositories.jpa.WarehouseJpaRepository;
import application.adapters.out.persistence.repositories.mongo.ShoppingCartMongoRepository;
import application.domain.ports.out.BuyerRepositoryPort;
import application.domain.ports.out.InventoryRepositoryPort;
import application.domain.ports.out.InvoiceRepositoryPort;
import application.domain.ports.out.OrderRepositoryPort;
import application.domain.ports.out.UserRepositoryPort;
import application.domain.ports.out.WarehouseRepositoryPort;
import application.adapters.out.persistence.mappers.ProductPersistenceMapper;
import application.domain.ports.out.ProductRepositoryPort;
import application.domain.ports.out.SellerRepositoryPort;


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
   
    @Bean
    public SellerRepositoryPort sellerRepositoryPort(SellerJpaRepository sellerJpaRepository, SellerPersistenceMapper sellerPersistenceMapper) {
        return new MySQLSellerRepositoryAdapter(sellerJpaRepository, sellerPersistenceMapper);
    }
    
    @Bean
    public WarehouseRepositoryPort warehouseRepositoryPort(WarehouseJpaRepository warehouseJpaRepository, WarehousePersistenceMapper warehousePersistenceMapper) {
        return new MySQLWarehouseRepositoryAdapter(warehouseJpaRepository, warehousePersistenceMapper);
    }

}