package com.TD.BL_Monolith_TD.api.controllers;

import com.TD.BL_Monolith_TD.api.dto.requests.CommentRequest;
import com.TD.BL_Monolith_TD.api.dto.response.CommentResponse;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.ICommentService;
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
@RequestMapping("/comment")
@Tag(name = "comment")
@AllArgsConstructor
public class CommentController {
    @Autowired
    private final ICommentService commentService;

    // get

    @GetMapping
    public ResponseEntity<List<CommentResponse>> get(){
        return ResponseEntity.ok(this.commentService.getAll());
    }

    // 6.2
    //getById

    @GetMapping(path = "/{id}")
    public ResponseEntity<CommentResponse> getById(
        @PathVariable Long id
    ){
        return ResponseEntity.ok(this.commentService.find(id));
    }

    @GetMapping(path = "/place/{id}")
    public ResponseEntity<List<CommentResponse>> getByIdPlace(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.commentService.getAllByIdPlace(id));
    }


    // insert

    @PostMapping
    public ResponseEntity<CommentResponse> insert(
        @Validated @RequestBody CommentRequest comment){
            return ResponseEntity.ok(this.commentService.create(comment));
        }

    // eliminar

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id){
        Map<String,String> response = new HashMap<>();
        response.put("message","Se elimino el post correctamente");
        
        this.commentService.delete(id);
        return ResponseEntity.ok(response);
    }

    //.6.1
    // update

    @PutMapping(path = "/{id}")
    public ResponseEntity<CommentResponse>update(
            @PathVariable Long id,
            @Validated @RequestBody CommentRequest commentRequest
    ){
        return ResponseEntity.ok(this.commentService.update(id,commentRequest));
    }
    
}
