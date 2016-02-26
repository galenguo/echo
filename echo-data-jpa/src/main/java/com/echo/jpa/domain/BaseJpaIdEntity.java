package com.echo.jpa.domain;

import com.echo.data.domain.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>base entity just with id field;
 * @author Galen
 * @since 2016/2/19
 */
@MappedSuperclass
public abstract class BaseJpaIdEntity<ID extends Serializable> extends Auditable<ID> {

    protected ID id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Transient
    public String getCreatedBy() {
        return null;
    }

    public void setCreatedBy(String createdBy) {
        throw new NoSuchMethodError();
    }

    @Transient
    public Date getCreationDate() {
        throw new NoSuchMethodError();
    }

    public void setCreationDate(Date creationDate) {
        throw new NoSuchMethodError();
    }

    @Transient
    public String getLastModifiedBy() {
        throw new NoSuchMethodError();
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        throw new NoSuchMethodError();
    }

    @Transient
    public Date getLastModificationDate() {
        throw new NoSuchMethodError();
    }

    public void setLastModificationDate(Date lastModificationDate) {
        throw new NoSuchMethodError();
    }
}
