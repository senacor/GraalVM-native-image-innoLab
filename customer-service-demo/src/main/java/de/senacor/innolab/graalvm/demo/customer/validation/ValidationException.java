package de.senacor.innolab.graalvm.demo.customer.validation;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.client.HttpClientErrorException;

public class ValidationException extends RuntimeException {
    private HttpClientErrorException clientErrorException;

    public ValidationException(String message, HttpClientErrorException cause) {
        super(message, cause);
        this.clientErrorException = cause;
    }

    public String getDetails() {
        try {
            return new JSONObject(clientErrorException.getResponseBodyAsString())
                    .getString("error");
        } catch (JSONException e) {
            e.printStackTrace();
            return "<Could not read details>";
        }
    }
}
