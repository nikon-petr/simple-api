package edu.nikon.simpleapi.api.organization.domain;

import edu.nikon.simpleapi.api.common.embeddable.Contact;
import edu.nikon.simpleapi.api.office.domain.Office;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Organization entity
 */
@Entity
@Table(
        name = "organization",
        uniqueConstraints = @UniqueConstraint(name = "unique_inn_kpp", columnNames = {"inn", "kpp"}),
        indexes = @Index(name = "idx_name_inn_active", columnList = "name,inn,active")
)
public class Organization {

    /**
     * organization id
     */
    private Long id;

    /**
     * hibernate version field
     */
    private Long version;

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
     * organization contacts
     */
    private Contact contact;


    /**
     * organization activity state
     */
    private Boolean active;

    private Set<Office> offices;

    public Organization() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name can not be null");
    }

    @Column(name = "full_name", nullable = false, length = 75)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = Objects.requireNonNull(fullName, "Full name can not be null");
    }

    @Column(nullable = false, length = 12)
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = Objects.requireNonNull(inn, "INN can not be null");
    }

    @Column(nullable = false, length = 9)
    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = Objects.requireNonNull(kpp, "KPP can not be null");
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = Objects.requireNonNull(contact, "Contact can not be null");
    }

    @Column
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @OneToMany(
            mappedBy = "organization",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public Set<Office> getOffices() {
        if (offices == null) {
            offices = new HashSet<>();
        }
        return offices;
    }

    protected void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    /**
     * Manage bidirectional office relation. Add office to organization
     *
     * @param office office entity
     */
    public void addOffice(Office office) {
        getOffices().add(office);
        office.setOrganization(this);
    }

    /**
     * Manage bidirectional office relation. Remove office from organization
     *
     * @param office office entity
     */
    public void removeOffice(Office office) {
        getOffices().remove(office);
        office.setOrganization(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(inn, that.inn) &&
                Objects.equals(kpp, that.kpp) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fullName, inn, kpp, contact, active);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Organization{");
        sb.append("id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", name='").append(name).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", inn='").append(inn).append('\'');
        sb.append(", kpp='").append(kpp).append('\'');
        sb.append(", contact=").append(contact);
        sb.append(", active=").append(active);
        sb.append(", offices=[...Office{...}]");
        sb.append('}');
        return sb.toString();
    }
}
