package edu.nikon.simpleapi.api.office;

import edu.nikon.simpleapi.api.common.response.OperationResults;
import edu.nikon.simpleapi.api.common.response.Response;
import edu.nikon.simpleapi.api.common.response.dto.OperationResultDto;
import edu.nikon.simpleapi.api.office.domain.Office;
import edu.nikon.simpleapi.api.office.dto.FilterOfficeDto;
import edu.nikon.simpleapi.api.office.dto.OfficeDetailedDto;
import edu.nikon.simpleapi.api.office.dto.OfficeItemDto;
import edu.nikon.simpleapi.api.office.dto.SaveOfficeDto;
import edu.nikon.simpleapi.api.office.dto.UpdateOfficeDto;
import edu.nikon.simpleapi.api.office.service.OfficeService;
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
 * Office api controller
 */
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final Logger logger = LoggerFactory.getLogger(OfficeController.class);
    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "Filter offices", nickname = "filterOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 500, message = "Server error")})
    @PostMapping(value = "/list", consumes = APPLICATION_JSON_VALUE)
    public Response<List<OfficeItemDto>> filterOffice(
            @RequestBody @Validated FilterOfficeDto filterDto) {
        logger.debug("Received: {}", filterDto);
        List<OfficeItemDto> offices = officeService.filter(filterDto);
        logger.debug("Sent: {}", offices);
        return Response.data(offices);
    }

    @ApiOperation(value = "Get office by id", nickname = "getOfficeById", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public Response<OfficeDetailedDto> getOfficeById(@PathVariable("id") long id) {
        logger.debug("Received: id={}", id);
        OfficeDetailedDto office = officeService.findById(id);
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
    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE)
    public Response<OperationResultDto> saveOffice(@RequestBody @Validated SaveOfficeDto saveDto) {
        logger.debug("Received: {}", saveDto);
        Office office = officeService.save(saveDto);
        logger.debug("Saved: {}", office);
        return Response.operationResult(OperationResults.SUCCESS);
    }

    @ApiOperation(value = "Update office", nickname = "updateOffice", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping(value = "/update", consumes = APPLICATION_JSON_VALUE)
    public Response<OperationResultDto> updateOffice(@RequestBody @Validated UpdateOfficeDto updateDto) {
        logger.debug("Received: {}", updateDto);
        Office office = officeService.update(updateDto);
        logger.debug("Updated: {}", office);
        return Response.operationResult(OperationResults.SUCCESS);
    }
}
