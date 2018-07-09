package edu.nikon.simpleapi.api.organization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.nikon.simpleapi.api.common.dto.ApiResponseDto;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(value = "OrganizationItem", parent = ApiResponseDto.class)
public class OrganizationItemDto implements Serializable {

    private long id;
    private String name;
    private boolean active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("isActive")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("OrganizationItem{id=%s, name=%s, active=%s}", id, name, active);
    }
}
