package fr.didiersenou.defimeningesapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final String baseUrl;
    private final boolean exposeDebugMessage;

    public GlobalExceptionHandler(@Value("${app.api.base-url}") String baseUrl,
            @Value("${app.errors.expose-debug-message:false}") boolean exposeDebugMessage) {
        this.baseUrl = baseUrl;
        this.exposeDebugMessage = exposeDebugMessage;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        return problem(HttpStatus.NOT_FOUND, "Resource Not Found", "not-found", ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentials(BadCredentialsException ex) {
        return problem(HttpStatus.UNAUTHORIZED, "Authentication Failed", "unauthorized", "Invalid email or password");
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    public ProblemDetail handleJwtAuthentication(JwtAuthenticationException ex) {
        return problem(HttpStatus.UNAUTHORIZED, "Unauthorized", "unauthorized", ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDenied(AccessDeniedException ex) {
        return problem(HttpStatus.FORBIDDEN, "Forbidden", "forbidden", "Access denied");
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ProblemDetail handleDuplicateResource(DuplicateResourceException ex) {
        return problem(HttpStatus.CONFLICT, "Resource Conflict", "conflict", ex.getMessage());
    }

    @ExceptionHandler(GameAlreadyStartedException.class)
    public ProblemDetail handleGameAlreadyStarted(GameAlreadyStartedException ex) {
        return problem(HttpStatus.BAD_REQUEST, "Game Already Started", "game-already-started", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.putIfAbsent(error.getField(), error.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors()
                .forEach(error -> errors.putIfAbsent(error.getObjectName(), error.getDefaultMessage()));

        ProblemDetail problemDetail = problem(HttpStatus.BAD_REQUEST, "Validation Error", "validation-error",
                "Validation failed");
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpected(Exception ex) {
        log.error("Unhandled exception", ex);

        ProblemDetail problemDetail = problem(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                "internal-error", "An unexpected error occurred");
        if (exposeDebugMessage) {
            problemDetail.setProperty("debugMessage", ex.getMessage());
        }
        return problemDetail;
    }

    private ProblemDetail problem(HttpStatus status, String title, String type, String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setType(URI.create(baseUrl + "/errors/" + type));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
