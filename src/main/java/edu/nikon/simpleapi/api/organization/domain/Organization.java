package edu.nikon.simpleapi.api.organization.domain;

import edu.nikon.simpleapi.api.common.embeddable.Contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import java.util.Objects;

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

    protected Organization() {
    }

    private Organization(long id, String name, String fullName, String inn, String kpp, Contact contact, Boolean active) {
        this.id = id;
        setName(name);
        setFullName(fullName);
        setInn(inn);
        setKpp(kpp);
        setContact(contact);
        this.active = active;
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
        this.fullName =  Objects.requireNonNull(fullName, "Full name can not be null");
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
        sb.append('}');
        return sb.toString();
    }

    /**
     * Organization entity builder
     */
    public static class Builder {

        /**
         * organization id
         */
        private long id;

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

        /**
         * Build organization entity with setted values
         *
         * @return organization entity
         */
        public Organization build() {
            return new Organization(id, name, fullName, inn, kpp, contact, active);
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setInn(String inn) {
            this.inn = inn;
            return this;
        }

        public Builder setKpp(String kpp) {
            this.kpp = kpp;
            return this;
        }

        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder setActive(Boolean active) {
            this.active = active;
            return this;
        }
    }
}
