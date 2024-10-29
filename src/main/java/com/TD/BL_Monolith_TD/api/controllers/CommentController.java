package com.TD.BL_Monolith_TD.api.controllers;

import com.TD.BL_Monolith_TD.api.dto.requests.CommentRequest;
import com.TD.BL_Monolith_TD.api.dto.response.CommentResponse;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing comments.
 * Provides endpoints to create, retrieve, update, and delete comments.
 */
@RestController
@RequestMapping("/comment")
@Tag(name = "comment", description = "API for managing comments")
@AllArgsConstructor
public class CommentController {
    @Autowired
    private final ICommentService commentService;

    /**
     * Retrieves a list of all comments.
     *
     * @return ResponseEntity containing a list of CommentResponse.
     */
    @Operation(summary = "Get all comments", description = "Retrieves a list of all comments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CommentResponse>> get(){
        return ResponseEntity.ok(this.commentService.getAll());
    }


    /**
     * Retrieves a comment by its ID.
     *
     * @param id The ID of the comment.
     * @return ResponseEntity containing the CommentResponse.
     */
    @Operation(summary = "Get comment by ID", description = "Retrieves a comment by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<CommentResponse> getById(
        @PathVariable Long id
    ){
        return ResponseEntity.ok(this.commentService.find(id));
    }

    /**
     * Retrieves comments by place ID.
     *
     * @param id The ID of the place.
     * @return ResponseEntity containing a list of CommentResponse.
     */
    @Operation(summary = "Get comments by place ID", description = "Retrieves comments by place ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Place not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping(path = "/place/{id}")
    public ResponseEntity<List<CommentResponse>> getByIdPlace(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.commentService.getAllByIdPlace(id));
    }


    /**
     * Inserts a new comment.
     *
     * @param comment The CommentRequest containing comment details.
     * @return ResponseEntity containing the created CommentResponse.
     */
    @Operation(summary = "Create a new comment", description = "Inserts a new comment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<CommentResponse> insert(
        @Validated @RequestBody CommentRequest comment){
            return ResponseEntity.ok(this.commentService.create(comment));
        }

    /**
     * Deletes a comment by its ID.
     *
     * @param id The ID of the comment.
     * @return ResponseEntity with a message confirming deletion.
     */
    @Operation(summary = "Delete a comment", description = "Deletes a comment by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id){
        Map<String,String> response = new HashMap<>();
        response.put("message","Se elimino el post correctamente");
        
        this.commentService.delete(id);
        return ResponseEntity.ok(response);
    }


    /**
     * Updates an existing comment by its ID.
     *
     * @param id The ID of the comment.
     * @param commentRequest The CommentRequest containing updated comment details.
     * @return ResponseEntity containing the updated CommentResponse.
     */
    @Operation(summary = "Update a comment", description = "Updates an existing comment by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<CommentResponse>update(
            @PathVariable Long id,
            @Validated @RequestBody CommentRequest commentRequest
    ){
        return ResponseEntity.ok(this.commentService.update(id,commentRequest));
    }
    
}
