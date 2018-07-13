package edu.nikon.simpleapi.api.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel("UpdateUser")
public class UpdateUserDto {

    private Long id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private String phone;
    private String docName;
    private String docNumber;
    private Date docDate;
    private Long citizenshipCode;
    private Boolean identified;

    public UpdateUserDto() {
    }

    public UpdateUserDto(Long id, String firstName, String secondName, String middleName, String position, String phone,
                         String docName, String docNumber, Date docDate, Long citizenshipCode, Boolean identified) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipCode = citizenshipCode;
        this.identified = identified;
    }

    @ApiModelProperty(required = true)
    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
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

    @NotBlank
    @Size(max = 40, message = "Position length should be less than 40 characters")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Pattern(regexp = "^[0-9]+$", message = "Phone number should contains only digit characters")
    @Size(max = 30, message = "Phone length should be less than 30 characters")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Size(max = 75, message = "Doc name length should be less than 75 characters")
    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    @Pattern(regexp = "^[0-9]+$", message = "Doc number should contains only digit characters")
    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Long citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    @JsonProperty(value = "isIdentified")
    public Boolean getIdentified() {
        return identified;
    }

    public void setIdentified(Boolean identified) {
        this.identified = identified;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateUserDto{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", position='").append(position).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", docName='").append(docName).append('\'');
        sb.append(", docNumber='").append(docNumber).append('\'');
        sb.append(", docDate=").append(docDate);
        sb.append(", citizenshipCode=").append(citizenshipCode);
        sb.append(", identified='").append(identified).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
