package cl.duoc.backend_bikes_app.controller;

import cl.duoc.backend_bikes_app.service.BicicletaNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BicicletaNoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrada(BicicletaNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuerpoError(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(MethodArgumentNotValidException ex) {
        String detalle = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cuerpoError(detalle));
    }

    private Map<String, Object> cuerpoError(String mensaje) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("timestamp", Instant.now().toString());
        cuerpo.put("mensaje", mensaje);
        return cuerpo;
    }
}
