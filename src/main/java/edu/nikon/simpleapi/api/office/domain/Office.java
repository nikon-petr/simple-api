package edu.nikon.simpleapi.api.office.domain;

import edu.nikon.simpleapi.api.common.embeddable.Contact;
import edu.nikon.simpleapi.api.organization.domain.Organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;

/**
 * Office entity
 */
@Entity
@Table(
        name = "office",
        indexes = @Index(name = "idx_organization_id_name_phone_active", columnList = "organization_id,name,phone,active")
)
public class Office {

    /**
     * office id
     */
    private Long id;

    /**
     * hibernate version field
     */
    private Long version;

    /**
     * office name
     */
    private String name;


    /**
     * office contacts
     */
    private Contact contact;


    /**
     * Office activity state
     */
    private Boolean active;


    /**
     * organization owning the office
     */
    private Organization organization;

    protected Office() {
    }

    private Office(long id, String name, Contact contact, Boolean active, Organization organization) {
        this.id = id;
        setName(name);
        setContact(contact);
        this.active = active;
        this.organization = organization;
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

    @Column(length = 20, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = Objects.requireNonNull(contact);
    }

    @Column
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Office)) return false;
        Office office = (Office) o;
        return Objects.equals(name, office.name) &&
                Objects.equals(active, office.active) &&
                Objects.equals(contact, office.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, active, contact);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Office{");
        sb.append("id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", name='").append(name).append('\'');
        sb.append(", contact=").append(contact);
        sb.append(", active=").append(active);
        sb.append(", organization={...}");
        sb.append('}');
        return sb.toString();
    }

    /**
     * Office entity builder
     */
    public static class Builder {

        /**
         * office id
         */
        private long id;


        /**
         * office name
         */
        private String name;


        /**
         * office contacts
         */
        private Contact contact;


        /**
         * office activity state
         */
        private Boolean active;


        /**
         * organization owning the office
         */
        private Organization organization;

        /**
         * Build office entity with setted values
         *
         * @return
         */
        public Office build() {
            return new Office(id, name, contact, active, organization);
        }

        public long getId() {
            return id;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Boolean getActive() {
            return active;
        }

        public Builder setActive(Boolean active) {
            this.active = active;
            return this;
        }

        public Contact getContact() {
            return contact;
        }

        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Organization getOrganization() {
            return organization;
        }

        public Builder setOrganization(Organization organization) {
            this.organization = organization;
            return this;
        }
    }
}
