package edu.nikon.simpleapi.api.common.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Name embeddable object
 */
@Embeddable
public class Name {

    /**
     * first name
     */
    private String first;

    /**
     * second name
     */
    private String second;

    /**
     * middle name
     */
    private String middle;

    public Name() {
    }

    public Name(String first, String second, String middle) {
        setFirst(first);
        this.second = second;
        this.middle = middle;
    }

    @Column(name = "first_name",length = 35, nullable = false)
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = Objects.requireNonNull(first);
    }

    @Column(name = "second_name",length = 35)
    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    @Column(name = "middle_name",length = 35)
    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name = (Name) o;
        return Objects.equals(first, name.first) &&
                Objects.equals(second, name.second) &&
                Objects.equals(middle, name.middle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, middle);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Name{");
        sb.append("first='").append(first).append('\'');
        sb.append(", second='").append(second).append('\'');
        sb.append(", middle='").append(middle).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
