package com.gf.musics.web.interceptor;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.util.NetworkUtil;
import com.gf.musics.web.util.SignUtil;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by chenpan on 2017/5/4.
 */
public class SignCheckInterceptor implements HandlerInterceptor {
    @Value("#{configProperties['force.version']}")
    private String FORCE_VERSION;

    private Logger logger = LoggerFactory.getLogger(SignCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map resultMap = new HashMap();
        String params = request.getParameter("params");
        params = URLDecoder.decode(params, "UTF-8");
        JSONObject jsonObject = JSONObject.fromObject(params);
        String clientVer = jsonObject.getString("clientVer");

        logger.info(NetworkUtil.getIpAddress(request) +  request.getRequestURI() + JSONObject.fromObject(request.getParameterMap()).toString());

        if (!checkClientVersion(clientVer)){
            resultMap = ResponseConstant.getResponsecodeDesc(101);
            outWriter.write(mapper.writeValueAsString(resultMap));
            return false;
        }

        String headerSign = request.getHeader(APIConstant.HEADER_ENCRYPTE_SIGN);
        String sign = (String)jsonObject.get("sign");
        if (StringUtils.isEmpty(headerSign)){
            logger.warn("header sign empty:" + params);
            resultMap = ResponseConstant.getResponsecodeDesc(100);
            outWriter.write(mapper.writeValueAsString(resultMap));
            return false;
        }
        if (StringUtils.isEmpty(sign) || !headerSign.equals(sign)){
            logger.warn("header sign error :" + params);
            resultMap = ResponseConstant.getResponsecodeDesc(100);
            outWriter.write(mapper.writeValueAsString(resultMap));
            return false;
        }

        SortedMap<Object,Object> sort = new TreeMap<Object,Object>(jsonObject);
        String checkedSign = SignUtil.createSign(APIConstant.SIGN_KEY_RELEASE,sort);
        if (checkedSign.equals(sign)){
            return true;
        }else{

            logger.warn("sign check error:" + params);
            resultMap = ResponseConstant.getResponsecodeDesc(100);
            outWriter.write(mapper.writeValueAsString(resultMap));
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    public boolean checkClientVersion(String clientVersion){

        String[] versionStr = clientVersion.split("\\.");

        String v1 = versionStr[0];
        String v2 = versionStr[1];
        String v3 = versionStr[2];

        String[] forceVersion = FORCE_VERSION.split("\\.");

        String version1 = forceVersion[0];
        String version2 = forceVersion[1];
        String version3 = forceVersion[2];

        if (Integer.valueOf(v1).intValue() < Integer.valueOf(version1).intValue()){
            return false;
        }else if(Integer.valueOf(v2).intValue() < Integer.valueOf(version2).intValue()){
            return false;
        }else if(Integer.valueOf(v3).intValue() < Integer.valueOf(version3).intValue()){
            return false;
        }

        return true;
    }



}
