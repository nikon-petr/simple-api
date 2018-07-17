package edu.nikon.simpleapi.api.catalog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "country")
public class Country {

    /**
     * country ISO code
     */
    private String code;

    /**
     * country name in russian
     */
    private String name;

    /**
     * country name in english
     */
    private String enName;

    public Country() {
    }

    @Id
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = Objects.requireNonNull(code);
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Column(nullable = false)
    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = Objects.requireNonNull(enName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return Objects.equals(code, country.code) &&
                Objects.equals(name, country.name) &&
                Objects.equals(enName, country.enName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, enName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", en_name='").append(enName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
