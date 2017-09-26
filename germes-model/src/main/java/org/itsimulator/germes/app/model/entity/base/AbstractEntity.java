package org.itsimulator.germes.app.model.entity.base;

import java.time.LocalDateTime;

import org.itsimulator.germes.app.model.entity.person.Account;

import javax.persistence.*;

/**
 * Base class for all business entities
 *
 * @author admin
 */
@MappedSuperclass
public abstract class AbstractEntity {
    /**
     * Unique entity identifier
     */
    private int id;

    /**
     * Timestamp of entity creation
     */
    private LocalDateTime createdAt;

    /**
     * Timestamp of entity last modification
     */
    private LocalDateTime modifiedAt;

    /**
     * Person who created specific entity
     */
    private Account createdBy;

    /**
     * Last person who modified entity
     */
    private Account modifiedBy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "MODIFIED_AT", insertable = false)
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = "CREATED_BY", updatable = false)
    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = "MODIFIED_BY", insertable = false)
    public Account getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Account modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @PrePersist
    public void prePersist() {
        if (getId() == 0) {
            setCreatedAt(LocalDateTime.now());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        if (id != that.id) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (modifiedAt != null ? !modifiedAt.equals(that.modifiedAt) : that.modifiedAt != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        return modifiedBy != null ? modifiedBy.equals(that.modifiedBy) : that.modifiedBy == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (modifiedAt != null ? modifiedAt.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
    }
}
