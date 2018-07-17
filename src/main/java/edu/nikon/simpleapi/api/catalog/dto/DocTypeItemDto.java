package edu.nikon.simpleapi.api.catalog.dto;

import io.swagger.annotations.ApiModel;

/**
 * DTO class used to represent document types in list
 */
@ApiModel("DocumentType")
public class DocTypeItemDto {

    /**
     * document code
     */
    private String code;

    /**
     * document name
     */
    private String name;

    public DocTypeItemDto() {
    }

    public DocTypeItemDto(String code, String name) {
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
        final StringBuilder sb = new StringBuilder("DocTypeItemDto{");
        sb.append("code=").append(code);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
