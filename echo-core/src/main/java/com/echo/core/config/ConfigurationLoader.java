package com.echo.core.config;

import org.springframework.core.Ordered;

/**
 * @author Galen
 * @since 2016/2/19
 */
public interface ConfigurationLoader extends Ordered {

    public void setProperties() throws Exception;
}
