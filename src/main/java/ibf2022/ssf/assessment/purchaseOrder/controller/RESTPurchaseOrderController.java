package ibf2022.ssf.assessment.purchaseOrder.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.ssf.assessment.purchaseOrder.model.Quotation;
import ibf2022.ssf.assessment.purchaseOrder.service.CartSvc;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/quotation")
public class RESTPurchaseOrderController {

    @Autowired
    private CartSvc cartSvc;
    
    @GetMapping
    public Optional<Quotation> getQuotations(List<String> items) throws Exception {
        
        Optional<Quotation> opt = cartSvc.getQuotations(items);

        if (opt.isEmpty()) 
			return Optional.empty();
         
        return opt;
    }

}
