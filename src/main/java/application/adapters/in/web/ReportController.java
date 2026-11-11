package application.adapters.in.web;

import application.adapters.out.persistence.entities.BuyerReportDocument;
import application.domain.services.report.GenerateBuyerReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final GenerateBuyerReportService reportService;

    public ReportController(GenerateBuyerReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<BuyerReportDocument> getBuyerReport(@PathVariable String buyerId) {
        BuyerReportDocument report = reportService.generateAndSaveReport(buyerId);
        return ResponseEntity.ok(report);
    }
}