package edu.nikon.simpleapi.api.catalog.dto;

import io.swagger.annotations.ApiModel;

@ApiModel("Country")
public class CountryItemDto {

    private long code;
    private String name;

    public CountryItemDto() {
    }

    public CountryItemDto(long code, String name) {
        this.code = code;
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
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
