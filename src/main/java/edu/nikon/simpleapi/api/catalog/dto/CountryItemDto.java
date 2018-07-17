package edu.nikon.simpleapi.api.catalog.dto;

import io.swagger.annotations.ApiModel;

/**
 * DTO class used to represent country data in list
 */
@ApiModel("Country")
public class CountryItemDto {

    /**
     * country ISO code
     */
    private String code;

    /**
     * country name
     */
    private String name;

    public CountryItemDto() {
    }

    public CountryItemDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CountryItemDto{");
        sb.append("code=").append(code);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
