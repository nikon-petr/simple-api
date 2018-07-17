package edu.nikon.simpleapi.api.organization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO class used to receive organization data for saving
 */
@ApiModel("SaveOrganization")
public class SaveOrganizationDto {

    /**
     * organization name
     */
    private String name;

    /**
     * organization full name
     */
    private String fullName;

    /**
     * organization INN
     */
    private String inn;

    /**
     * organization KPP
     */
    private String kpp;

    /**
     * organization address
     */
    private String address;

    /**
     * organization phone
     */
    private String phone;

    /**
     * organization activity state
     */
    private Boolean active;

    public SaveOrganizationDto() {
    }

    @ApiModelProperty(required = true)
    @NotBlank(message = "Name is required and should not be empty")
    @Size(max = 20, message = "name length should be less than 20 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(required = true)
    @NotBlank(message = "Full name is required and should not be empty")
    @Size(max = 75, message = "Full name length should be less than 75 characters")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @ApiModelProperty(required = true)
    @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$", message = "INN is invalid")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @ApiModelProperty(required = true)
    @Pattern(regexp = "^[0-9]{4}[0-9A-Z]{2}[0-9]{3}$", message = "KPP is invalid")
    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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

    @JsonProperty(value = "isActive")
    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SaveOrganizationDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", inn='").append(inn).append('\'');
        sb.append(", kpp='").append(kpp).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
