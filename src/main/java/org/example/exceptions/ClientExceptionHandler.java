package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> exceptionHandler(ClientNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Клиент под данным ID не найден.");
    }

    @ExceptionHandler(TableIsEmptyException.class)
    public ResponseEntity<Object> exceptionHandler(TableIsEmptyException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Таблица пуста. Следует добавить новых клиентов в неё, чтобы взаимодействовать с ней.");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> exceptionHandler(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Вводите корректный ID, используя только цифры.");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> exceptionHandler(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Добавление или удаление клиентов невозможно с браузера.");
    }
}