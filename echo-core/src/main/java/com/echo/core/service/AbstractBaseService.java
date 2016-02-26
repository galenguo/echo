package com.echo.core.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * abstract base service
 *
 * @author Galen
 * @since 2016/2/3
 */
public abstract class AbstractBaseService implements BaseService {

    protected final Logger logger = LogManager.getLogger(this.getClass());
}
