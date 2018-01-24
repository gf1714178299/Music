package com.gf.musics.web.Covert;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.util.Base64Utils;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lokey on 2017/7/17.
 */
public class RequestCovert {
//    public static void main(String[] args) {
//        Map map = getParamMap("ewogICJ0aW1lIiA6IDE0OTkwNDkyMzA3MTcsCiAgImJvZHkiIDogewogICAgInVzZXJJZCIgOiAiMTIzIgogIH0KfQ==",null);
//        System.out.println(map.get(APIConstant.CODE)+":"+map.get(APIConstant.MSG));
//    }

    public static Map getParamMap(String paramString){
        Map resultMap = new HashMap();
        resultMap.put(APIConstant.CODE,1);
        try {
            if(!paramString.trim().equals("")) {
//                resultMap = (Map) JSONObject.fromObject(Base64Utils.decode(paramString));
                resultMap = (Map) JSONObject.fromObject(paramString);
                resultMap.put(APIConstant.CODE,0);
            }
        }catch (Exception e){
            resultMap.put(APIConstant.MSG,"参数不合法，请校验后重试");
            return resultMap;
        }
        return resultMap;
    }
}
