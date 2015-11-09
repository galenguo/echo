package com.echo.data.domain;

import java.util.Date;

/**
 * Created by Galen on 2015/11/4.
 */
public interface Auditable<ID> extends Id<ID> {

    public String getCreatedBy();

    public void setCreatedBy(final String createdBy);

    public Date getCreationTime();

    public void setCreationTime(final Date creationTime);

    public String getLastModifiedBy();

    public void setLastModifiedBy(final String lastModifiedBy);

    public Date getLastModificationTime();

    public void setLastModificationTime(final Date lastModificationTime);

    public boolean isNew();

}
