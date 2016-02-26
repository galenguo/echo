package com.echo.core.config;

import com.echo.core.utils.FileUtils;
import com.echo.core.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

/**
 * properties file loader.
 *
 * @author Galen
 * @since 2016/2/25
 */
public class PropertiesConfigurationLoader implements ConfigurationLoader {

    protected Logger logger = LogManager.getLogger(this.getClass());

    protected static final String DEFAULT_CONFIG_NAME = "echo.properties";

    protected String[] fileNames;

    protected int order = 1;

    public String[] getFileNames() {
        return fileNames;
    }

    public void setFileNames(String... fileNames) {
        this.fileNames = fileNames;
    }

    @Override
    public void setProperties() throws Exception {
        for (String fileName : fileNames) {
            String resourceName = FileUtils.addSeparatorIfNec(EchoConfiguration.getConfigPath()) + DEFAULT_CONFIG_NAME;
            if (StringUtils.isNotEmpty(fileName)) {
                if(StringUtils.isNotBlank(fileName))
                {
                    resourceName =  FileUtils.addSeparatorIfNec(EchoConfiguration.getConfigPath()) + fileName;
                }
            }
            logger.debug("Loading properties from {} ", resourceName);
            Properties properties = PropertiesLoaderUtils.loadProperties(new FileSystemResource(resourceName));


            if (null != properties) {

                for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                    Object key = entry.getKey();
                    String value = (String) entry.getValue();
                    EchoConfiguration.putProperty((String) key, value);
                    logger.debug("putProperty {}:{} to Configuration", key, value);
                }
            } else {
                logger.error("Loading Error from file: {}", resourceName);
            }
        }
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
