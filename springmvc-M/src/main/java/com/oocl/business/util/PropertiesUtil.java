package com.oocl.business.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesUtil {


    private static Properties props;

    private static final String FILE_NAME = "common.properties";

    static{
        readProperties(FILE_NAME);
    }

    /**
     * 读取配置文件
     */
    private static void readProperties(String fileName) {
        try {
            props = new Properties();
            InputStream fis = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取某个属性
     */
    public static String getProperty(String key) {
        if(props == null){
            readProperties(FILE_NAME);
        }
        String value = props.getProperty(key);
        if(StringUtils.isBlank(value)){
            return null;
        }else{
            return value.trim();
        }
    }

}
