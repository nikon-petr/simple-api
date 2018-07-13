package edu.nikon.simpleapi.api.organization.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import java.util.Objects;

@Entity
@Table(
        name = "organization",
        uniqueConstraints = @UniqueConstraint(name = "unique_inn_kpp", columnNames = {"inn", "kpp"})
)
public class Organization {

    private long id;
    private long version;
    private String name;
    private String fullName;
    private String inn;
    private String kpp;
    private String address;
    private String phone;
    private Boolean isActive;

    public Organization() {
    }

    public Organization(String name, String fullName, String inn, String kpp, String address, String phone,
                        Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Organization(long id, String name, String fullName, String inn, String kpp, String address,
                        String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Column(nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "full_name", nullable = false, length = 75)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(nullable = false, length = 12)
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Column(nullable = false, length = 9)
    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    @Column(nullable = false, length = 175)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
                Objects.equals(address, that.address) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fullName, inn, kpp, address, phone, isActive);
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
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
