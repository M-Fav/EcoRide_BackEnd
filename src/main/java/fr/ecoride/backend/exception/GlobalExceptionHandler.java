package fr.ecoride.backend.exception;

import fr.ecoride.backend.dto.ErreurDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErreurDTO> handleCustomException(CustomException ex) {
        ErreurDTO errorResponse = new ErreurDTO(
                ex.getStatus().value(),
                ex.getStatus().name(),
                ex.getMessage()
        );
        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }
}
