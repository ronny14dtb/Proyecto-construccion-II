package application.adapters.out.persistence.mappers;

import application.adapters.out.persistence.entities.ShoppingCartDocument;
import application.domain.models.CartItem;
import application.domain.models.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ShoppingCartPersistenceMapper {

    public ShoppingCartDocument toDocument(ShoppingCart cart) {
        if (cart == null) return null;

        ShoppingCartDocument doc = new ShoppingCartDocument();
        doc.setBuyerId(cart.getBuyerId());

        if (cart.getItems() != null) {
            doc.setItems(cart.getItems().stream().map(item -> {
                ShoppingCartDocument.CartItemDocument itemDoc = new ShoppingCartDocument.CartItemDocument();
                itemDoc.setProductId(item.getProductId());
                itemDoc.setSellerId(item.getSellerId());
                itemDoc.setQuantity(item.getQuantity());
                return itemDoc;
            }).collect(Collectors.toList()));
        }

        return doc;
    }

    public ShoppingCart toDomain(ShoppingCartDocument doc) {
        if (doc == null) return null;

        ShoppingCart cart = new ShoppingCart(doc.getBuyerId());

        if (doc.getItems() != null) {
            for (ShoppingCartDocument.CartItemDocument itemDoc : doc.getItems()) {
                CartItem item = new CartItem(
                    itemDoc.getProductId(),
                    itemDoc.getSellerId(),
                    itemDoc.getQuantity()
                );
                cart.addItem(item);
            }
        }

        return cart;
    }
}