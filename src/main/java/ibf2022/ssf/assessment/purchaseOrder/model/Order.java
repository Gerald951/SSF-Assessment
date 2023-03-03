package ibf2022.ssf.assessment.purchaseOrder.model;

import java.io.Serializable;

public class Order implements Serializable{

    private Cart cart;
    private Quotation quotation;
    private ShippingAddress shippingAddress;

    public Order(Cart cart, ShippingAddress shippingAddress) {
        this.cart = cart;
        this.shippingAddress = shippingAddress;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

	public void put(String quotationId, Order order) {
	}


    

    
    
}
