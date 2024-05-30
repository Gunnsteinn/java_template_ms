package com.template.app.apirest;

import com.template.app.apirest.dto.input.UserRequest;
import com.template.app.apirest.dto.output.UserResponse;
import com.template.app.apirest.mapper.UserMapper;
import com.template.app.application.ports.input.*;
import com.template.app.application.validation.ErrorCode;
import com.template.app.commons.helper.web.ErrorResponse;
import com.template.app.domain.exception.BadRequestException;
import com.template.app.domain.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final GetAllUseCase getAllUseCase;
    private final GetByIdUseCase getByIdUseCase;
    private final GetByReferenceUseCase getByReferenceUseCase;
    private final SaveUseCase saveUseCase;
    private final UpdateUseCase updateUseCase;
    private final DeleteUseCase deleteUseCase;
    private final UserMapper mapper;

    /**
     * Return a list of users
     *
     * @return A list of {@link UserResponse}
     */
    @Operation(summary = "Get a list of active users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of active users")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<UserResponse> getAll() {
        return mapper.convertToResponseListFromDomainList(getAllUseCase.execute());
    }

    /**
     * Return a user by its id
     *
     * @param id User id
     * @return {@link UserResponse} data
     */
    @Operation(summary = "Get an active user by it's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User data"),
            @ApiResponse(responseCode = "404", description = "User not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/{id}")
    public UserResponse getById(@Parameter(description = "User id") @PathVariable(value = "id") long id) {
        var user =
                getByIdUseCase
                        .execute(id)
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                ErrorCode.USER_NOT_FOUND.getCode(),
                                                ErrorCode.USER_NOT_FOUND.getDescription(id)));
        return mapper.convertToResponseFromDomain(user);
    }

    /**
     * Return a user by its reference
     *
     * @param reference User reference
     * @return {@link UserResponse} data
     */
    @Operation(summary = "Get an active user by it's reference")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User data"),
            @ApiResponse(responseCode = "404", description = "User not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/references/{reference}")
    public UserResponse getByReference(@Parameter(description = "User reference") @PathVariable(value = "reference") String reference) {
        var user =
                getByReferenceUseCase
                        .execute(reference)
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                ErrorCode.USER_BY_REFERENCE_NOT_FOUND.getCode(),
                                                ErrorCode.USER_BY_REFERENCE_NOT_FOUND.getDescription(reference)));
        return mapper.convertToResponseFromDomain(user);
    }

    /**
     * Save a user
     *
     * @param user {@link UserRequest} data to save
     * @return {@link UserResponse} created user
     */
    @Operation(summary = "Create an User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created user", content = {@Content(schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Not valid user data", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserResponse save(@Parameter(description = "User data") @Valid @RequestBody UserRequest user) {
        var createdUser =
                saveUseCase.execute(mapper.convertToDomain(user))
                        .orElseThrow(() -> new NotFoundException(
                                ErrorCode.USER_BY_EMAIL_NOT_FOUND.getCode(),
                                ErrorCode.USER_BY_EMAIL_NOT_FOUND.getDescription(user.getEmail())));
        return mapper.convertToResponseFromDomain(createdUser);
    }

    /**
     * Save a user
     *
     * @param user {@link UserRequest} data to save
     * @return {@link UserResponse} created user
     */
    @Operation(summary = "Register an User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register user", content = {@Content(schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Not valid user data", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserResponse register(@Parameter(description = "User data") @Valid @RequestBody UserRequest user) {
        return save(user);
    }

    /**
     * Update a user
     *
     * @param userToUpdate {@link UserRequest} data to update
     * @return {@link UserResponse} updated user
     */
    @Operation(summary = "Update an existing User. Also, active inactive users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated user", content = {@Content(schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Not valid user data", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public UserResponse update(@Parameter(description = "User id") @PathVariable(value = "id") long id, @Parameter(description = "User data") @Valid @RequestBody UserRequest userToUpdate) {
        var updatedUser =
                updateUseCase.execute(id, userToUpdate)
                        .orElseThrow(() -> new NotFoundException(
                                ErrorCode.USER_NOT_FOUND.getCode(),
                                ErrorCode.USER_NOT_FOUND.getDescription(id)));

        return mapper.convertToResponseFromDomain(updatedUser);
    }

    /**
     * Delete an user
     *
     * @param id User id
     * @return {@link UserResponse} deleted user
     */
    @Operation(summary = "Delete an user. Marks the User as inactive")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted user", content = {@Content(schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public UserResponse delete(@Parameter(description = "User id") @PathVariable(value = "id") long id) {
        var deletedUser =
                deleteUseCase.execute(id)
                        .orElseThrow(() -> new BadRequestException(
                                ErrorCode.USER_COULD_NOT_BE_DELETED.getCode(),
                                ErrorCode.USER_COULD_NOT_BE_DELETED.getDescription(id)));
        return mapper.convertToResponseFromDomain(deletedUser);
    }
}