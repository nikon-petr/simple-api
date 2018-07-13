package edu.nikon.simpleapi.api.office.domain;

import java.util.concurrent.atomic.AtomicLong;

public class Office {

    private static AtomicLong idGenerator = new AtomicLong(1);

    private long id;
    private String name;
    private String address;
    private String phone;
    private Boolean active;
    private Long orgId;

    public Office() {
    }

    public Office(String name, String address, String phone, Boolean active, Long orgId) {
        this.id = idGenerator.getAndIncrement();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.active = active;
        this.orgId = orgId;
    }

    public Office(long id, String name, String address, String phone, Boolean active, Long orgId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.active = active;
        this.orgId = orgId;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
