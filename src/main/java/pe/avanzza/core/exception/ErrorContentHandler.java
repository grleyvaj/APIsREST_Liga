package pe.avanzza.core.exception;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Gloria R. Leyva Jerez
 * Build the content of a messahe, header or body of a ResponseEntity to Error shared.exception.
 * To Header: Create the HTTP header with a header name and description of the error occurred.
 * To Body: Create message error.
 * To Messade: Build error message
 */
@Component
public class ErrorContentHandler {

    @Resource
    private Environment env;

    private static final String UNPROCESSABLE_ENTITY = "app-error-unprocessable-entity";
    private static final String NOT_FOUND = "app-error-resource-not-found";
    private static final String CONFLICT = "app-error-conflict-db";
    private static final String BAD_REQUEST = "app-error-bad-request";
    private static final String METHOD_NOT_ALLOWED = "app-error-method-not-allowed";
    private static final String METHOD_UNAUTHORIZED = "app-error-method-unauthorized";


    /*  --------------- ALERT TO Rest Controller Advice    --------------------------------*/
    public HttpHeaders notFoundAlert(String errorMessage) {
        return createHeaders(NOT_FOUND, errorMessage);
    }

    public String notFoundAlertMessage(String resourceName, String fieldName, String fieldValue) {
        return head(resourceName, fieldName, fieldValue) + env.getProperty("not.found");
    }

    public HttpHeaders validationInModelAlert(String errorMessage) {
        return createHeaders(UNPROCESSABLE_ENTITY, errorMessage);
    }

    public HttpHeaders validationConstrainAlert(String errorMessage) {
        return createHeaders(CONFLICT, errorMessage);
    }

    public String validationInModelAlertMessage(String errorMessage) {
        return errorMessage.split(",").length > 1 ? env.getProperty("validation.intro") + " " + errorMessage : errorMessage;
    }

    public HttpHeaders notReadableAlert(String errorMessage) {
        return createHeaders(BAD_REQUEST, errorMessage);
    }

    public String notReadableAlertMessage() {
        return env.getProperty("notReadable");
    }

    public HttpHeaders methodNotSupported(String errorMessage) {
        return createHeaders(METHOD_NOT_ALLOWED, errorMessage);
    }

    public String methodNotSupportedMessage() {
        return env.getProperty("notMethodNotSupported");
    }

    public HttpHeaders notMethodArgumentTypeAlert(String errorMessage) {
        return createHeaders(METHOD_NOT_ALLOWED, errorMessage);
    }

    public String notMethodArgumentTypeAlertMessage() {
        return env.getProperty("notMethodArgumentType");
    }

    public String contentArgumentError() {
        return env.getProperty("contentError");
    }

    public HttpHeaders unauthorizedError(String errorMessage) {
        return createHeaders(METHOD_UNAUTHORIZED, errorMessage);
    }

    public String unauthorizedError() {
        return env.getProperty("unauthorizedError");
    }

    public HttpHeaders accessDeniedError(String errorMessage) {
        return createHeaders(METHOD_UNAUTHORIZED, errorMessage);
    }

    public String accessDeniedErrorError() {
        return env.getProperty("accessDeniedError");
    }

    private String head(String resourceName, String fieldName, String fieldValue) {
        return env.getProperty("resource") + " " + resourceName + " " + env.getProperty("with") + " " +
                fieldName + " = " + fieldValue + " ";
    }

    private static HttpHeaders createHeaders(String header, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(header, message);
        return headers;
    }
}
