package edu.nikon.simpleapi.api.user.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO class used to transfer parameters necessary for filtering users
 */
@ApiModel("FilterUser")
public class FilterUserDto {

    /**
     * office id
     */
    private Long officeId;

    /**
     * user first name
     */
    private String firstName;

    /**
     * user second name
     */
    private String secondName;

    /**
     * user middle name
     */
    private String middleName;

    /**
     * user position
     */
    private String position;

    /**
     * user document code
     */
    private String docCode;

    /**
     * user citizenship code
     */
    private String citizenshipCode;

    public FilterUserDto() {
    }

    public FilterUserDto(Long officeId, String firstName, String secondName, String middleName, String position,
                         String docCode, String citizenshipCode) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.docCode = docCode;
        this.citizenshipCode = citizenshipCode;
    }

    @ApiModelProperty(required = true)
    @NotNull(message = "Office id must not be null")
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

    @Pattern(regexp = "^[0-9]{2}$", message = "Document code must be number with length 2")
    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    @Pattern(regexp = "^[0-9]{3}$", message = "Citizenship code code must be number with length 3")
    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
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
        sb.append(", docCode='").append(docCode).append('\'');
        sb.append(", citizenshipCode='").append(citizenshipCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
