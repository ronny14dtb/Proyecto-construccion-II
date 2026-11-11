package application.adapters.in.web;

import application.adapters.in.web.dto.SellerRequestDto;
import application.domain.models.Seller;
import application.domain.services.seller.RegisterSellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final RegisterSellerService registerSellerService;

    public SellerController(RegisterSellerService registerSellerService) {
        this.registerSellerService = registerSellerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Seller> registerSeller(@RequestBody SellerRequestDto request) {
        Seller seller = registerSellerService.register(request.getStoreName(), request.getEmail());
        return ResponseEntity.ok(seller);
    }
}