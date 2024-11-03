package org.example.sentineldemo.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomBlockExceptionHandler {
    @ExceptionHandler(BlockException.class)
    public ResponseEntity<String> handleBlockException(BlockException ex) {
        // 自定义返回信息
        String message = "{\"code\": 429, \"message\": \"Request was blocked by Sentinel: " + ex.getClass().getSimpleName() + "\"}";
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(message);
    }
}
