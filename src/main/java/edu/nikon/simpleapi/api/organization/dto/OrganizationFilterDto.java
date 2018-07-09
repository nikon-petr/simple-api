package edu.nikon.simpleapi.api.organization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel("OrganizationFilter")
public class OrganizationFilterDto {

    private String name;
    private String inn;
    private Boolean active;

    @NotNull(message = "name is required")
    @Pattern(regexp = "^(?!\\s*$).+", message = "name should not be empty")
    @Length(max = 255, message = "name length should not be greater than 255 characters")
    @ApiModelProperty(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$", message = "inn should contains 10 or 12 digits only")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @JsonProperty(value = "isActive")
    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("OrganizationFilterDto{name=%s, inn=%s, active=%s}", name, inn, active);
    }
}
