package com.TD.BL_Monolith_TD.api.error_handler;

import com.TD.BL_Monolith_TD.api.dto.requests.UserRequest;
import com.TD.BL_Monolith_TD.util.exceptions.IdNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SomeController {

    @GetMapping("/throw-id-not-found")
    public ResponseEntity<Void> throwIdNotFoundException() {
        throw new IdNotFoundException("ID not found");
    }

    @PostMapping("/throw-validation-exception")
    public ResponseEntity<Void> throwValidationException(@Valid @RequestBody UserRequest request, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()){
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        return ResponseEntity.ok().build(); }
}
