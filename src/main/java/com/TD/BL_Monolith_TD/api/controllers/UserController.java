package com.TD.BL_Monolith_TD.api.controllers;

import com.TD.BL_Monolith_TD.api.dto.requests.UserRequest;
import com.TD.BL_Monolith_TD.api.dto.response.UserResponse;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IUserService;
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
 * Controller for managing users.
 *
 * Provides endpoints to create, retrieve, update, and delete users.
 */
/**
 * Controller for managing users.
 *
 * Provides endpoints to create, retrieve, update, and delete users.
 */
@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "API for managing users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final IUserService userService;

    /**
     * Retrieves a list of all users.
     *
     * @return ResponseEntity containing a list of UserResponse.
     */
    @Operation(summary = "Get all users", description = "Retrieves a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<UserResponse>> get(){
        return ResponseEntity.ok(this.userService.getAll());
    }


    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user.
     * @return ResponseEntity containing the UserResponse.
     */
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getById(
            @PathVariable String id
    ){
        return ResponseEntity.ok(this.userService.find(id));
    }


    /**
     * Inserts a new user.
     *
     * @param user The UserRequest containing user details.
     * @return ResponseEntity containing the created UserResponse.
     */
    @Operation(summary = "Create a new user", description = "Inserts a new user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserResponse>insert(
            @Validated @RequestBody UserRequest user
            ){
        return ResponseEntity.ok(this.userService.create(user));
    }


    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user.
     * @return ResponseEntity with no content.
     */
    @Operation(summary = "Delete a user", description = "Deletes a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id
    ){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * Updates an existing user by their ID.
     *
     * @param id The ID of the user.
     * @param user The UserRequest containing updated user details.
     * @return ResponseEntity containing the updated UserResponse.
     */
    @Operation(summary = "Update a user", description = "Updates an existing user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponse>update(
            @PathVariable String id,
            @Validated @RequestBody UserRequest user
    ){
        return ResponseEntity.ok(this.userService.update(id, user));
    }
}
