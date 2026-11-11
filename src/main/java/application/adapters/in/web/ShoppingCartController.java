package application.adapters.in.web;

import application.adapters.in.web.AddItemRequestDto;
import application.domain.models.ShoppingCart;
import application.domain.ports.in.AddToCartUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class ShoppingCartController {

    private final AddToCartUseCase addToCartUseCase;

    public ShoppingCartController(AddToCartUseCase addToCartUseCase) {
        this.addToCartUseCase = addToCartUseCase;
    }

    @PostMapping("/add")
    public ResponseEntity<ShoppingCart> addProductToCart(@RequestBody AddItemRequestDto request) {
        ShoppingCart updatedCart = addToCartUseCase.addProduct(
            request.getBuyerId(), 
            request.getProductId(), 
            request.getSellerId(), 
            request.getQuantity()
        );
        return ResponseEntity.ok(updatedCart);
    }
}