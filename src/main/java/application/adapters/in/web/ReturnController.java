package application.adapters.in.web;

import application.adapters.in.web.dto.ReturnRequestDto;
import application.domain.ports.in.CreateRefundUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    private final CreateRefundUseCase createRefundUseCase;

    public ReturnController(CreateRefundUseCase createRefundUseCase) {
        this.createRefundUseCase = createRefundUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createReturn(@RequestBody ReturnRequestDto request) {
        // Enlazamos la petición con la lógica que valida la orden
        var refund = createRefundUseCase.execute(request.getOrderId(), request.getReason());
        
        return ResponseEntity.ok(refund);
    }
}