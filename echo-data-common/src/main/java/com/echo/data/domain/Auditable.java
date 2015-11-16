package com.echo.data.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * Created by Galen on 2015/11/4.
 */
public abstract class Auditable<ID> implements Id<ID> {

    public abstract String getCreatedBy();

    public abstract void setCreatedBy(final String createdBy);

    public abstract Date getCreationTime();

    public abstract void setCreationTime(final Date creationTime);

    public abstract String getLastModifiedBy();

    public abstract void setLastModifiedBy(final String lastModifiedBy);

    public abstract Date getLastModificationTime();

    public abstract void setLastModificationTime(final Date lastModificationTime);

    public boolean isNew() {
        return null == getId();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }


    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (!getClass().equals(obj.getClass())) {
            return false;
        } else {
            return null == this.getId() ? false :
                    this.getId().equals(((com.echo.data.domain.Id)obj).getId());
        }
    }

}
