package com.echo.jpa.domain;

import com.echo.data.domain.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>base entity ,can be use with oracle;
 *
 * @author Galen
 * @since 2016/2/19
 */
@MappedSuperclass
public abstract class BaseJpaOracleEntity<ID extends Serializable> extends Auditable<ID> {

    protected ID id;

    protected String createBy;

    protected Date creationDate;

    protected String lastModifiedBy;

    protected Date lastModificationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Column(name = "created_by")
    public String getCreatedBy() {
        return createBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "last_modified_by")
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modification_date")
    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }
}
