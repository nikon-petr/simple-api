package edu.nikon.simpleapi.api.office.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ApiModel("UpdateOffice")
public class UpdateOfficeDto {

    private long id;
    private String name;
    private String address;
    private String phone;
    private Boolean active;

    public UpdateOfficeDto() {
    }

    public UpdateOfficeDto(long id, String name, String address, String phone, Boolean active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.active = active;
    }

    @ApiModelProperty(required = true)
    @NotNull
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @JsonProperty(value = "isActive")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateOfficeDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
