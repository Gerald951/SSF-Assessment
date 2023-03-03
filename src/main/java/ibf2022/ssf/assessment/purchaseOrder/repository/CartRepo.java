package ibf2022.ssf.assessment.purchaseOrder.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ibf2022.ssf.assessment.purchaseOrder.model.Cart;

@Repository
public class CartRepo {

    @Autowired  @Qualifier("my-cart")
    private Map<String, Object> order;

    public Map<String,Cart> saveCart(String item, Integer quantity) {
        Cart cart = new Cart(item, quantity);
        mapCart.put(item, cart);
        return mapCart;
    }

    public Optional<Cart> getCart(String item) {
        Optional<Cart> getCart = Optional.ofNullable(mapCart.get(item));
        return getCart;
    }

}
