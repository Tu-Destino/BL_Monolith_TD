package com.TD.BL_Monolith_TD.api.controllers;

import com.TD.BL_Monolith_TD.api.dto.requests.PlaceRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PlaceResponse;
import com.TD.BL_Monolith_TD.api.dto.response.SearchListPlaceResponse;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPlaceService;
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

import java.util.List;

/**
 * Controller for managing places.
 * Provides endpoints to create, retrieve, update, and delete places.
 */
@RestController
@RequestMapping("/place")
@Tag(name = "Place", description = "API for managing places")
@AllArgsConstructor
public class PlaceController {
    @Autowired
    private final IPlaceService placeService;

    /**
     * Retrieves a list of all places.
     *
     * @return ResponseEntity containing a list of PlaceResponse.
     */
    @Operation(summary = "Get all places", description = "Retrieves a list of all places.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PlaceResponse>> getAll(){
        return ResponseEntity.ok(this.placeService.getAll());
    }


    /**
     * Retrieves a place by its ID.
     *
     * @param id The ID of the place.
     * @return ResponseEntity containing the PlaceResponse.
     */
    @Operation(summary = "Get place by ID", description = "Retrieves a place by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceResponse.class))),
            @ApiResponse(responseCode = "404", description = "Place not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<PlaceResponse> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.placeService.find(id));
    }


    /**
     * Retrieves a place by its title.
     *
     * @param title The title of the place.
     * @return ResponseEntity containing the PlaceResponse.
     */
    @Operation(summary = "Find place by title", description = "Retrieves a place by its title.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceResponse.class))),
            @ApiResponse(responseCode = "404", description = "Place not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping(path = "/findTitle/{title}")
    public ResponseEntity<PlaceResponse> findByTitle(@PathVariable String title){
        return ResponseEntity.ok(this.placeService.findByPlaceName(title));
    }


    /**
     * Retrieves a list of all place titles.
     *
     * @return ResponseEntity containing a list of place titles.
     */
    @Operation(summary = "Get list of place titles", description = "Retrieves a list of all place titles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SearchListPlaceResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping(path = "/getListTitle")
    public ResponseEntity<List<String>> getListTitle(){
        return ResponseEntity.ok(this.placeService.getListNamePlace());
    }


    /**
     * Inserts a new place.
     *
     * @param place The PlaceRequest containing place details.
     * @return ResponseEntity containing the created PlaceResponse.
     */
    @Operation(summary = "Create a new place", description = "Inserts a new place.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Place created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<PlaceResponse> insert(
            @Validated @RequestBody PlaceRequest place
            ){
        return ResponseEntity.ok(this.placeService.create(place));
    }

    /**
     * Deletes a place by its ID.
     *
     * @param id The ID of the place.
     * @return ResponseEntity with no content.
     */
    @Operation(summary = "Delete a place", description = "Deletes a place by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Place deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Place not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.placeService.delete(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * Updates an existing place by its ID.
     *
     * @param id The ID of the place.
     * @param place The PlaceRequest containing updated place details.
     * @return ResponseEntity containing the updated PlaceResponse.
     */
    @Operation(summary = "Update a place", description = "Updates an existing place by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Place updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Place not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<PlaceResponse>update(
            @PathVariable Long id,
            @Validated @RequestBody PlaceRequest place
    ){
        return ResponseEntity.ok(this.placeService.update(id,place));
    }
    @PostMapping(path = "/list")
    public ResponseEntity<List<PlaceResponse>> insertList(
            @Validated @RequestBody List<PlaceRequest> places
    ){
        return ResponseEntity.ok(this.placeService.CreateList(places));
    }

    @GetMapping(path = "/getIdByTitle/{title}")
    public ResponseEntity<Long> getIDByTitle(
            @PathVariable String title
    ){
        return ResponseEntity.ok(this.placeService.getIdByTitle(title));
    }
}
