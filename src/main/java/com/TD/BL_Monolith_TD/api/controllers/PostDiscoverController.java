package com.TD.BL_Monolith_TD.api.controllers;

import com.TD.BL_Monolith_TD.api.dto.requests.LabelsRequest;
import com.TD.BL_Monolith_TD.api.dto.requests.PostDiscoverRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PostDiscoverResponse;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPostDiscoverService;
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
 * Controller for managing post discoveries.
 * Provides endpoints to create, retrieve, update, and delete post discoveries.
 */
@RestController
@RequestMapping("/postDiscover")
@Tag(name = "postDiscover", description = "API for managing post discoveries")
@AllArgsConstructor
public class PostDiscoverController {
    @Autowired
    private final IPostDiscoverService postDiscoverService;


    /**
     * Retrieves a list of all post discoveries.
     *
     * @return ResponseEntity containing a list of PostDiscoverResponse.
     */
    @Operation(summary = "Get all post discoveries", description = "Retrieves a list of all post discoveries.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostDiscoverResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PostDiscoverResponse>> get(){
        return ResponseEntity.ok(this.postDiscoverService.getAll());
    }

    /**
     * Retrieves a post discovery by its ID.
     *
     * @param id The ID of the post discovery.
     * @return ResponseEntity containing the PostDiscoverResponse.
     */
    @Operation(summary = "Get post discovery by ID", description = "Retrieves a post discovery by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostDiscoverResponse.class))),
            @ApiResponse(responseCode = "404", description = "Post discovery not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping(path = "/{id}")
    public  ResponseEntity<PostDiscoverResponse> getById(
            @PathVariable String id
    ){
        return  ResponseEntity.ok(this.postDiscoverService.find(id));
    }


    /**
     * Inserts a new post discovery.
     *
     * @param request The PostDiscoverRequest containing post discovery details.
     * @return ResponseEntity containing the created PostDiscoverResponse.
     */
    @Operation(summary = "Create a new post discovery", description = "Inserts a new post discovery.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post discovery created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostDiscoverResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<PostDiscoverResponse> insert(
            @Validated @RequestBody PostDiscoverRequest request
            ){
        return ResponseEntity.ok(this.postDiscoverService.create(request));

    }


    /**
     * Deletes a post discovery by its ID.
     *
     * @param id The ID of the post discovery.
     * @return ResponseEntity with a message confirming deletion.
     */
    @Operation(summary = "Delete a post discovery", description = "Deletes a post discovery by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post discovery deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "404", description = "Post discovery not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete (@PathVariable String id)
    {
        Map<String,String> response = new HashMap<>();
        response.put("message","Se elimino el post correctamente");
        this.postDiscoverService.delete(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing post discovery by its ID.
     *
     * @param id The ID of the post discovery.
     * @param postDiscoverRequest The PostDiscoverRequest containing updated post discovery details.
     * @return ResponseEntity containing the updated PostDiscoverResponse.
     */
    @Operation(summary = "Update a post discovery", description = "Updates an existing post discovery by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post discovery updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostDiscoverResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Post discovery not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDiscoverResponse> update(
            @PathVariable String id,
            @Validated @RequestBody PostDiscoverRequest postDiscoverRequest
    ){
        return  ResponseEntity.ok(this.postDiscoverService.update(id,postDiscoverRequest));
    }

    @PostMapping(path = "/tags")
    public ResponseEntity<List<PostDiscoverResponse>> getByTags(
            @RequestBody @Validated LabelsRequest lisTags
    ){
        return ResponseEntity.ok(this.postDiscoverService.findByTags(lisTags));

    }
    @GetMapping(path = "/AllTags")
    public ResponseEntity<List<String>> getALLTags(
    ){
        return ResponseEntity.ok(this.postDiscoverService.getTags());

    }

}
