package edu.nikon.simpleapi.api.organization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * DTO class used to represent short organization data in list
 */
@ApiModel("OrganizationItem")
public class OrganizationItemDto implements Serializable {

    /**
     * organization id
     */
    private long id;

    /**
     * organization name
     */
    private String name;

    /**
     * organization activity state
     */
    private Boolean active;

    public OrganizationItemDto() {
    }

    public OrganizationItemDto(long id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

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
    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrganizationItemDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
