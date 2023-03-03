package ibf2022.ssf.assessment.purchaseOrder.model;

import java.util.LinkedHashMap;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Quotation {

    private String quoteId;
    private LinkedHashMap<String, Float> quotations = new LinkedHashMap<>();
    private String errorMessage = null;
    private Integer errorStatusCode = null;
    
    public Quotation() {
    }

    public String getQuoteId() {
        return quoteId;
    }
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public LinkedHashMap<String, Float> getQuotations() {
        return quotations;
    }
    public void setQuotations(LinkedHashMap<String, Float> quotations) {
        this.quotations = quotations;
    }
    public void addQuotation(String item, Float unitPrice) {
        this.quotations.put(item, unitPrice);
    }
    public Float getQuotation(String item) {
        return this.quotations.getOrDefault((Object)item, -1000000f);
    }

    public Boolean errorMessageIsEmpty() {
        return (errorMessage==null) ? true : false;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorStatusCode() {
        return this.errorStatusCode;
    }

    public void setErrorStatusCode(Integer errorStatusCode) {
        this.errorStatusCode = errorStatusCode;
    }

    public JsonObject toJSON() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();

        String quoString = mapper.writeValueAsString(quotations);
            
        return Json.createObjectBuilder()
        .add("quoteId", quoteId)
        .add("quotations", quoString)
        .build();
    }
}
    

