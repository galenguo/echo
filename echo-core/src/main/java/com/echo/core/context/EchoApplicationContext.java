package com.echo.core.context;

import com.echo.core.utils.AssertUtils;
import com.echo.core.utils.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.util.Locale;

/**
 * <P>echo application context
 * <p>include spring bean, messages, system properties, and so on
 *
 * @author Galen
 * @since 2016/01/27
 */
public class EchoApplicationContext {

    protected static final Logger logger = LogManager.getLogger(EchoApplicationContext.class);

    /**
     * spring ioc container
     */
    protected static ApplicationContext applicationContext = null;

    /**
     * web container context
     */
    protected static ServletContext servletContext = null;

    /**
     * <p>get spring application context
     * <p>but it may return null when ioc container was initializing
     * @return
     */
    public static synchronized ApplicationContext getApplicationContext() {
        if (applicationContext == null && servletContext != null) {
            applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        }
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        EchoApplicationContext.applicationContext = applicationContext;
    }

    public static ServletContext getServletContext() {
        return EchoApplicationContext.servletContext;
    }

    public static void setServletContext(ServletContext servletContext) {
        EchoApplicationContext.servletContext = servletContext;
    }

    public static <T> T getBean(Class<T> clz) {
        try {
            return getApplicationContext().getBean(clz);
        } catch (BeansException ex) {
            throw ex;
        }
    }

    public static <T> T getBean(String name) {
        try {
            return (T) getApplicationContext().getBean(name);
        } catch (BeansException ex) {
            throw ex;
        }
    }

    /**
     * get bean from ioc container
     * @param name
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clz) {
        try {
            return (T) getApplicationContext().getBean(name, clz);
        } catch (BeansException ex) {
            throw ex;
        }
    }

    public static String getMessage(String code) {
        return getMessage(code, (Object[]) null);
    }

    public static String getMessage(String code, String defaultMessage) {
        return getMessage(code, (Object[]) null, defaultMessage);
    }

    public static String getMessage(String code, Locale locale) {
        return getMessage(code, null, locale);
    }

    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, code);
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        return getMessage(code, args, code, locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = getCurrentUserLocale();
        if (null == locale) {
            locale = Locale.getDefault();
        }
        return getMessage(code, args, defaultMessage, locale);
    }

    /**
     * get i18n messages string
     * @param code
     * @param args
     * @param defaultMessage
     * @param locale
     * @return
     */
    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        try {
            ApplicationContext context = getApplicationContext();
            return context != null ? context.getMessage(code, args, defaultMessage, locale) : code;
        } catch (NoSuchMessageException ex) {
            return code;
        }
    }

    /**
     * get current user locale
     *
     * @return
     */
    public static Locale getCurrentUserLocale() {
        //return RequestContextUtils.getLocale(getHttpRequest());
        return null;
    }

    /**
     * get request, but it just can use in request thread.
     *
     * @return
     */
    public static HttpServletRequest getHttpRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        AssertUtils.notNull(attrs);
        return attrs.getRequest();
    }

    /**
     * get sesion, but it juse can use in request thread.
     *
     * @return
     */
    public static HttpSession getHttpSession() {
        HttpServletRequest request = getHttpRequest();
        AssertUtils.notNull(request);
        return request.getSession(false);
    }

    /**
     * get application deploy path
     *
     * @return
     */
    public static String getApplicationPath() {
        ServletContext context = getServletContext();
        String serverInfo = context.getServerInfo().toUpperCase();
        String dirName = context.getRealPath("/");

        if (serverInfo.contains("TOMCAT")) {
            logger.debug("It is running in tomcat");

        } else if (serverInfo.contains("JETTY")) {
            logger.debug("It is running in jetty");

        } else if (serverInfo.contains("JBOSS")) {
            logger.debug("It is running in jboss");

        } else if (serverInfo.contains("WEBLOGIC")) {
            logger.debug("It is running in weblogic");
            try {
                dirName = context.getResource("/").getFile();
                logger.debug("context.getResource:" + dirName);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }

        }
        return FileUtils.addSeparatorIfNec(dirName);
    }
}
