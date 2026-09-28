package application.domain.services.warehouse;

import application.domain.models.Warehouse;
import application.domain.ports.in.RegisterWarehouseUseCase;
import application.domain.ports.out.WarehouseRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterWarehouseService implements RegisterWarehouseUseCase {
    
    private final WarehouseRepositoryPort warehouseRepository;

    public RegisterWarehouseService(WarehouseRepositoryPort warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Warehouse registerWarehouse(Warehouse warehouse) {
        if (warehouse == null) {
            throw new IllegalArgumentException("Warehouse to register cannot be null");
        }
        return warehouseRepository.save(warehouse);
    }
}