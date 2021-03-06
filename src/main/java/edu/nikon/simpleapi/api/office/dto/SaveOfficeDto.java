package edu.nikon.simpleapi.api.office.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * DTO class used to receive office data for saving
 */
@ApiModel("SaveOffice")
public class SaveOfficeDto {

    /**
     * office name
     */
    private String name;


    /**
     * office address
     */
    private String address;

    /**
     * office phone
     */
    private String phone;

    /**
     * organization related to the office
     */
    private Long organizationId;


    /**
     * office activity state
     */
    private Boolean active;

    public SaveOfficeDto() {
    }

    public SaveOfficeDto(String name, String address, String phone, Long organizationId, Boolean active) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.organizationId = organizationId;
        this.active = active;
    }

    @ApiModelProperty(required = true)
    @NotBlank(message = "Name is required and should not be empty")
    @Size(max = 20, message = "Name length should be less than 20 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(required = true)
    @NotBlank(message = "Address is required and should not be empty")
    @Size(max = 175, message = "Address length should be less than 175 characters")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Pattern(regexp = "^[0-9]+$", message = "Phone number should contains only digit characters")
    @Size(max = 30, message = "Phone length should be less than 30 characters")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public SaveOfficeDto setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    @JsonProperty(value = "isActive")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SaveOfficeDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", organizationId=").append(organizationId);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
