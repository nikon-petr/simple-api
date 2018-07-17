package edu.nikon.simpleapi.api.catalog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Document type entity
 */
@Entity
@Table(name = "document_type")
public class DocumentType {

    /**
     * document code
     */
    private String code;

    /**
     * document name
     */
    private String name;

    public DocumentType() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentType)) return false;
        DocumentType documentType = (DocumentType) o;
        return Objects.equals(code, documentType.code) &&
                Objects.equals(name, documentType.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DocumentType{");
        sb.append("code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
