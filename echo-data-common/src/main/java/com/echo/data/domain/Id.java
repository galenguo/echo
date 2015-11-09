package com.echo.data.domain;

import java.io.Serializable;

/**
 * Created by Galen on 2015/11/4.
 */
public interface Id<ID> extends Serializable {

    public ID getId();

    public void setId(final ID id);
}
