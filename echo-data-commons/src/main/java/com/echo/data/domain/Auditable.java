package com.echo.data.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * entity abstract class
 *
 * @author Galen
 * @since 2015/11/4.
 */
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
public abstract class Auditable<ID extends Serializable> implements Id<ID> {

    public abstract String getCreatedBy();

    public abstract void setCreatedBy(final String createdBy);

    public abstract Date getCreationDate();

    public abstract void setCreationDate(final Date creationDate);

    public abstract String getLastModifiedBy();

    public abstract void setLastModifiedBy(final String lastModifiedBy);

    public abstract Date getLastModificationDate();

    public abstract void setLastModificationDate(final Date lastModificationDate);

    @Transient
    public boolean isNew() {
        return null == getId();
    }

    @Transient
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @Transient
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

    @Transient
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
