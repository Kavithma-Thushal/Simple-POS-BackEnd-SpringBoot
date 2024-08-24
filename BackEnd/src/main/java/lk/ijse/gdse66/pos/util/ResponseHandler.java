package lk.ijse.gdse66.pos.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 6:55 AM - 6/18/2024
 **/
@Slf4j
@ControllerAdvice
public class ResponseHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<String>> handleValidationException(MethodArgumentNotValidException e) {
        String errors = e.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error("\u001B[31m{}\u001B[0m", errors);
        Response<String> response = new Response<>(errors, HttpStatus.BAD_REQUEST, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}