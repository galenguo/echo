package com.echo.core.config;

import com.echo.core.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Galen
 * @since 2016/2/26
 */
@Component
public class EnvironmentConfiguurationLoader implements ConfigurationLoader {

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void setProperties() throws Exception {
        logger.info("load properties from System Environment");
        Field[] fields = EchoConfiguration.class.getFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            logger.debug(field.getName() + " " + mod );
            if (Modifier.isPublic(mod) && Modifier.isFinal(mod) && Modifier.isStatic(mod)) {
                String key = field.get(null).toString();

                String value = System.getenv(key);
                logger.debug( "key " + key + " value "+ value);
                if (StringUtils.isNotEmpty(value)) {
                    EchoConfiguration.putProperty(key, value);
                    logger.debug("putProperty {}:{} to EchoConfiguration", key, value);
                }
            }
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
