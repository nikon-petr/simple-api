package edu.nikon.simpleapi.api.organization.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class OrganizationFilterDto implements Serializable {
    private String name;
    private String inn;
    private Bool active;

    @NotNull(message = "name.invalid")
    @NotBlank(message = "name.invalid")
    @Length(max = 255, message = "name.invalid")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Bool isActive() {
        return active;
    }

    public void setActive(Bool active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("OrganizationFilterDto[name=%s, inn=%s, active=%s]", name, inn, active);
    }
}
