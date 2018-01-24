package com.gf.musics.web.controller;

import com.gf.musics.web.Covert.RequestCovert;
import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.service.RecommendService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "recommend")
public class RecommendController {
    private final static Logger logger = LoggerFactory.getLogger(RecommendController.class);
    @Autowired
    private RecommendService recommendService;

    /**
     * 根据歌曲类型搜索前十条相对于的歌曲，用于点击歌单
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "getRecommendMusic.action")
    public void getRecommendMusic(HttpServletResponse response,
                                  @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            Map requeryMap = RequestCovert.getParamMap(requestBody);
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
            returnMap = recommendService.getRecommendMusic((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("getRecommendMusic.action:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }

}
