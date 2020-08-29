package pe.avanzza.league.interfaces.shared;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Gloria R. Leyva Jerez
 * Build the content of a message, header or body of a ResponseEntity to Successful Response.
 * To Header: Create the HTTP header with a header name and description of the error occurred.
 * To Body: Create message error.
 * To Message: Build success message
 */
@Component
public class SuccessfulContentHandler {

    @Resource
    private Environment env;

    private static final String SUCCESS_HEADER = "app-success";

    public String successCreateAlert(String entityName) {
        return entityName + " " + env.getProperty("add.success");
    }

    public String successUpdateAlert(String entityName) {
        return entityName + " " + env.getProperty("update.success");
    }

    public String successPartialUpdateAlert(String entityName) {
        return entityName + " " + env.getProperty("partialUpdate.success");
    }


    public String successDeleteAlert(String entityName) {
        return entityName + " " + env.getProperty("delete.success");
    }

    public static HttpHeaders createHeaders(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(SUCCESS_HEADER, message);
        return headers;
    }
}
