package pe.avanzza.core.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pe.avanzza.security.configuration.JwtAuthEntryPoint;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gloria R. Leyva Jerez
 * Create responses to handle exceptions thrown in the system
 */
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionResponseHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionResponseHandler.class);

    /*This are internal error code*/
    private static final int ERROR_CODE_HttpMediaTypeNotSupportedException = 1;
    private static final int ERROR_CODE_ConstraintViolationException_MODEL = 2;
    private static final int ERROR_CODE_ConstraintViolationException_DB = 3;
    private static final int ERROR_CODE_ResourceNotFoundException = 4;
    private static final int ERROR_CODE_HttpMessageNotReadableException = 5;
    private static final int ERROR_CODE_HttpRequestMethodNotSupportedException = 6;
    private static final int ERROR_CODE_MethodArgumentTypeMismatchException = 7;
    private static final int ERROR_CODE_AuthenticationException = 8;
    private static final int ERROR_CODE_AccessDeniedException = 9;

    private final ErrorContentHandler contentHandler;

    @Resource
    private final Environment env;

    /**
     * Create response to handle an exception thrown by Unauthorized
     *
     * @param request JwtAuthEntryPoint Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(AuthenticationException.class)
    @ApiResponse(responseCode = "401", description = Constants.unauthorizedError_resp_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.unauthorizedError_resp_name, summary = "401 from the service directly",
                            value = Constants.unauthorizedError_resp_example)}))
    public ResponseEntity<ErrorInfo> unauthorizedError(HttpServletRequest request, AuthenticationException e) {

        LOG.error("Se ha producido una excepción AuthenticationException porque no se encuentra registrado y/o logueado");

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String errorMessage = contentHandler.unauthorizedError();
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_AuthenticationException,
                "AuthenticationException");

        return new ResponseEntity<>(errorInfo, contentHandler.unauthorizedError(errorMessage), status);

    }

    /**
     * Create response to handle an exception thrown by Unauthorized
     *
     * @param request JwtAuthEntryPoint Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ApiResponse(responseCode = "401", description = Constants.accessDeniedError_resp_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.accessDeniedError_resp_name, summary = "401 from the service directly",
                            value = Constants.accessDeniedError_resp_example)}))
    public ResponseEntity<ErrorInfo> accessDeniedError(HttpServletRequest request, AccessDeniedException e) {

        LOG.error("Se ha producido una excepción AccessDeniedException porque no tiene permisos para acceder al endpoint");

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String errorMessage = contentHandler.accessDeniedErrorError();
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_AccessDeniedException,
                "AccessDeniedException");

        return new ResponseEntity<>(errorInfo, contentHandler.accessDeniedError(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by empty content
     *
     * @param request HttpServletRequest Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ApiResponse(responseCode = "417", description = Constants.contentError_resp_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.contentError_resp_name, summary = "417 from the service directly",
                            value = Constants.contentError_resp_example)}))
    public ResponseEntity<ErrorInfo> contentError(HttpServletRequest request) {

        LOG.error("Se ha producido una excepción HttpMediaTypeNotSupportedException porque conetenido en el body está vacío o no es el esperado");

        HttpStatus status = HttpStatus.EXPECTATION_FAILED;
        String errorMessage = contentHandler.contentArgumentError();
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_HttpMediaTypeNotSupportedException,
                "HttpMediaTypeNotSupportedException");

        return new ResponseEntity<>(errorInfo, contentHandler.notMethodArgumentTypeAlert(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by resource not found
     *
     * @param e ResourceNotFoundException Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ApiResponse(responseCode = "404", description = Constants.notFound_resp_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.notFound_resp_name, summary = "404 from the service directly",
                            value = Constants.notFound_resp_example)}))
    public ResponseEntity<ErrorInfo> notFound(HttpServletRequest request, ResourceNotFoundException e) {
        LOG.error("Se ha producido una excepción ResourceNotFoundException porque el recurso " + e.getResourceName() +
                " con " + e.getFieldName() + " = " + e.getFieldValue() + " no existe");

        HttpStatus status = HttpStatus.NOT_FOUND;
        String errorMessage = contentHandler.notFoundAlertMessage(e.getResourceName(), e.getFieldName(), String.valueOf(e.getFieldValue()));
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_ResourceNotFoundException,
                "ResourceNotFoundException");

        return new ResponseEntity<>(errorInfo, contentHandler.notFoundAlert(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by constraint violation in database manipulation process. For example: UK o FK violation
     *
     * @param e ConstraintViolationException Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    @ApiResponse(responseCode = "409", description = Constants.constraintViolation_resp_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.constraintViolation_resp_name, summary = "409 from the service directly",
                            value = Constants.constraintViolation_resp_example)}))
    public ResponseEntity<ErrorInfo> constraintViolation(HttpServletRequest request, org.hibernate.exception.ConstraintViolationException e) {
        LOG.error("Se ha producido una excepción " + "ConstraintViolationException" + " durante la manipulación  de la base de datos (DML)");

        int state = Integer.parseInt(e.getSQLState());
        String errorMessage = "";
        String detail = e.getSQLException().getLocalizedMessage().split("Detail: ")[1];
        if (state != -1) {
            switch (state) {
                case 23503:
                    LOG.error("Violación de llave foránea. Detalles: " + detail);
                    errorMessage = env.getProperty("violation.fk") + " " + env.getProperty("detail") + " " + detail;
                    break;
                case 23505:
                    LOG.error("Violación de llave única. Detalles: " + detail);
                    errorMessage = env.getProperty("violation.uk") + " " + env.getProperty("detail") + " " + detail;
                    break;
                default:
                    errorMessage = env.getProperty("unresolved.db");
                    break;
            }
        }

        HttpStatus status = HttpStatus.CONFLICT;
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_ConstraintViolationException_DB,
                "ConstraintViolationException-hibernate");

        return new ResponseEntity<>(errorInfo, contentHandler.validationConstrainAlert(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by not readable HTTP
     *
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ApiResponse(responseCode = "400", description = Constants.notReadable_resp_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.notReadable_resp_name, summary = "400 from the service directly",
                            value = Constants.notReadable_resp_example)}))
    public ResponseEntity<ErrorInfo> notReadable(HttpServletRequest request) {
        LOG.error("Se ha producido una excepción HttpMessageNotReadableException porque el header es incorrecto para el método requerido");

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = contentHandler.notReadableAlertMessage();
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_HttpMessageNotReadableException,
                "HttpMessageNotReadableException");

        return new ResponseEntity<>(errorInfo, contentHandler.notReadableAlert(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by request to unsupported method
     *
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ApiResponse(responseCode = "405", description = Constants.notSupported_resp_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.notSupported_resp_name, summary = "405 from the service directly",
                            value = Constants.notSupported_example)}))
    public ResponseEntity<ErrorInfo> notSupported(HttpServletRequest request) {
        LOG.error("Se ha producido una excepción HttpRequestMethodNotSupportedException porque el método solicitado por el recurso no está soportado");

        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        String errorMessage = contentHandler.methodNotSupportedMessage();
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_HttpRequestMethodNotSupportedException,
                "HttpRequestMethodNotSupportedException");

        return new ResponseEntity<>(errorInfo, contentHandler.methodNotSupported(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by unsupported string in URL request
     *
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ApiResponse(responseCode = "405", description = Constants.typeMismatch_resp_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.typeMismatch_resp_name, summary = "405 from the service directly",
                            value = Constants.typeMismatch_resp_example)}))
    public ResponseEntity<ErrorInfo> typeMismatch(HttpServletRequest request) {
        LOG.error("Se ha producido una excepción MethodArgumentTypeMismatchException porque se ha especificado una cadena en el id que no machea con el tipo esperado");

        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        String errorMessage = contentHandler.notMethodArgumentTypeAlertMessage();
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_MethodArgumentTypeMismatchException,
                "MethodArgumentTypeMismatchException");

        return new ResponseEntity<>(errorInfo, contentHandler.notMethodArgumentTypeAlert(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by constraint violation in validation DTO process
     *
     * @param e ConstraintViolationException Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ApiResponse(responseCode = "422", description = Constants.validationViolation_description,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorInfo.class), examples = {
                    @ExampleObject(name = Constants.validationViolation_resp_name, summary = "422 from the service directly",
                            value = Constants.validationViolation_example)}))
    public ResponseEntity<ErrorInfo> methodValidationViolationConstraintHandler(HttpServletRequest request, ConstraintViolationException e) {
        LOG.error("Se ha producido una excepción " + "MethodArgumentNotValidException" + " debido a que existe un error de validación");

        // get spring errors
        Set<ConstraintViolation<?>> result = e.getConstraintViolations();

        // convert errors to standard string
        StringBuilder error = new StringBuilder();

        AtomicInteger s = new AtomicInteger(result.size());
        result.forEach(f -> {
            error.append(f.getMessage());
            s.getAndDecrement();
            if (s.getPlain() != 0) {
                error.append(", ");
            }
        });

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String errorMessage = contentHandler.validationInModelAlertMessage(error.toString());
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage,
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_ConstraintViolationException_MODEL,
                "ConstraintViolationException-javax-validation");

        return new ResponseEntity<>(errorInfo, contentHandler.validationInModelAlert(errorMessage), status);
    }

    /**
     * Create response to handle an exception thrown by constraint violation in validation DTO process
     *
     * @param e ConstraintViolationException Information for create a response to this exception
     * @return ResponseEntity The response created
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodValidationViolationConstraintHandler(HttpServletRequest request, MethodArgumentNotValidException e) {

        // get spring errors
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        // convert errors to standard string
        StringBuilder errorMessage = new StringBuilder();

        AtomicInteger s = new AtomicInteger(fieldErrors.size());
        fieldErrors.forEach(f -> {
            errorMessage.append(f.getDefaultMessage());
            s.getAndDecrement();
            if (s.getPlain() != 0) {
                errorMessage.append(", ");
            }
        });

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorInfo errorInfo = new ErrorInfo(
                request.getRequestURI(),
                request.getMethod(),
                errorMessage.toString(),
                status.getReasonPhrase(),
                status.value(),
                ERROR_CODE_ConstraintViolationException_MODEL,
                "ConstraintViolationException-javax-validation");

        return new ResponseEntity<>(errorInfo, contentHandler.validationInModelAlert(errorMessage.toString()), status);
    }


}

