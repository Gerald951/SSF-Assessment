package ibf2022.ssf.assessment.purchaseOrder.configuration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ibf2022.ssf.assessment.purchaseOrder.model.Cart;

@Configuration
public class AppConfig {

    @Bean("my-cart")
    public Map<String, Object> createOrder() {
        final Map<String, Object> order = new HashMap<>();
        return order;
    }
    
}
