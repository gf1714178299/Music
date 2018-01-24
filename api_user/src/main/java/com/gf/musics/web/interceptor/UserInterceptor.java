package com.gf.musics.web.interceptor;


import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.redis.TokenRedisDao;
import com.gf.musics.web.util.StringUtil;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by lokey on 2015/10/23.
 */
public class UserInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
    @Autowired
    private TokenRedisDao tokenRedisDao;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map resultMap = new HashMap();
        resultMap.put(APIConstant.CODE,1);
        String params = request.getParameter(APIConstant.PARAMS);
        if(StringUtil.isEmpty(params)||!params.contains("{")){
            resultMap = ResponseConstant.getResponsecodeDesc(101);
            outWriter.write(mapper.writeValueAsString(resultMap));
            logger.error("LOGIN_INTERCEPTOR_FAIL:{}",params);
            return false;
        }
        params = URLDecoder.decode(params, "UTF-8");
        JSONObject jsonObject = JSONObject.fromObject(params);

        Integer userId = 0;
        if(jsonObject.get("userId") == null){
            userId = (Integer) jsonObject.get(APIConstant.USERID);
        }
        String userToken = (String)jsonObject.get("userToken");

        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userToken)){
            resultMap = ResponseConstant.getResponsecodeDesc(100);
            outWriter.write(mapper.writeValueAsString(resultMap));
            logger.error("LOGIN_INTERCEPTOR_FAIL:{}",params);
            return false;
        }else{
            String checkToken = tokenRedisDao.getUserToken(userId);
            if ((userToken != null && userToken.equals(checkToken))) {
                return true;
            } else {
                resultMap = ResponseConstant.getResponsecodeDesc(100);
                outWriter.write(mapper.writeValueAsString(resultMap));
                logger.error("LOGIN_INTERCEPTOR_FAIL:{}",params);
                return false;
            }
        }
    }


    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}
