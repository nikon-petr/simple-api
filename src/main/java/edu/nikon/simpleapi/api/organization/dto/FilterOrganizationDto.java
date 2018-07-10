package edu.nikon.simpleapi.api.organization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel("OrganizationFilter")
public class FilterOrganizationDto {

    private String name;
    private String inn;
    private Boolean active;

    @NotBlank(message = "Name is required and should not be empty")
    @Length(max = 20, message = "Name length should be less than 20 characters")
    @ApiModelProperty(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$", message = "INN is invalid")
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
        final StringBuilder sb = new StringBuilder("FilterOrganizationDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", inn='").append(inn).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
