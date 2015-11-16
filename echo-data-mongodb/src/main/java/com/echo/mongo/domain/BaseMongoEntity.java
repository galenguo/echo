package com.echo.mongo.domain;

import com.echo.data.domain.Auditable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Galen on 2015/11/15.
 */
public class BaseMongoEntity<ID extends Serializable> extends Auditable<ID> {

    @Id
    private ID id;

    @Field
    private String createdBy;

    @Field
    private Date creationTime;

    @Field
    private String lastModifiedBy;

    @Field
    private Date lastModificationTime;

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

}
