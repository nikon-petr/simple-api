package edu.nikon.simpleapi.api.user.domain;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class User {

    private static AtomicLong idGenerator = new AtomicLong(1);

    private long id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private String phone;
    private Long docCode;
    private String docName;
    private String docNumber;
    private Date docDate;
    private Long citizenshipCode;
    private Boolean identified;
    private Long officeId;

    public User() {
    }

    public User(
            long id,
            String firstName,
            String secondName,
            String middleName,
            String position,
            String phone,
            Long docCode,
            String docName,
            String docNumber,
            Date docDate,
            Long citizenshipCode,
            Boolean identified,
            Long officeId) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.docCode = docCode;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipCode = citizenshipCode;
        this.identified = identified;
        this.officeId = officeId;
    }

    public User(
            String firstName,
            String secondName,
            String middleName,
            String position,
            String phone,
            Long docCode,
            String docName,
            String docNumber,
            Date docDate,
            Long citizenshipCode,
            Boolean identified,
            Long officeId) {
        this.id = idGenerator.getAndIncrement();
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.docCode = docCode;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipCode = citizenshipCode;
        this.identified = identified;
        this.officeId = officeId;
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

    public Long getDocCode() {
        return docCode;
    }

    public void setDocCode(Long docCode) {
        this.docCode = docCode;
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

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Long citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIdentified() {
        return identified;
    }

    public void setIdentified(Boolean identified) {
        this.identified = identified;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", position='").append(position).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", docCode=").append(docCode);
        sb.append(", docName='").append(docName).append('\'');
        sb.append(", docNumber='").append(docNumber).append('\'');
        sb.append(", docDate=").append(docDate);
        sb.append(", citizenshipCode=").append(citizenshipCode);
        sb.append(", identified='").append(identified).append('\'');
        sb.append(", officeId=").append(officeId);
        sb.append('}');
        return sb.toString();
    }
}
