package com.TD.BL_Monolith_TD.api.controllers;

import com.TD.BL_Monolith_TD.api.dto.requests.PlaceRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PlaceResponse;
import com.TD.BL_Monolith_TD.api.dto.response.SearchListPlaceResponse;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPlaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
@Tag(name = "Place")
@AllArgsConstructor
public class PlaceController {
    @Autowired
    private final IPlaceService placeService;

    @GetMapping
    public ResponseEntity<List<PlaceResponse>> getAll(){
        return ResponseEntity.ok(this.placeService.getAll());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<PlaceResponse> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.placeService.find(id));
    }
    @GetMapping(path = "/findTitle/{title}")
    public ResponseEntity<PlaceResponse> findByTitle(@PathVariable String title){
        return ResponseEntity.ok(this.placeService.findByPlaceName(title));
    }
    @GetMapping(path = "/listTitle")
    public ResponseEntity<SearchListPlaceResponse> getListTitle(){
        return ResponseEntity.ok(this.placeService.getListNamePlace());
    }

    @PostMapping
    public ResponseEntity<PlaceResponse> insert(
            @Validated @RequestBody PlaceRequest place
            ){
        return ResponseEntity.ok(this.placeService.create(place));
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.placeService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<PlaceResponse>update(
            @PathVariable Long id,
            @Validated @RequestBody PlaceRequest place
    ){
        return ResponseEntity.ok(this.placeService.update(id,place));
    }


}
