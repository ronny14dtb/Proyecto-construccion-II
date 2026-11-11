package application.adapters.in.web;

import application.adapters.in.web.dto.InventoryRequestDto;
import application.domain.models.Inventory;
import application.domain.services.inventory.ManageInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final ManageInventoryService manageInventoryService;

    public InventoryController(ManageInventoryService manageInventoryService) {
        this.manageInventoryService = manageInventoryService;
    }

    @PostMapping("/update")
    public ResponseEntity<Inventory> updateStock(@RequestBody InventoryRequestDto request) {
        Inventory inventory = manageInventoryService.registerOrUpdateStock(
                request.getProductId(),
                request.getSellerId(),
                request.getQuantity(),
                request.getWarehouseLocation()
        );
        return ResponseEntity.ok(inventory);
    }
}