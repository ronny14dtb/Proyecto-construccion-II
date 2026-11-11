package application.domain.services.report;

import application.adapters.out.persistence.entities.BuyerReportDocument;
import application.adapters.out.persistence.repositories.mongo.BuyerReportMongoRepository;
import application.adapters.out.persistence.repositories.mongo.MongoShoppingCartRepository;
import application.adapters.out.persistence.repositories.jpa.OrderJpaRepository;
import application.adapters.out.persistence.repositories.jpa.ShipmentJpaRepository;
import application.adapters.out.persistence.repositories.jpa.RefundJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerateBuyerReportService {

    private final MongoShoppingCartRepository cartRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final ShipmentJpaRepository shipmentJpaRepository;
    private final RefundJpaRepository refundJpaRepository;
    private final BuyerReportMongoRepository reportMongoRepository;

    public GenerateBuyerReportService(MongoShoppingCartRepository cartRepository,
                                      OrderJpaRepository orderJpaRepository,
                                      ShipmentJpaRepository shipmentJpaRepository,
                                      RefundJpaRepository refundJpaRepository,
                                      BuyerReportMongoRepository reportMongoRepository) {
        this.cartRepository = cartRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.shipmentJpaRepository = shipmentJpaRepository;
        this.refundJpaRepository = refundJpaRepository;
        this.reportMongoRepository = reportMongoRepository;
    }

    public BuyerReportDocument generateAndSaveReport(String buyerId) {
        // 1. Carrito del comprador desde MongoDB mapeado a CartItemSummary
        List<BuyerReportDocument.CartItemSummary> cartItems = cartRepository.findAll().stream()
                .filter(cart -> java.util.Objects.equals(cart.getBuyerId(), buyerId))
                .findFirst()
                .map(cart -> cart.getItems().stream()
                        .map(item -> new BuyerReportDocument.CartItemSummary(
                                item.getProductId(),
                                item.getSellerId(),
                                item.getQuantity()
                        ))
                        .collect(Collectors.toList()))
                .orElse(java.util.Collections.emptyList());

        // 2. Órdenes de MySQL mapeadas a OrderSummary
        var orders = orderJpaRepository.findAll().stream()
                .map(o -> {
                    BuyerReportDocument.OrderSummary summary = new BuyerReportDocument.OrderSummary();
                    summary.setOrderId(o.getOrderId());
                    summary.setBuyerId(o.getBuyerId());
                    summary.setStatus(o.getStatus());
                    return summary;
                })
                .collect(Collectors.toList());

        // 3. Envíos de MySQL mapeados a ShipmentSummary
        var shipments = shipmentJpaRepository.findAll().stream()
                .map(s -> {
                    BuyerReportDocument.ShipmentSummary summary = new BuyerReportDocument.ShipmentSummary();
                    summary.setShipmentId(s.getShipmentId());
                    summary.setOrderId(s.getOrderId());
                    summary.setCarrier(s.getCarrier());
                    summary.setTrackingNumber(s.getTrackingNumber());
                    summary.setStatus(s.getStatus());
                    return summary;
                })
                .collect(Collectors.toList());

        // 4. Devoluciones de MySQL mapeadas a ReturnSummary
        var returns = refundJpaRepository.findAll().stream()
                .map(r -> {
                    BuyerReportDocument.ReturnSummary summary = new BuyerReportDocument.ReturnSummary();
                    summary.setReturnId(r.getRefundId()); // O r.getId() según tu entidad
                    summary.setOrderId(r.getOrderId());
                    summary.setReason(r.getReason());
                    return summary;
                })
                .collect(Collectors.toList());

        // 5. Construimos y guardamos el documento consolidado en MongoDB
        BuyerReportDocument report = reportMongoRepository.findByBuyerId(buyerId)
                .orElse(new BuyerReportDocument());

        report.setBuyerId(buyerId);
        report.setCartItems(cartItems);
        report.setOrders(orders);
        report.setShipments(shipments);
        report.setReturns(returns);
        report.setUpdatedAt(LocalDateTime.now().toString());

        return reportMongoRepository.save(report);
    }
}