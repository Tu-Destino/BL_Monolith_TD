package com.TD.BL_Monolith_TD.api.controllers;


import com.TD.BL_Monolith_TD.api.dto.requests.PublicationRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PublicationResponse;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPublicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/publication")
@Tag(name = "Publication")
@AllArgsConstructor

public class PublicationController {
    @Autowired
    private final IPublicationService publicationService;

    @GetMapping
    public ResponseEntity<List<PublicationResponse>> getAll(){
        return ResponseEntity.ok(this.publicationService.getAll());
    }
    @GetMapping(path = "/{id}")
    public  ResponseEntity<PublicationResponse> getById(
            @PathVariable Long id
    ){
        return  ResponseEntity.ok(this.publicationService.find(id));
    }
    @PostMapping
    public ResponseEntity<PublicationResponse> insert(
            @Validated @RequestBody PublicationRequest request
    ){
        return ResponseEntity.ok(this.publicationService.create(request));

    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete (@PathVariable Long id)
    {
        Map<String,String> response = new HashMap<>();
        response.put("message","Se elimino el publicación correctamente");
        this.publicationService.delete(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<PublicationResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody PublicationRequest Request
    ){
        return  ResponseEntity.ok(this.publicationService.update(id,Request));
    }


}
