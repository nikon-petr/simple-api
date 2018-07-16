package edu.nikon.simpleapi.api.office.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO class used to represent detailed office data
 */
public class OfficeDetailedDto {

    /**
     * office id
     */
    private long id;


    /**
     * office name
     */
    private String name;


    /**
     * office address
     */
    private String address;


    /**
     * office phone
     */
    private String phone;


    /**
     * office activity state
     */
    private Boolean active;

    public OfficeDetailedDto() {
    }

    public OfficeDetailedDto(long id, String name, String address, String phone, Boolean active) {
        this.id = id;
        this.name = name;
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
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OfficeDetailedDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
