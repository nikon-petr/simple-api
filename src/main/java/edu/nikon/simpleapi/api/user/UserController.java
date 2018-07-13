package edu.nikon.simpleapi.api.user;

import edu.nikon.simpleapi.api.common.exception.DataConflictException;
import edu.nikon.simpleapi.api.common.exception.DataNotFoundException;
import edu.nikon.simpleapi.api.common.response.OperationResults;
import edu.nikon.simpleapi.api.common.response.Response;
import edu.nikon.simpleapi.api.common.response.dto.OperationResultDto;
import edu.nikon.simpleapi.api.user.domain.User;
import edu.nikon.simpleapi.api.user.dto.FilterUserDto;
import edu.nikon.simpleapi.api.user.dto.SaveUserDto;
import edu.nikon.simpleapi.api.user.dto.UpdateUserDto;
import edu.nikon.simpleapi.api.user.dto.UserDetailedDto;
import edu.nikon.simpleapi.api.user.dto.UserItemDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final List<User> users = new ArrayList<>(Arrays.asList(
            new User("User 1", "User 1", "User 1", "Position 1", "000000", 21L, "Doc Name 1", "000", new Date(),
                    826L, true, 1L),
            new User("User 2", "User 2", "User 2", "Position 2", "000000", 21L, "Doc Name 2", "000", new Date(),
                    826L, true, 1L),
            new User("User 3", "User 3", "User 3", "Position 3", "000000", 21L, "Doc Name 3", "000", new Date(),
                    826L, true, null)
    ));

    @ApiOperation(value = "Filter users", nickname = "filterUser", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 500, message = "Server error")})
    @PostMapping(value = "/list", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response<List<UserItemDto>> filterUser(
            @RequestBody @Validated FilterUserDto filterDto) {
        logger.debug("Received: {}", filterDto);
        List<UserItemDto> filteredUsers = users.stream()
                .filter(o -> Objects.equals(o.getOfficeId(), filterDto.getOfficeId()) ||
                        Objects.equals(o.getFirstName(), filterDto.getFirstName()) ||
                        Objects.equals(o.getSecondName(), filterDto.getSecondName()) ||
                        Objects.equals(o.getMiddleName(), filterDto.getMiddleName()) ||
                        Objects.equals(o.getPosition(), filterDto.getPosition()) ||
                        Objects.equals(o.getDocCode(), filterDto.getDocCode()) ||
                        Objects.equals(o.getCitizenshipCode(), filterDto.getCitizenshipCode()))
                .map(o -> new UserItemDto(o.getId(), o.getFirstName(), o.getSecondName(), o.getMiddleName(), o.getPosition()))
                .collect(Collectors.toList());
        logger.debug("Sent: {}", filteredUsers);
        return Response.data(filteredUsers);
    }

    @ApiOperation(value = "Get user by id", nickname = "getUserById", httpMethod = "GET")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Response<UserDetailedDto> getUserById(@PathVariable("id") long id) {
        logger.debug("Received: id={}", id);
        UserDetailedDto user = users.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .map(o -> new UserDetailedDto(
                        o.getId(),
                        o.getFirstName(),
                        o.getSecondName(),
                        o.getMiddleName(),
                        o.getPosition(),
                        o.getPhone(),
                        o.getDocName(),
                        o.getDocNumber(),
                        o.getDocDate(),
                        o.getCitizenshipCode(),
                        "Russia",
                        o.getIdentified()
                ))
                .orElseThrow(() -> new DataNotFoundException(String.format("User with id=%s not found", id)));
        logger.debug("Sent: {}", user);
        return Response.data(user);
    }

    @ApiOperation(value = "Save user", nickname = "saveUser", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response<OperationResultDto> saveUser(@RequestBody @Validated SaveUserDto saveDto) {
        logger.debug("Received: {}", saveDto);
        users.add(new User(
                saveDto.getFirstName(),
                saveDto.getSecondName(),
                saveDto.getMiddleName(),
                saveDto.getPosition(),
                saveDto.getPhone(),
                saveDto.getDocCode(),
                saveDto.getDocName(),
                saveDto.getDocNumber(),
                saveDto.getDocDate(),
                saveDto.getCitizenshipCode(),
                saveDto.getIdentified(),
                null
        ));
        return Response.operationResult(OperationResults.SUCCESS);
    }

    @ApiOperation(value = "Update user", nickname = "updateUser", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping(value = "/update", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response<OperationResultDto> updateUser(@RequestBody @Validated UpdateUserDto updateDto) {
        User User = users.stream()
                .filter(o -> o.getId() == updateDto.getId())
                .findFirst()
                .orElseThrow(() -> new DataConflictException("User does not exists"));
        User.setFirstName(updateDto.getFirstName());
        User.setSecondName(updateDto.getSecondName());
        User.setMiddleName(updateDto.getMiddleName());
        User.setPosition(updateDto.getPosition());
        User.setPhone(updateDto.getPhone());
        User.setDocName(updateDto.getDocName());
        User.setDocNumber(updateDto.getDocNumber());
        User.setDocDate(updateDto.getDocDate());
        User.setCitizenshipCode(updateDto.getCitizenshipCode());
        User.setIdentified(updateDto.getIdentified());
        logger.debug("Received: {}", updateDto);
        return Response.operationResult(OperationResults.SUCCESS);
    }
}
