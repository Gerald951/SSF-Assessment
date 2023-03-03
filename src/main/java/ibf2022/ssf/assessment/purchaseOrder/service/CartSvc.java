package ibf2022.ssf.assessment.purchaseOrder.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import ibf2022.ssf.assessment.purchaseOrder.model.Item;
import ibf2022.ssf.assessment.purchaseOrder.model.Quotation;
import ibf2022.ssf.assessment.purchaseOrder.repository.CartRepo;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class CartSvc {

    @Autowired
    private CartRepo cartRepo;

    public static final String[] ITEM_NAMES = {
		"apple", "orange", "bread", "cheese", "chicken", "mineral_water", "instant_noodles"
	};

	private final Set<String> itemNames = new HashSet<String>();


    public List<ObjectError> validateCart(Item item) {
		List<ObjectError> errors = new LinkedList<>();
		FieldError error;

		if (!itemNames.contains(item.getName().toLowerCase())) {
			error = new FieldError("name", "name"
					, "We do not stock %s".formatted(item.getName()));
			errors.add(error);
		}

        if ((item.getQuantity()) <= 0) {
            error = new FieldError("quantity", "quantity"
					, "You must add at least 1 item");
			errors.add(error);
        }

		return errors;
	}

	private String restEndpoint = "https://quotation.chuklee.com"; 

    public Quotation getQuotations(List<String> items) throws Exception {

        JsonArrayBuilder arrOfItems = Json.createArrayBuilder();

        for (int i=0; i<items.size(); i++) {
            arrOfItems.add(items.get(i)).build();
        }
        
    // Make the request 
    RequestEntity<String> req = RequestEntity.post(restEndpoint)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(arrOfItems.toString(), String.class);

    // Make the call to QSys
    RestTemplate template = new RestTemplate();

    ResponseEntity<String> resp = null;

    String payload = "";
    int statusCode = 500;
	Quotation quotation = new Quotation();
    try {
        resp = template.exchange(req, String.class);
        payload = resp.getBody();
        statusCode = resp.getStatusCode().value();
    } catch (HttpClientErrorException ex) {
        payload = ex.getResponseBodyAsString();
        statusCode = ex.getStatusCode().value();
		quotation.setErrorMessage(payload);
		quotation.setErrorStatusCode(statusCode);
		System.out.println("{ error %d: error message:%s}".formatted(statusCode, payload));
        return quotation;
    } 

		// Parse the result to Quotation
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject quotationJson = reader.readObject();

		JsonObject quoteId = quotationJson.getJsonObject("quoteId");

        JsonObject quotations = quotationJson.getJsonObject("quotations");
        InputStream quo = new ByteArrayInputStream((quotations.toString()).getBytes());
        LinkedHashMap<String,Float> quotationMap = new ObjectMapper().readValue(quo, LinkedHashMap.class);

		// Set the quoteId & quotations
		quotation.setQuoteId(quoteId.toString());
        quotation.setQuotations(quotationMap);

		return quotation;
  
    }



    
    
}
