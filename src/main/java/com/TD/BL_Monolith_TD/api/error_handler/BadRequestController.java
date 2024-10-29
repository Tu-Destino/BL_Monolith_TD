package com.TD.BL_Monolith_TD.api.error_handler;

import com.TD.BL_Monolith_TD.util.exceptions.IdNotFoundException;
import com.TD.BL_Monolith_TD.api.dto.errors.BaseErrorResponse;
import com.TD.BL_Monolith_TD.api.dto.errors.ErrorResponse;
import com.TD.BL_Monolith_TD.api.dto.errors.ErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {
    @ExceptionHandler(IdNotFoundException.class)
    public BaseErrorResponse handleIdNotFound(IdNotFoundException exception){

        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value()).status(HttpStatus.BAD_REQUEST.name()).message(exception.getMessage()).build();

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleErrors(MethodArgumentNotValidException exception){
        List<Map<String,String>> erroLis=new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fiel->{
            Map<String ,String > error = new HashMap<>();
            error.put("error",fiel.getDefaultMessage());
            error.put("field",fiel.getField());
            erroLis.add(error);
        });
        return ErrorsResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value()).status(HttpStatus.BAD_REQUEST.name()).errors(erroLis).build();
    }
}
