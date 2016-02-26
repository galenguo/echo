package com.echo.core.config;

import com.echo.core.Constants;
import com.echo.core.exception.EchoException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * echo configuration can get properties from which defined in application xml.
 *
 *
 * @author Galen on
 * @since 2015/12/6.
 */
public class EchoConfiguration {

    public static final String CONFIG_PATH = "echo_config_path";

    public static final String HOST_ROOT = "host.root";

    public static final String APP_VERSION = "app.version";

    public static final String JPA_SHOWSQL = "jpa.showSql";

    public static final String JPA_GENERATEDDL = "jpa.generateDdl";

    public static final String LOGIN_CAPTCHA_ENABLED = "login.captcha.enable";

    public static final String LOG_LEVEL = "log.level";

    public static final String PATTERN_TIME = "pattern.time";

    public static final String PATTERN_DATE = "pattern.date";

    public static final String PATTERN_DATETIME = "pattern.dateTime";

    public static final String PATTERN_DATETIMESTAMP = "pattern,dateTimeStamp";

    private static Map<String, Object> properties = new HashMap<String, Object>();

    private static String jpaShowSql;

    private static String jpaGenerateddl;

    private static String logLevel;

    private static String patternTime;

    private static String patternDate;

    private static String patternDateTime;

    private static String patternDateTimeStamp;

    private static String configPath;

    public static <T> T getProperty(String key) {
        return (T) properties.get(key);
    }

    public static Map<String, Object> getProperties() {
        return EchoConfiguration.properties;
    }

    /**
     * get visable properties with view
     *
     * @return
     */
    public static Map<String, Object> getVisableProperties() {
        Map<String, Object> visableProperties = new HashMap<String, Object>();
        String key = null;
        Iterator<Map.Entry<String, Object>> iterator = EchoConfiguration.properties.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            key = entry.getKey();
            if (key.contains(Constants.USERNAME) || key.contains(Constants.PASSWORD)) {
                continue;
            }
            visableProperties.put(key, entry.getValue());
        }
        return visableProperties;
    }

    public static void putProperty(String key, Object value) {
        properties.put(key, value);
    }

    public static String getJpaShowSql() {
        if (StringUtils.isBlank(jpaShowSql)) {
            jpaShowSql = getProperty(JPA_SHOWSQL);
        }
        if (StringUtils.isBlank(jpaShowSql)) {
            jpaShowSql = "true";
        }
        return jpaShowSql;
    }

    public static String getJpaGenerateddl() {
        if (StringUtils.isBlank(jpaGenerateddl)) {
            jpaGenerateddl = getProperty(JPA_GENERATEDDL);
        }
        if (StringUtils.isBlank(jpaGenerateddl)) {
            jpaGenerateddl = "false";
        }
        return jpaGenerateddl;
    }

    public static String getLogLevel() {
        if (StringUtils.isBlank(logLevel)) {
            logLevel = getProperty(LOG_LEVEL);
        }
        if (StringUtils.isBlank(logLevel)) {
            // TODO: 2015/12/13 unfinished
            logLevel = Constants.DEFAULT_LOG_LEVEL;
        }
        return logLevel;
    }

    public static String getPatternTime() {
        if (StringUtils.isBlank(patternTime)) {
            patternTime = getProperty(PATTERN_TIME);
        }
        if (StringUtils.isBlank(patternTime)) {
            patternTime = Constants.DEFAULT_PATTERN_TIME;
        }
        return patternTime;
    }

    public static String getPatternDate() {
        if (StringUtils.isBlank(patternDate)) {
            patternDate = getProperty(PATTERN_DATE);
        }
        if (StringUtils.isBlank(patternDate)) {
            patternDate = Constants.DEFAULT_PATTERN_DATE;
        }
        return patternDate;
    }

    public static String getPatternDateTime() {
        if (StringUtils.isBlank(patternDateTime)) {
            patternDateTime = getProperty(PATTERN_DATETIME);
        }
        if (StringUtils.isBlank(patternDateTime)) {
            patternDateTime = Constants.DEFAULT_PATTERN_DATETIME;
        }
        return patternDateTime;
    }

    public static String getPatternDateTimeStamp() {
        if (StringUtils.isBlank(patternDateTimeStamp)) {
            patternDateTimeStamp = getProperty(PATTERN_DATETIMESTAMP);
        }
        if (StringUtils.isBlank(patternDateTimeStamp)) {
            patternDateTimeStamp = Constants.DEFAULT_PATTERN_DATETIMESTAMP;
        }
        return patternDateTimeStamp;
    }

    public static String getConfigPath() {
        if (StringUtils.isEmpty(configPath)) {
            configPath = getProperty(EchoConfiguration.CONFIG_PATH);
        }
        if (StringUtils.isEmpty(configPath)) {
            throw new EchoException(String.format("environment variable: %s must be set！", EchoConfiguration.CONFIG_PATH));
        }

        return configPath;
    }

}
