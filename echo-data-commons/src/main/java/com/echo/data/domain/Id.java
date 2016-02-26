package com.echo.data.domain;

import java.io.Serializable;

/**
 * entity interface
 *
 * @author Galen
 * @since 2015/11/4.
 */
public interface Id<ID extends Serializable> extends Serializable {

    public ID getId();

    public void setId(final ID id);
}
