package pe.avanzza.core.exception;


/**
 * @author Gloria R. Leyva Jerez
 *  * This class contains the OpenAPI documentation of the EXCEPTIONS
 */
public class Constants {

    /***********************
     * ExceptionResponseHandler's Constants
     **********************/

    public static final String unauthorizedError_resp_description = "Se ha producido una excepción AuthenticationException.";
    public static final String unauthorizedError_resp_name = "Devuelve un ErrorInfo al producirse una excepción AuthenticationException debido a que hubo un error en la autenticación.";
    public static final String unauthorizedError_resp_example = "{\"uri\": \"/avanzza/v1/entity-uri/{id}\","
            + "\"method\": \"GET\","
            + "\"message\": \"Credenciales incorrectas\","
            + "\"http_status\": \"Unauthorized\","
            + "\"status_code\": 401,"
            + "\"code_error\": 8,"
            + "\"type\": \"AuthenticationException\""
            + "}";

    public static final String accessDeniedError_resp_description = "Se ha producido una excepción AccessDeniedException.";
    public static final String accessDeniedError_resp_name = "Devuelve un ErrorInfo al producirse una excepción AccessDeniedException debido a que ha sido denegado por el número de peticiones realizadas.";
    public static final String accessDeniedError_resp_example = "{\"uri\": \"/avanzza/v1/entity-uri/{id}\","
            + "\"method\": \"GET\","
            + "\"message\": \"Acceso denegado\","
            + "\"http_status\": \"Too Many Requests\","
            + "\"status_code\": 429,"
            + "\"code_error\": 9,"
            + "\"type\": \"AccessDeniedException\""
            + "}";

    public static final String notFound_resp_description = "Se ha producido una excepción ResourceNotFoundException.";
    public static final String notFound_resp_name = "Devuelve un ErrorInfo al producirse una excepción ResourceNotFoundException debido a que el recurso solicitado no existe.";
    public static final String notFound_resp_example = "{\"uri\": \"/avanzza/v1/entity-uri\","
            + "\"method\": \"GET\","
            + "\"message\": \"Recurso con identificador=50 no encontrado\","
            + "\"http_status\": \"Not Found\","
            + "\"status_code\": 404,"
            + "\"code_error\": 4,"
            + "\"type\": \"ResourceNotFoundException\""
            + "}";

    public static final String constraintViolation_resp_description = "Se ha producido una excepción ConstraintViolationshared.exception.";
    public static final String constraintViolation_resp_name = "Devuelve un ErrorInfo al producirse una excepción ConstraintViolationshared.exception durante la manipulación  de la base de datos (DML).";
    public static final String constraintViolation_resp_example = "{\"uri\": \"/avanzza/v1/entity-uri\","
            + "\"method\": \"POST\","
            + "\"message\": \"Se ha producido una violación de contraint durante la manipulación  de la base de datos (DML)\","
            + "\"http_status\": \"Conflict\","
            + "\"status_code\": 409,"
            + "\"code_error\": 3,"
            + "\"type\": \"ConstraintViolationshared\""
            + "}";

    public static final String notReadable_resp_description = "Se ha producido un BAD_REQUEST.";
    public static final String notReadable_resp_name = "Devuelve un ErrorInfo al producirse una excepción HttpMessageNotReadableshared.exception debido a que el header es incorrecto para el método requerido.";
    public static final String notReadable_resp_example = "{\"uri\": \"/avanzza/v1/entity-uri\","
            + "\"method\": \"GET\","
            + "\"message\": \"Se ha producido un error porque el header es incorrecto para el método requerido\","
            + "\"http_status\": \"Bad Request\","
            + "\"status_code\": 400,"
            + "\"code_error\": 5,"
            + "\"type\": \"HttpMessageNotReadableshared\""
            + "}";

    public static final String notSupported_resp_description = "Se ha producido una solicitud no soportada.";
    public static final String notSupported_resp_name = "Devuelve un ErrorInfo al producirse una excepción HttpMessageNotReadableshared.exception debido a que el método solicitado por el recurso no está soportado.";
    public static final String notSupported_example = "{\"uri\": \"/avanzza/v1/entity-uri\","
            + "\"method\": \"GET\","
            + "\"message\": \"Se ha producido un error porque el método solicitado por el recurso no está soportado\","
            + "\"http_status\": \"Method Not Allowed\","
            + "\"status_code\": 405,"
            + "\"code_error\": 6,"
            + "\"type\": \"HttpRequestMethodNotSupportedshared\""
            + "}";

    public static final String typeMismatch_resp_description = "Se ha producido una solicitud no soportada.";
    public static final String typeMismatch_resp_name = "Devuelve un ErrorInfo al producirse una excepción MethodArgumentTypeMismatchshared.exception debido a que el método solicitado por el recurso no está soportado.";
    public static final String typeMismatch_resp_example = "{\"uri\": \"/avanzza/v1/entity-uri\","
            + "\"method\": \"GET\","
            + "\"message\": \"Se ha producido un error porque se ha especificado una cadena que no machea con el tipo de dato esperado\","
            + "\"http_status\": \"Method Not Allowed\","
            + "\"status_code\": 405,"
            + "\"code_error\": 6,"
            + "\"type\": \"MethodArgumentTypeMismatchshared\""
            + "}";

    public static final String validationViolation_description = "El recurso especificado por parámetro no es válido.";
    public static final String validationViolation_resp_name = "Devuelve un ErrorInfo al producirse una excepción MethodArgumentNotValidshared.exception debido a que la entidad especificada por parámetro no es válida.";
    public static final String validationViolation_example = "{\"uri\": \"/avanzza/v1/entity-uri\","
            + "\"method\": \"PUT\","
            + "\"message\": \"Se ha producido un error porque la entidad especificada por parámetro no es válida\","
            + "\"http_status\": \"Unprocessable Entity\","
            + "\"status_code\": 422,"
            + "\"code_error\": 2,"
            + "\"type\": \"MethodArgumentNotValidshared\""
            + "}";

    public static final String contentError_resp_description = "Se ha producido una excepción HttpMediaTypeNotSupportedshared.exception.";
    public static final String contentError_resp_name = "Devuelve un ErrorInfo al producirse una excepción HttpMediaTypeNotSupportedshared.exception debido a que el conetenido en el body está vacío o no es el esperado.";
    public static final String contentError_resp_example = "{\"uri\": \"/avanzza/v1/entity-uri/{id}\","
            + "\"method\": \"PATCH\","
            + "\"message\": \"No se ha especificado contenido en el body o no es el esperado\","
            + "\"http_status\": \"Expectation failed\","
            + "\"status_code\": 417,"
            + "\"code_error\": 1,"
            + "\"type\": \"HttpMediaTypeNotSupportedshared\""
            + "}";

    /***********************
     * ErrorInfo's Constants
     **********************/
    public static final String error_schema_description = "Entidad que gestiona la información del error que se produce al lanzarse una excepción";

    public static final String uri_description = "URI donde se produce la excepción";
    public static final String uri_example = "/avanzza/v1/entity-uri";

    public static final String method_description = "Método que produce la excepción";
    public static final String method_example = "GET | POST | PUT | DELETE | ...";

    public static final String message_description = "Descripción del error";
    public static final String message_example = "Se ha producido un error debido a que ...";

    public static final String httpStatus_description = "HttpStatus de respuesta";
    public static final String httpStatus_example = "Bad Request | Not Found | Method Not Allowed | ConstraintViolationshared.exception | Precondition Failed | Unprocessable Entity | ...";

    public static final String statusCode_description = "Código de estado de respuesta automático";
    public static final String statusCode_example = "400 | 404 | 405 | 409 | 412 | 422 | ...";

    public static final String errorCode_description = "Código de error generado en el sistema";
    public static final String errorCode_example = "5| 4 | 6 | 3 | 1 | 2 | ...";

    public static final String type_description = "Excepción lanzada";
    public static final String type_example = "HttpMessageNotReadableshared.exception | ResourceNotFoundException | HttpRequestMethodNotSupportedshared.exception | ConstraintViolationshared.exception | NotValidIDshared.exception | Unprocessable Entity | ...";
}
