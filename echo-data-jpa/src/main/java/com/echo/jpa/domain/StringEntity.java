package com.echo.jpa.domain;

import com.echo.data.domain.Auditable;

import java.util.Date;

/**
 * Created by Galen on 2015/11/5.
 */
public abstract class StringEntity<String> implements Auditable<String> {

    private String id;

    private String createdBy;

    private Date creationTime;

    private String lastModifiedBy;

    private Date lastMoodificationTime;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String string) {
        this.id = id;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreationTime() {
        return this.creationTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.creationTime = createTime;
    }

    @Override
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public Date getLastModificationTime() {
        return this.lastMoodificationTime;
    }

    @Override
    public void setLastModificationTime(Date lastModificationTime) {
        this.lastMoodificationTime = lastModificationTime;
    }
}
