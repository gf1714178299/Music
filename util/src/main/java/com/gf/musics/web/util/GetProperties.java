package com.gf.musics.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chenxin on 2016/02/02.
 */
public class GetProperties {

    public static String wordPath = null;




    /**
     * 获取敏感词库
     *
     * @return
     * @throws IOException
     */
    public static String getWordPath() throws IOException {
        if (wordPath != null) {
            return wordPath;
        }
        Properties prop = new Properties();// 属性集合对象
        //定位看到包路径的第一级路径
        InputStream fis = GetProperties.class.getClassLoader().getResourceAsStream("configs/url.properties");
        prop.load(fis);// 将属性文件流装载到Properties对象中
        fis.close();// 关闭流
        String url = prop.getProperty("wordPath");
        wordPath = url;
        return url;
    }

    /**
     * 获取图片url
     *
     * @return
     * @throws IOException
     */
    public static String getFilePath() throws IOException {
        Properties prop = new Properties();// 属性集合对象
        //定位看到包路径的第一级路径
        InputStream fis = GetProperties.class.getClassLoader().getResourceAsStream("configs/file.properties");
        prop.load(fis);// 将属性文件流装载到Properties对象中
        fis.close();// 关闭流
        String url = prop.getProperty("url");
        return url;
    }

    public static String getNewsFilePath() throws IOException {
        Properties prop = new Properties();// 属性集合对象
        //定位看到包路径的第一级路径
        InputStream fis = GetProperties.class.getClassLoader().getResourceAsStream("configs/file.properties");
        prop.load(fis);// 将属性文件流装载到Properties对象中
        fis.close();// 关闭流
        String url = prop.getProperty("newsUrl");
        return url;
    }
}
