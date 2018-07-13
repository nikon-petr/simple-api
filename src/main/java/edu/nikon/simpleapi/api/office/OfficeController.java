package edu.nikon.simpleapi.api.office;

import edu.nikon.simpleapi.api.common.exception.DataConflictException;
import edu.nikon.simpleapi.api.common.exception.DataNotFoundException;
import edu.nikon.simpleapi.api.common.response.OperationResults;
import edu.nikon.simpleapi.api.common.response.Response;
import edu.nikon.simpleapi.api.common.response.dto.OperationResultDto;
import edu.nikon.simpleapi.api.office.domain.Office;
import edu.nikon.simpleapi.api.office.dto.FilterOfficeDto;
import edu.nikon.simpleapi.api.office.dto.OfficeDetailedDto;
import edu.nikon.simpleapi.api.office.dto.OfficeItemDto;
import edu.nikon.simpleapi.api.office.dto.SaveOfficeDto;
import edu.nikon.simpleapi.api.office.dto.UpdateOfficeDto;
import edu.nikon.simpleapi.api.organization.OrganizationController;
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    private final Logger logger = LoggerFactory.getLogger(OfficeController.class);

    private final List<Office> offices = new ArrayList<>(Arrays.asList(
            new Office("Office 1", "Address 1", "000000", null, 1L),
            new Office("Office 2", "Address 2", "111111", true, null),
            new Office("Office 3", "Address 3", "222222", false, 1L)
    ));

    @ApiOperation(value = "Filter offices", nickname = "filterOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 500, message = "Server error")})
    @PostMapping(value = "/list/{orgId}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response<List<OfficeItemDto>> filterOffice(
            @RequestBody @Validated FilterOfficeDto filterDto, @PathVariable("orgId") String orgId) {
        logger.debug("Received: {}", filterDto);
        List<OfficeItemDto> filteredOffices = offices.stream()
                .filter(o -> Objects.equals(o.getOrgId(), filterDto.getOrgId()) ||
                        Objects.equals(o.getName(), filterDto.getName()) ||
                        Objects.equals(o.getPhone(), filterDto.getPhone()) ||
                        Objects.equals(o.getActive(), filterDto.getActive()))
                .map(o -> new OfficeItemDto(o.getId(), o.getName(), o.getActive()))
                .collect(Collectors.toList());
        logger.debug("Sent: {}", filteredOffices);
        return Response.data(filteredOffices);
    }

    @ApiOperation(value = "Get office by id", nickname = "getOfficeById", httpMethod = "GET")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Response<OfficeDetailedDto> getOfficeById(@PathVariable("id") long id) {
        logger.debug("Received: id={}", id);
        OfficeDetailedDto office = offices.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .map(o -> new OfficeDetailedDto(o.getId(), o.getName(), o.getAddress(), o.getPhone(), o.getActive()))
                .orElseThrow(() -> new DataNotFoundException(String.format("Office with id=%s not found", id)));
        logger.debug("Sent: {}", office);
        return Response.data(office);
    }

    @ApiOperation(value = "Save office", nickname = "saveOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response<OperationResultDto> saveOffice(@RequestBody @Validated SaveOfficeDto saveDto) {
        logger.debug("Received: {}", saveDto);
        offices.add(new Office(saveDto.getName(), saveDto.getAddress(), saveDto.getPhone(), saveDto.getActive(), null));
        return Response.operationResult(OperationResults.SUCCESS);
    }

    @ApiOperation(value = "Update office", nickname = "updateOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping(value = "/update", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response<OperationResultDto> updateOffice(@RequestBody @Validated UpdateOfficeDto updateDto) {
        Office office = offices.stream()
                .filter(o -> o.getId() == updateDto.getId())
                .findFirst()
                .orElseThrow(() -> new DataConflictException("Office does not exists"));
        office.setName(updateDto.getName());
        office.setAddress(updateDto.getAddress());
        office.setPhone(updateDto.getPhone());
        office.setActive(updateDto.getActive());
        logger.debug("Received: {}", updateDto);
        return Response.operationResult(OperationResults.SUCCESS);
    }
}
