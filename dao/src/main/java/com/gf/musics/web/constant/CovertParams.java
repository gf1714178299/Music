package com.gf.musics.web.constant;

import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by lokey on 2017/10/23.
 */
public class CovertParams {


    public static Map getBody(String params){
        JSONObject paramsObject = JSONObject.fromObject(params);
        Map bodyMap = (Map) paramsObject.get("body");
//        bodyMap.put(APIConstant.USERID,paramsObject.get(APIConstant.USERID)); //用户id
//        bodyMap.put(APIConstant.CLIENTTYPE,paramsObject.get(APIConstant.CLIENTTYPE)); //版本号
        return bodyMap;
    }

}
