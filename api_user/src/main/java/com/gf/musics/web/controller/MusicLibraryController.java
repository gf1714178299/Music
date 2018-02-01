package com.gf.musics.web.controller;

import com.gf.musics.web.Covert.RequestCovert;
import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.CovertParams;
import com.gf.musics.web.constant.ParameterConstant;
import com.gf.musics.web.service.MusicLibraryService;
import com.gf.musics.web.util.DateTableUtil;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "musiclibrary")
public class MusicLibraryController {
    private final static Logger logger = LoggerFactory.getLogger(MusicLibraryController.class);
    @Autowired
    private MusicLibraryService musicLibraryService;
    /**
     * 歌曲搜索(以点击量排序)
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "musiclibrary-list.action")
    public void musiclibraryListDo(HttpServletResponse response,
                                   @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter outWriter = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map returnMap = new HashMap();
            try{
                Map requeryMap = RequestCovert.getParamMap(requestBody);
                if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                    returnMap = musicLibraryService.selectMusiclibraryByPage((Map) requeryMap.get(APIConstant.BODY));
                }else {
                    requeryMap.put(APIConstant.CODE,1);
                    requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("musiclibrary-list:" + returnMap, e);
            }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }
    /**
     * 歌曲搜索(以发行时间排序)
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "musiclibraryByNew-list.action")
    public void musiclibraryByNewListDo(HttpServletResponse response,
                                   @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try{
            Map requeryMap = RequestCovert.getParamMap(requestBody);
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                returnMap = musicLibraryService.selectMusiclibraryByNew((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("musiclibraryByNew-list:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }

}
