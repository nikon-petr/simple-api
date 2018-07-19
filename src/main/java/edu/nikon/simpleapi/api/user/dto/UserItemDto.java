package edu.nikon.simpleapi.api.user.dto;

import io.swagger.annotations.ApiModel;

/**
 * DTO class used to represent short organization data in list
 */
@ApiModel("UserItem")
public class UserItemDto {

    /**
     * office id
     */
    private long officeId;

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
     * user's document code
     */
    private String docCode;

    /**
     * user citizenship country code
     */
    private String citizenshipCode;

    public UserItemDto(long officeId, String firstName, String secondName, String middleName, String position,
                       String docCode, String citizenshipCode) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.docCode = docCode;
        this.citizenshipCode = citizenshipCode;
    }

    public long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(long officeId) {
        this.officeId = officeId;
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

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserItemDto{");
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
