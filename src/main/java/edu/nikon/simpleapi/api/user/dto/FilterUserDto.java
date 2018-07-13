package edu.nikon.simpleapi.api.user.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel("FilterUser")
public class FilterUserDto {

    private Long officeId;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private Long docCode;
    private Long citizenshipCode;

    public FilterUserDto() {
    }

    public FilterUserDto(Long officeId, String firstName, String secondName, String middleName, String position,
                         Long docCode, Long citizenshipCode) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.docCode = docCode;
        this.citizenshipCode = citizenshipCode;
    }

    @ApiModelProperty(required = true)
    @NotNull
    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    @Size(max = 35, message = "First name length should be less than 35 characters")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(max = 35, message = "Second name length should be less than 35 characters")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Size(max = 35, message = "Middle name length should be less than 35 characters")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Size(max = 40, message = "Position length should be less than 40 characters")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getDocCode() {
        return docCode;
    }

    public void setDocCode(Long docCode) {
        this.docCode = docCode;
    }

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Long citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FilterUserDto{");
        sb.append("officeId=").append(officeId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", position='").append(position).append('\'');
        sb.append(", docCode=").append(docCode);
        sb.append(", citizenshipCode=").append(citizenshipCode);
        sb.append('}');
        return sb.toString();
    }
}
