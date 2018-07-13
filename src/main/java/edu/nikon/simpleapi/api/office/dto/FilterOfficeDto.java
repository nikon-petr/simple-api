package edu.nikon.simpleapi.api.office.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ApiModel("FilterOffice")
public class FilterOfficeDto {

    private Long orgId;
    private String name;
    private String phone;
    private Boolean isActive;

    public FilterOfficeDto() {
    }

    public FilterOfficeDto(Long orgId, String name, String phone, Boolean isActive) {
        this.orgId = orgId;
        this.name = name;
        this.phone = phone;
        this.isActive = isActive;
    }

    @ApiModelProperty(required = true)
    @NotNull
    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    @Size(max = 20, message = "Name length should be less than 20 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FilterOfficeDto{");
        sb.append("orgId=").append(orgId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
