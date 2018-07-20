package edu.nikon.simpleapi.api.catalog;

import edu.nikon.simpleapi.api.catalog.dto.CountryItemDto;
import edu.nikon.simpleapi.api.catalog.dto.DocTypeItemDto;
import edu.nikon.simpleapi.api.catalog.service.CountryService;
import edu.nikon.simpleapi.api.catalog.service.DocumentTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Catalog api controller
 */
@RestController
@RequestMapping("/api")
public class CatalogController {

    private final DocumentTypeService documentTypeService;
    private final CountryService countryService;

    @Autowired
    public CatalogController(DocumentTypeService documentTypeService,
                             CountryService countryService) {
        this.documentTypeService = documentTypeService;
        this.countryService = countryService;
    }

    @ApiOperation(value = "Return documents", nickname = "documents", httpMethod = "GET")
    @GetMapping(value = "/docs", produces = APPLICATION_JSON_VALUE)
    public List<DocTypeItemDto> getDocs() {
        return documentTypeService.findAll();
    }

    @ApiOperation(value = "Return counties", nickname = "countries", httpMethod = "GET")
    @GetMapping(value = "/countries", produces = APPLICATION_JSON_VALUE)
    public List<CountryItemDto> getCountries() {
        return countryService.findAll();
    }
}
