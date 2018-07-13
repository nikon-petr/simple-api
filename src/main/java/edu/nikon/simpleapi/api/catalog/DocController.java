package edu.nikon.simpleapi.api.catalog;

import edu.nikon.simpleapi.api.catalog.dto.CountryItemDto;
import edu.nikon.simpleapi.api.catalog.dto.DocItemDto;
import edu.nikon.simpleapi.api.common.response.Response;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class DocController {

    private final Logger logger = LoggerFactory.getLogger(DocController.class);

    public final List<CountryItemDto> countries = new ArrayList<>(Arrays.asList(
            new CountryItemDto(643, "Российская Федерация")
    ));

    public final List<DocItemDto> docs = new ArrayList<>(Arrays.asList(
            new DocItemDto(21, "Паспорт гражданина РФ")
    ));

    @ApiOperation(value = "Return docs", nickname = "docs", httpMethod = "GET")
    @GetMapping(value = "/docs", produces = APPLICATION_JSON_VALUE)
    public Response<List<DocItemDto>> getDocs() {
        return Response.data(docs);
    }

    @ApiOperation(value = "Return counties", nickname = "countries", httpMethod = "GET")
    @GetMapping(value = "/countries", produces = APPLICATION_JSON_VALUE)
    public Response<List<CountryItemDto>> getCountries() {
        return Response.data(countries);
    }
}
