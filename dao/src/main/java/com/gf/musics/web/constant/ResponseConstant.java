package com.gf.musics.web.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thy_niit on 2016/9/18.
 */
public class ResponseConstant {
    public final static Map getResponsecodeDesc(Integer requestCode) {
        Map returnMap = new HashMap();
        returnMap.put(ParameterConstant.RETURN_CODE, requestCode);
        switch (requestCode) {
            case -5:
                returnMap.put(ParameterConstant.RETURN_MSG,"您有未完成的订单");
            case 0:
                returnMap.put(ParameterConstant.RETURN_MSG,"返回成功");
                break;
            case 100:
                returnMap.put(ParameterConstant.RETURN_MSG,"登陆验证失败,请重新登陆");
                break;
            case 101:
                returnMap.put(ParameterConstant.RETURN_MSG,"您的客户端版本过低，请及时更新版本");
                break;
            case 102:
                returnMap.put(ParameterConstant.RETURN_MSG,"参数校验失败");
                break;
            case 110:
                returnMap.put(ParameterConstant.RETURN_MSG,"用户登录失效");
                break;
            case 111:
                returnMap.put(ParameterConstant.RETURN_MSG,"数据不存在");
                break;
            case 112:
                returnMap.put(ParameterConstant.RETURN_MSG,"用户名已存在");
                break;
            case 113:
                returnMap.put(ParameterConstant.RETURN_MSG,"账号已存在");
                break;
            case 200:
                returnMap.put(ParameterConstant.RETURN_MSG,"something is wrong，Please read logs！");
                break;
            case 1000:
                returnMap.put(ParameterConstant.RETURN_MSG,"系统运行时异常");
                break;
            default:
                break;
        }
        return returnMap;
    }


    public final static Map getOneResponseMsg(String msg){
        Map returnMap = new HashMap();
        returnMap.put(ParameterConstant.RETURN_CODE,1);
        returnMap.put(ParameterConstant.RETURN_MSG,msg);
        return returnMap;
    }


    public final static Map getSuccessResult(Object object){
        Map returnMap = new HashMap();
        returnMap.put(ParameterConstant.RETURN_CODE,0);
        returnMap.put(ParameterConstant.RETURN_MSG,"返回成功");
        returnMap.put(ParameterConstant.RETURN_DATA,object);
        return returnMap;
    }
}
