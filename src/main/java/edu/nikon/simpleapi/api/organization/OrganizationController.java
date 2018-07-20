package edu.nikon.simpleapi.api.organization;

import edu.nikon.simpleapi.api.common.response.Response;
import edu.nikon.simpleapi.api.organization.dto.FilterOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationDetailedDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationItemDto;
import edu.nikon.simpleapi.api.organization.dto.SaveOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.UpdateOrganizationDto;
import edu.nikon.simpleapi.api.organization.service.OrganizationService;
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
 * Organization api controller
 */
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "Filter organizations", nickname = "filterOrganizations", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 500, message = "Server error")})
    @PostMapping(value = "/list", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public List<OrganizationItemDto> filterOrganizations(
            @RequestBody @Validated FilterOrganizationDto filterDto) {
        logger.debug("Received: {}", filterDto);
        List<OrganizationItemDto> organizations = organizationService.filter(filterDto);
        return organizations;
    }

    @ApiOperation(value = "Get organization by id", nickname = "getOrganizationById", httpMethod = "GET")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public OrganizationDetailedDto getOrganizationById(@PathVariable("id") long id) {
        logger.debug("Received: id={}", id);
        OrganizationDetailedDto organization = organizationService.findById(id);
        return organization;
    }

    @ApiOperation(value = "Save organization", nickname = "saveOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response<?> saveOrganization(@RequestBody @Validated SaveOrganizationDto organizationDto) {
        logger.debug("Received: {}", organizationDto);
        organizationService.save(organizationDto);
        return Response.successOperation();
    }

    @ApiOperation(value = "Update organization", nickname = "updateOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping(value = "/update", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response<?> updateOrganization(
            @RequestBody @Validated UpdateOrganizationDto organizationDto) {
        logger.debug("Received: {}", organizationDto);
        organizationService.update(organizationDto);
        return Response.successOperation();
    }
}
