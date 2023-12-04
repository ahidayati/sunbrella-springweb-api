package com.hb.authen_demo.controllers;


import com.hb.authen_demo.models.User;
import com.hb.authen_demo.services.OrderService;
import com.hb.authen_demo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200") /*access cross-origin for angular app*/
@CrossOrigin
@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Get all users")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{user}")
    @Operation(summary = "Get one user by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User is found",
                    content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "[\n" +
                                            "  {\n" +
                                            "    \"input\": \"Not Found\",\n" +
                                            "    \"error\": \"User not found\",\n" +
                                            "    \"timestamp\": 1696601194307\n" +
                                            "  }\n" +
                                            "]"
                            )
                    ))
    })
    public User getOneUser(@PathVariable(required = false) @Parameter(name = "user", description = "User id", example = "1") User user ) {
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }


    @PostMapping(value = "/")
    @Operation(summary = "Insert user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User is successfully added"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User with this email is already exist"
                    )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    examples = {
                            @ExampleObject(
                                    name = "User",
                                    summary = "User without Order",
                                    value = "{\n" +
                                            "        \"email\": \"test3@mail.com@\",\n" +
                                            "        \"firstname\": \"test3-firstname\",\n" +
                                            "        \"lastname\": \"test3\",\n" +
                                            "        \"password\": \"pass1234\",\n" +
                                            "        \"roles\": [\n" +
                                            "            {\n" +
                                            "                \"id\": 2\n" +
                                            "            }\n" +
                                            "        ]\n" +
                                            "    }"
                            )
                    }
            )
    )
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User existUser = userService.findByEmail(user.getEmail());

        if(existUser != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email is already exist");
        }
        user = userService.saveUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{user}")
    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User is successfully modified"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User does not exist"
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    examples = {
                            @ExampleObject(
                                    name = "User",
                                    summary = "User without Order",
                                    value = "{\n" +
                                            "        \"email\": \"test3@mail.com@\",\n" +
                                            "        \"firstname\": \"test3-firstname\",\n" +
                                            "        \"lastname\": \"test3\",\n" +
                                            "        \"password\": \"pass1234\",\n" +
                                            "        \"roles\": [\n" +
                                            "            {\n" +
                                            "                \"id\": 2\n" +
                                            "            }\n" +
                                            "        ]\n" +
                                            "    }"
                            )
                    }
            )
    )
    public ResponseEntity<User> updateUser(
            @PathVariable(name = "user", required = false) @Parameter(name = "user", description = "User id", example = "2") User user,
            @Valid @RequestBody User userUpdate
    ) {
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        userUpdate.setId(user.getId());
        userService.saveUser(userUpdate);

        return new ResponseEntity<User>(userUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{user}")
    @Operation(summary = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User is successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "This user does not exist"
            )
    })
    public void deleteOneUser(@PathVariable(name = "user", required = false) @Parameter(name = "user", description = "User id", example = "2") User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User cannot be found");
        } else {
            if (!orderService.findOrderByUser(user).isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete user that has order(s)");
            }
            userService.removeUser(user);
        }
    }

}
