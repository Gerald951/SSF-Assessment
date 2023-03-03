package ibf2022.ssf.assessment.purchaseOrder.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ShippingAddress implements Serializable {
    
    @NotNull(message="Please state your name")
	@Size(min=2, message="Your name must be longer than 2 characters")
    private String customerName;

    @NotNull(message="Please state your address")
    private String customerAddress;

    public ShippingAddress() {}

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return "CustomerName:%s, CustomerAddress:%s".formatted(customerName, customerAddress);
    }

    public static ShippingAddress create(JsonObject json) {
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setCustomerName(json.getString("customerName"));
		shippingAddress.setCustomerAddress(json.getString("customerAddress"));
		return shippingAddress;
	}
    


}
