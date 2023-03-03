package ibf2022.ssf.assessment.purchaseOrder.model;

import java.util.List;
import java.util.LinkedList;

public class Cart {
    private List<Item> contents =new LinkedList<Item>();
    private boolean isEmpty = true;

    public Cart() {}
    
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
