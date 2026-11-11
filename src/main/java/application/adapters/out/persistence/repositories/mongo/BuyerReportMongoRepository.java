package application.adapters.out.persistence.repositories.mongo;

import application.adapters.out.persistence.entities.BuyerReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BuyerReportMongoRepository extends MongoRepository<BuyerReportDocument, String> {
    Optional<BuyerReportDocument> findByBuyerId(String buyerId);
}