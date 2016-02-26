package com.echo.core.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * base controller
 *
 * @author Galen
 * @since 2016/2/3
 */
public abstract class BaseController {

    protected final Logger logger = LogManager.getLogger(this.getClass());
}
