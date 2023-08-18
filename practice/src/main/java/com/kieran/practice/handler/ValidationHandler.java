package com.kieran.practice.handler;

import com.kieran.practice.domain.vo.response.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@RestControllerAdvice
public class ValidationHandler {

    /**
     * 验证异常通用返回
     *
     * @param e e
     * @param <T> <T>
     * @return 返回异常
     */
    @ExceptionHandler(ValidationException.class)
    public <T> ApiResult<T> handleBindException(ValidationException e) {
        return ApiResult.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

//    @Override
//    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String, String> messages = new HashMap<>();
//        BindingResult result = ex.getBindingResult();
//        if (result.hasErrors()) {
//            List<ObjectError> errors = result.getAllErrors();
//            for (ObjectError error : errors) {
//                FieldError fieldError = (FieldError) error;
//                messages.put(fieldError.getField(), fieldError.getDefaultMessage());
//            }
//        }
//
//        return new ResponseEntity<>(ApiResult.fail(HttpStatus.BAD_REQUEST.value(), messages.toString()),
//                HttpStatus.BAD_REQUEST);
//    }
}
