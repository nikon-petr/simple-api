package edu.nikon.simpleapi.api.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

/**
 * DTO class used to represent detailed organization data
 */
@ApiModel("Organization")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class OrganizationDetailedDto {

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
     * organization address
     */
    private String address;

    /**
     * organization phone
     */
    private String phone;

    /**
     * organization activity state
     */
    private Boolean active;

    public OrganizationDetailedDto() {
    }

    public OrganizationDetailedDto(
            long id,
            String name,
            String fullName,
            String inn,
            String kpp,
            String address,
            String phone,
            Boolean active) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty(value = "isActive")
    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrganizationDetailedDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", inn='").append(inn).append('\'');
        sb.append(", kpp='").append(kpp).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
