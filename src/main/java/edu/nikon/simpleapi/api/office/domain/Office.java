package edu.nikon.simpleapi.api.office.domain;

import edu.nikon.simpleapi.api.common.embeddable.Contact;
import edu.nikon.simpleapi.api.organization.domain.Organization;
import edu.nikon.simpleapi.api.user.domain.User;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    /**
     * users related to office
     */
    private Set<User> users;

    public Office() {
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

    @OneToMany(
            mappedBy = "office",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public Set<User> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Manage bidirectional user relation. Add user to office
     *
     * @param user user entity
     */
    public void addUser(User user) {
        getUsers().add(user);
        user.setOffice(this);
    }

    /**
     * Manage bidirectional user relation. Remove user from office
     *
     * @param user user entity
     */
    public void removeUser(User user) {
        getUsers().remove(user);
        user.setOffice(null);
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
        sb.append(", users=[...]");
        sb.append('}');
        return sb.toString();
    }
}
