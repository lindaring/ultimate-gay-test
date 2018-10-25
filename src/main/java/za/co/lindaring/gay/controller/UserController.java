package za.co.lindaring.gay.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.lindaring.gay.exception.TechnicalException;
import za.co.lindaring.gay.exception.UserNotFoundException;
import za.co.lindaring.gay.model.GetAllUsersResponse;
import za.co.lindaring.gay.model.GetUserResponse;
import za.co.lindaring.gay.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value="/ultimate-gay-test/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/{id}")
    @ApiOperation(notes="Get specific user", value="Get specific user")
    public ResponseEntity<GetUserResponse> getUser(
            @ApiParam(value="User ID", required=true) @PathVariable int id) throws TechnicalException, UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping(value="/all")
    @ApiOperation(notes="Get all users", value="Get all users")
    public ResponseEntity<GetAllUsersResponse> getAllUsers() throws TechnicalException, UserNotFoundException {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
