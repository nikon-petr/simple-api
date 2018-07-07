package edu.nikon.simpleapi.api.organization;

import edu.nikon.simpleapi.api.common.BaseController;
import edu.nikon.simpleapi.api.organization.dto.OrganizationFilterDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationItemDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class OrganizationController  extends BaseController {

    private static final Logger logger = LogManager.getLogger(OrganizationController.class);

    @ApiOperation(value = "filterOrganizations", nickname = "filterOrganizations", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping(value = "/organization/list")
    public List<OrganizationItemDto> filterOrganizations(@RequestBody @Validated OrganizationFilterDto filterDto){
        return Collections.emptyList();
    }
}
