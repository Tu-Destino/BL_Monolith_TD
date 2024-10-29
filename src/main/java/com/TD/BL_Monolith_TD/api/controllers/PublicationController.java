package com.TD.BL_Monolith_TD.api.controllers;


import com.TD.BL_Monolith_TD.api.dto.requests.PublicationRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PublicationResponse;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPublicationService;
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
 * Controller for managing publications.
 * Provides endpoints to create, retrieve, update, and delete publications.
 */
@RestController
@RequestMapping(path = "/publication")
@Tag(name = "Publication", description = "API for managing publications")
@AllArgsConstructor
public class PublicationController {
    @Autowired
    private final IPublicationService publicationService;

    /**
     * Retrieves a list of all publications.
     *
     * @return ResponseEntity containing a list of PublicationResponse.
     */
    @Operation(summary = "Get all publications", description = "Retrieves a list of all publications.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicationResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PublicationResponse>> getAll(){
        return ResponseEntity.ok(this.publicationService.getAll());
    }

    /**
     * Retrieves a publication by its ID.
     *
     * @param id The ID of the publication.
     * @return ResponseEntity containing the PublicationResponse.
     */
    @Operation(summary = "Get publication by ID", description = "Retrieves a publication by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicationResponse.class))),
            @ApiResponse(responseCode = "404", description = "Publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping(path = "/{id}")
    public  ResponseEntity<PublicationResponse> getById(
            @PathVariable Long id
    ){
        return  ResponseEntity.ok(this.publicationService.find(id));
    }


    /**
     * Inserts a new publication.
     *
     * @param request The PublicationRequest containing publication details.
     * @return ResponseEntity containing the created PublicationResponse.
     */
    @Operation(summary = "Create a new publication", description = "Inserts a new publication.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<PublicationResponse> insert(
            @Validated @RequestBody PublicationRequest request
    ){
        return ResponseEntity.ok(this.publicationService.create(request));

    }

    /**
     * Deletes a publication by its ID.
     *
     * @param id The ID of the publication.
     * @return ResponseEntity with a message confirming deletion.
     */
    @Operation(summary = "Delete a publication", description = "Deletes a publication by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "404", description = "Publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete (@PathVariable Long id)
    {
        Map<String,String> response = new HashMap<>();
        response.put("message","Se elimino el publicación correctamente");
        this.publicationService.delete(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing publication by its ID.
     *
     * @param id The ID of the publication.
     * @param //request The PublicationRequest containing updated publication details.
     * @return ResponseEntity containing the updated PublicationResponse.
     */
    @Operation(summary = "Update a publication", description = "Updates an existing publication by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PublicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Publication not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<PublicationResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody PublicationRequest Request
    ){
        return  ResponseEntity.ok(this.publicationService.update(id,Request));
    }


}
