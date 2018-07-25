package edu.nikon.simpleapi.api.user.domain;

import edu.nikon.simpleapi.api.catalog.domain.Country;
import edu.nikon.simpleapi.api.catalog.domain.DocumentType;
import edu.nikon.simpleapi.api.common.embeddable.Name;
import edu.nikon.simpleapi.api.office.domain.Office;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;

/**
 * User entity
 */
@Entity
@Table(
        name = "`user`",
        indexes = {
                @Index(name = "idx_user_citizenship_code", columnList = "citizenship_code"),
                @Index(name = "idx_user_filter", columnList = "first_name,second_name,middle_name,position,citizenship_code")
        }
)
public class User {

    /**
     * user id
     */
    private Long id;

    /**
     * hibernate version field
     */
    private Long version;

    /**
     * user name
     */
    private Name name;

    /**
     * user position
     */
    private String position;

    /**
     * user phone number
     */
    private String phone;

    /**
     * user's citizenship country code (readonly)
     */
    private String citizenshipCode;

    /**
     * user identified state
     */
    private Boolean identified;

    /**
     * office id related to user (readonly)
     */
    private Long officeId;

    /**
     * user document data (number, date and etc)
     */
    private DocumentData documentData;

    /**
     * user citizenship country joined by citizenshipCode
     */
    private Country citizenshipCountry;

    /**
     * office related to user joined by officeId
     */
    private Office office;

    public User() {
    }

    @Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    protected Long getVersion() {
        return version;
    }

    protected void setVersion(Long version) {
        this.version = version;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Column(length = 35, nullable = false)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "citizenship_code", length = 3, insertable = false, updatable = false)
    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    @Column
    public Boolean getIdentified() {
        return identified;
    }

    public void setIdentified(Boolean identified) {
        this.identified = identified;
    }

    @Column(name = "office_id", insertable = false, updatable = false)
    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    public DocumentData getDocumentData() {
        return documentData;
    }

    public void setDocumentData(DocumentData documentData) {
        this.documentData = documentData;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_code")
    public Country getCitizenshipCountry() {
        return citizenshipCountry;
    }

    public void setCitizenshipCountry(Country country) {
        this.citizenshipCountry = country;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    /**
     * Manage bidirectional documentData relation. Attach document data or set to null.
     *
     * @param documentData document data entity or null
     */
    public void attachDocumentData(DocumentData documentData) {
        if (documentData != null) {
            documentData.setUser(this);
        }
        this.documentData = documentData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(position, user.position) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(citizenshipCode, user.citizenshipCode) &&
                Objects.equals(identified, user.identified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, phone, citizenshipCode, identified);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", name=").append(name);
        sb.append(", position='").append(position).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", citizenshipCode='").append(citizenshipCode).append('\'');
        sb.append(", identified=").append(identified);
        sb.append(", documentData={...}").append(documentData);
        sb.append(", citizenshipCountry={...}").append(citizenshipCountry);
        sb.append('}');
        return sb.toString();
    }
}
