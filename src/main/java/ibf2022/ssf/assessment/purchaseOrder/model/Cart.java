package ibf2022.ssf.assessment.purchaseOrder.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedList;

public class Cart {
    private List<Item> contents = new LinkedList<Item>();
    private boolean isEmpty = true;
    private boolean isItemName = false;

    public Cart() {}
    
    public List<Item> getItemName(String name) {
        List<Item> item = contents
        .stream()
        .filter(c -> c.getName().equals(name))
        .collect(Collectors.toList());

        if (!item.isEmpty()) 
            this.isItemName = true;

        return item;
    }
    // public String getName() { return get}
    
    public boolean isItemName() {
        return isItemName;
    }

    public List<Item> getContents() {
        return contents;
    }

    public void setContents(List<Item> contents) {
        this.contents = contents;
    }

    public void addItemToCart(Item item){
        this.contents.add(item);
    }

    public boolean getisEmpty() {
        isEmpty = this.contents.isEmpty();
        return isEmpty;
    }

    public Boolean setItemtoCart(Item item) {
        return contents.add(item);
    }
}
