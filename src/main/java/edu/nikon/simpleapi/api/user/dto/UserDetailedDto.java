package edu.nikon.simpleapi.api.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * DTO class used to represent detailed user data
 */
@ApiModel("UserDetailed")
public class UserDetailedDto {

    /**
     * user id
     */
    private long id;

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
     * user phone
     */
    private String phone;

    /**
     * user's document name
     */
    private String docName;

    /**
     * user's document number
     */
    private String docNumber;

    /**
     * user's document date
     */
    private Date docDate;

    /**
     * user citizenship country code
     */
    private String citizenshipCode;

    /**
     * user citizenship country name
     */
    private String citizenshipName;

    /**
     * user identity state
     */
    private Boolean identified;

    public UserDetailedDto() {
    }

    public UserDetailedDto(long id, String firstName, String secondName, String middleName, String position,
                           String phone, String docName, String docNumber, Date docDate, String citizenshipCode,
                           String citizenshipName, Boolean identified) {
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
        this.citizenshipName = citizenshipName;
        this.identified = identified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

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

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
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
        final StringBuilder sb = new StringBuilder("UserDetailedDto{");
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
        sb.append(", citizenshipName='").append(citizenshipName).append('\'');
        sb.append(", identified='").append(identified).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
