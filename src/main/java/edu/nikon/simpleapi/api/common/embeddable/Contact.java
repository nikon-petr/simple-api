package edu.nikon.simpleapi.api.common.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Contact object
 */
@Embeddable
public class Contact {

    private String address;
    private String phone;

    protected Contact() {
    }

    public Contact(String address, String phone) {
        setAddress(address);
        this.phone = phone;
    }

    @Column(name = "address", length = 175, nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = Objects.requireNonNull(address);
    }

    @Column(name = "phone", length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(address, contact.address) &&
                Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, phone);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contact{");
        sb.append("address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
