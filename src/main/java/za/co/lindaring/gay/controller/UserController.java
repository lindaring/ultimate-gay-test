package za.co.lindaring.gay.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            @ApiParam(value="User ID", required=true) @PathVariable int id) {
        GetUserResponse user = userService.getUser(id);
        if (user != null)
            return ResponseEntity.ok(user);
        else
            return new ResponseEntity<>(new GetUserResponse(), HttpStatus.NOT_FOUND);
    }

}
