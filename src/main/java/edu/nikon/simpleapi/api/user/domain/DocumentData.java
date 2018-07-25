package edu.nikon.simpleapi.api.user.domain;

import edu.nikon.simpleapi.api.catalog.domain.DocumentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import java.util.Date;
import java.util.Objects;

/**
 * Document data entity
 */
@Entity
@Table(
        name = "document_data",
        uniqueConstraints = @UniqueConstraint(name = "unique_document_data_number", columnNames = "number"),
        indexes = @Index(name = "idx_document_data_document_type_code", columnList = "document_type_code")
)
public class DocumentData {

    /**
     * user id (foreign key and primary key)
     */
    private Long userId;

    /**
     * hibernate version field
     */
    private Long version;

    /**
     * document number
     */
    private String number;

    /**
     * document date
     */
    private Date date;

    /**
     * document code (readonly)
     */
    private String documentCode;

    /**
     * document type joined by documentCode
     */
    private DocumentType documentType;

    /**
     * user object
     */
    private User user;

    public DocumentData() {
    }

    public DocumentData(String number, Date date) {
        this.number = number;
        this.date = date;
    }

    @Id
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Column(nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "document_type_code", length = 2, insertable = false, updatable = false)
    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_code")
    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentData)) return false;
        DocumentData that = (DocumentData) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(date, that.date) &&
                Objects.equals(documentCode, that.documentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, date, documentCode);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DocumentData{");
        sb.append("userId=").append(userId);
        sb.append(", version=").append(version);
        sb.append(", number='").append(number).append('\'');
        sb.append(", date=").append(date);
        sb.append(", documentCode='").append(documentCode).append('\'');
        sb.append(", documentType={...}");
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
