package com.gf.musics.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenwei on 2016/11/1.
 */
public class ImageReplaceUtils {
    // 获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";

    public static String replaceImg(String html, String serverUrl) {
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);
        while (matcher.find()) {
            String img = matcher.group();
            if (img.contains("src=\"/web/upload/")) {
                html = html.replace(img, img.replace("src=\"/web/upload/", "src=\"" + serverUrl + "/web/upload/"));
            }

        }
        return html;
    }
}
