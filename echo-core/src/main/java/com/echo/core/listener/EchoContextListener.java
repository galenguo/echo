package com.echo.core.listener;

import com.echo.core.Constants;
import com.echo.core.config.EchoConfiguration;
import com.echo.core.context.EchoApplicationContext;
import com.echo.core.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.jar.Manifest;

/**
 * <p>framework application context Listener
 * <P>initialize application context
 *
 * @author Galen
 * @since 2015/12/6.
 */
public class EchoContextListener implements ServletContextListener {

    protected Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // resource host root
        String hostRoot = EchoConfiguration
                .getProperty(EchoConfiguration.HOST_ROOT);
        if (StringUtils.isBlank(hostRoot)) {
            hostRoot = sce.getServletContext().getContextPath();
            EchoConfiguration.putProperty(EchoConfiguration.HOST_ROOT, hostRoot);
        }

        String appVersion = EchoConfiguration.getProperty(EchoConfiguration.APP_VERSION);
        if (StringUtils.isEmpty(appVersion)) {
            //get app version from manifiest file
            try {
                Manifest metainfo = new Manifest();
                metainfo.read(sce.getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF"));
                String manifestVersion = metainfo.getMainAttributes().getValue("Manifest-Version");
                if (StringUtils.isNotEmpty(manifestVersion)) {
                    appVersion = manifestVersion;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            EchoConfiguration.putProperty(EchoConfiguration.APP_VERSION, appVersion);
        }

        //set context path
        String contextPath =  sce.getServletContext().getContextPath();
        sce.getServletContext().setAttribute(Constants.CONFIG_CONTEXT, contextPath);
        logger.debug(Constants.CONFIG_CONTEXT + ": " + contextPath);

        //set app version
        sce.getServletContext().setAttribute(Constants.APP_VERSION, appVersion);
        logger.debug(Constants.APP_VERSION + ": " + appVersion);

        //set host
        sce.getServletContext().setAttribute(Constants.HOST, hostRoot);
        logger.debug(Constants.HOST + ": " + hostRoot);

        //set resource path
        String resourcePath = hostRoot + "/resources/" + appVersion;
        sce.getServletContext().setAttribute(Constants.RESOURCE_PATH, resourcePath);
        logger.debug(Constants.RESOURCE_PATH + ": " + resourcePath);

        //set config context
        sce.getServletContext().setAttribute(Constants.CONFIG_CONTEXT, EchoConfiguration.getVisableProperties());

        initEchoApplicationContext(sce.getServletContext());

        if (logger.isInfoEnabled()) {
            logger.debug("echoContextListener context initialized!");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (logger.isInfoEnabled()) {
            logger.info("echoContextListener context destoryed!");
        }
    }

    private void initEchoApplicationContext(ServletContext servletContext) {
        //set servlet context
        EchoApplicationContext.setServletContext(servletContext);

        //set environment properties and set spring ioc context
        ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext)WebApplicationContextUtils
                .getRequiredWebApplicationContext(servletContext);
        ConfigurableEnvironment env = applicationContext.getEnvironment();
        env.getPropertySources().addFirst(new MapPropertySource(Constants.APP_EVN, EchoConfiguration.getProperties()));
        EchoApplicationContext.setApplicationContext(applicationContext);
    }
}
