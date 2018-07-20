package edu.nikon.simpleapi.api.user;

import edu.nikon.simpleapi.api.common.response.Response;
import edu.nikon.simpleapi.api.user.dto.FilterUserDto;
import edu.nikon.simpleapi.api.user.dto.SaveUserDto;
import edu.nikon.simpleapi.api.user.dto.UpdateUserDto;
import edu.nikon.simpleapi.api.user.dto.UserDetailedDto;
import edu.nikon.simpleapi.api.user.dto.UserItemDto;
import edu.nikon.simpleapi.api.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * User api controller
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Filter users", nickname = "filterUser", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 500, message = "Server error")})
    @PostMapping(value = "/list", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public List<UserItemDto> filterUser(
            @RequestBody @Validated FilterUserDto filterDto) {
        logger.debug("Received: {}", filterDto);
        List<UserItemDto> users = userService.filter(filterDto);
        return users;
    }

    @ApiOperation(value = "Get user by id", nickname = "getUserById", httpMethod = "GET")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public UserDetailedDto getUserById(@PathVariable("id") long id) {
        logger.debug("Received: id={}", id);
        UserDetailedDto user = userService.findById(id);
        return user;
    }

    @ApiOperation(value = "Save user", nickname = "saveUser", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response<?> saveUser(@RequestBody @Validated SaveUserDto saveDto) {
        logger.debug("Received: {}", saveDto);
        userService.save(saveDto);
        return Response.successOperation();
    }

    @ApiOperation(value = "Update user", nickname = "updateUser", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping(value = "/update", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response<?> updateUser(@RequestBody @Validated UpdateUserDto updateDto) {
        logger.debug("Received: {}", updateDto);
        userService.update(updateDto);
        return Response.successOperation();
    }
}
