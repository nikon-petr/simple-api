package edu.nikon.simpleapi.api.organization.dto;

import java.io.Serializable;

public class OrganizationItemDto implements Serializable {
    private long id;
    private String name;
    private boolean active;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("OrganizationItem [id=%s, name=%s, active=%s]", id, name, active);
    }
}
