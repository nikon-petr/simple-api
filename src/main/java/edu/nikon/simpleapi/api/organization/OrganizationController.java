package edu.nikon.simpleapi.api.organization;

import edu.nikon.simpleapi.api.common.dto.ApiResponseDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationFilterDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationItemDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @ApiOperation(value = "filterOrganizations", nickname = "filterOrganizations", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseDto<List<OrganizationItemDto>> filterOrganizations(
            @RequestBody @Validated OrganizationFilterDto filterDto) {
        logger.debug(filterDto.toString());
        return new ApiResponseDto<>(Collections.emptyList());
    }
}
