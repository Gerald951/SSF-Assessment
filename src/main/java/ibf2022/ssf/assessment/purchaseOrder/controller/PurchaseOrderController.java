package ibf2022.ssf.assessment.purchaseOrder.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.ssf.assessment.purchaseOrder.model.Cart;
import ibf2022.ssf.assessment.purchaseOrder.model.Item;
import ibf2022.ssf.assessment.purchaseOrder.model.Quotation;
import ibf2022.ssf.assessment.purchaseOrder.model.ShippingAddress;
import ibf2022.ssf.assessment.purchaseOrder.service.CartSvc;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class PurchaseOrderController {

    @Autowired
    private CartSvc cartSvc;

    @GetMapping(path = "/")
    public String getView1(Model model, HttpSession sess) {
        Cart cart = (Cart)sess.getAttribute("cart");
        if(null == cart){
            cart = new Cart();
            sess.setAttribute("cart",cart);
        }

        model.addAttribute("item", new Item());
        model.addAttribute("cart", cart);

        return "view1";
    }

    @PostMapping(path = "/addCart")
    public String getView1(Model model, HttpSession sess, 
    @Valid Item item, BindingResult bindings) {

        Cart cart = (Cart)sess.getAttribute("cart");

        if (bindings.hasErrors()) {
            model.addAttribute("item", item);
            model.addAttribute("cart", cart);
            return "view1";
        }
			
        List<ObjectError> errors = cartSvc.validateCart(item);
        if (!errors.isEmpty()) {
			for (ObjectError err: errors)
				bindings.addError(err);

            model.addAttribute("item", item);
            model.addAttribute("cart", cart);

			return "view1";
		}

        cart.addItemToCart(item);

		model.addAttribute("item", item);
        model.addAttribute("cart", cart);

        return "redirect:/";
    }

    @GetMapping(path = "/shippingaddress")
    public String getShippingAddress(Model model, HttpSession sess) { 

        Cart cart = (Cart) sess.getAttribute("cart");
        if((null==cart) || (cart.getisEmpty())) {
            return "redirect:/";
        }
        
        ShippingAddress shippingAddress = (ShippingAddress)sess.getAttribute("shippingAddress");
        if(null == shippingAddress){
            shippingAddress = new ShippingAddress();
            sess.setAttribute("shippingAddress",shippingAddress);
        }

        model.addAttribute("shippingAddress", shippingAddress);

        return "view2";
    }

    @GetMapping(path = "/quotation")
    public String getQuotations(List<String> items, HttpSession sess) throws Exception {
        //Retrieving quotation
        Quotation quotation = cartSvc.getQuotations(items);

        //printing error message
        if (quotation.errorMessageIsEmpty()) {
            return "{ error:%d, error message:%s }".formatted(quotation.getErrorStatusCode(), opt.getErrorMessage());
         }
        
        float totalCost = 0f;

        Cart cart = (Cart)sess.getAttribute("cart");
        
        //converting quotation into 
        Set<String> keySet = quotation.getQuotations().keySet();

        String[] keyArray
            = keySet.toArray(new String[keySet.size()]);

        for (int i=0; i<keyArray.length; i++) {
            for (int j=0; j<cart.getContents().size(); j++) {
                List<Item> item = cart.getItemName(keyArray[i]);

                if (cart.isItemName()) {
                    for (int k=0; k<item.size(); k++) {
                        totalCost += (item.getQuantity()*quotation.quotations.keySet);
                    }
                }
            }

        }

         return "Success";
    }

    
    

}
