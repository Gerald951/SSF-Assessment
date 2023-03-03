package ibf2022.ssf.assessment.purchaseOrder.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Item implements Serializable {

    @NotNull(message="You should choose an item!")
    private String name;

    @NotEmpty(message="You should enter a quantity!")
    @Min(value = 1, message = "You should enter a minimum of 1!")
    private int quantity;

    public Item() {
    }

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item:%s, Quantity:%d".formatted(name, quantity);
    }

    public static Item create(JsonObject json) {
		Item item = new Item();
		item.setName(json.getString("name"));
		item.setQuantity(json.getInt("quantity"));
		return item;
	}
}