package com.echo.data.domain;

import java.util.Date;

/**
 * Created by Galen on 2015/11/4.
 */
public interface Auditable<ID> extends Id<ID> {

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public Date getCreationTime();

    public void setCreateTime(Date createTime);

    public String getLastModifiedBy();

    public void setLastModifiedBy(String lastModifiedBy);

    public Date getLastModificationTime();

    public void setLastModificationTime(Date lastModificationTime);
}
