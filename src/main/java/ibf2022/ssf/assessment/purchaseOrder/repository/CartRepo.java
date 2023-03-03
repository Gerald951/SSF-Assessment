package ibf2022.ssf.assessment.purchaseOrder.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ibf2022.ssf.assessment.purchaseOrder.model.Cart;
import ibf2022.ssf.assessment.purchaseOrder.model.Order;
import ibf2022.ssf.assessment.purchaseOrder.model.ShippingAddress;

@Repository
public class CartRepo {

    @Autowired  @Qualifier("my-cart")
    private Map<String, Order> order;

    public Order saveCart(String quotationId, Cart cart, ShippingAddress shippingAddress) {
        Order order = new Order(cart, shippingAddress);
        order.put(quotationId, order);
        return order;
    }

    public Optional<Order> getCart(String quotationId) {
        Optional<Order> getCart = Optional.ofNullable(order.get(quotationId));
        return getCart;
    }

}
