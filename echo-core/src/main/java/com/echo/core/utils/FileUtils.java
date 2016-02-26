package com.echo.core.utils;

import java.io.File;

/**
 * file utils
 *
 * @author Galen
 * @since 2015/12/6.
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

    /**
     * add separator for dir path
     *
     * @param dirName
     * @return
     */
    public static String addSeparatorIfNec(String dirName) {
        if (StringUtils.isNotBlank(dirName)
                && !dirName.endsWith(File.separator)) {
            dirName += File.separator;
        }
        return dirName;
    }
}
