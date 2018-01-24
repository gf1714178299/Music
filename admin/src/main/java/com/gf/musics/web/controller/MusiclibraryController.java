package com.gf.musics.web.controller;

import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.model.MusicStyle;
import com.gf.musics.web.service.MusicStyleService;
import com.gf.musics.web.service.MusiclibraryService;
import com.gf.musics.web.util.DateTableUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "manage")
public class MusiclibraryController {
    @Autowired
    private MusiclibraryService musiclibraryService;
    @Autowired
    private MusicStyleService musicStyleService;
    /**
     *音乐查找
     * @return
     */
    @RequestMapping(value = "musiclibrary.html")
    public ModelAndView managermanagePage(){
        Map returnMap = new HashMap();
        return new ModelAndView("manage/musiclibrary/musiclibrary-list",returnMap);
    }

    /**
     * 添加音乐
     * @return
     */
    @RequestMapping(value = "musicAdd.html")
    public ModelAndView musicAddavePage(){
        Map returnMap = new HashMap();
//        Map requestmap = new HashMap();
//        returnMap = musicStyleService.selectByPage(requestmap);
        return new ModelAndView("manage/musiclibrary/musiclibrary-add",returnMap);
    }

    /**
     * 获取所有MusicStyle
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "selectMusicStyleListByPage.json")
    public void selectMusicStyleListByPage(HttpServletResponse response)throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try{
            Map requestmap = new HashMap();
            returnMap = musicStyleService.selectByPage(requestmap);
        }catch (Exception e){
            returnMap = ResponseConstant.getResponsecodeDesc(1000);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }

    /**
     * 音乐列表查找
     * @param response
     * @param aoData
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "musiclibrary-list.json")
    public void musiclibraryListDo(
            HttpServletResponse response,
            @RequestParam String aoData
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            Map requestMap = new HashMap();
            if(aoData!=null && !aoData.equals("")) {
                requestMap = DateTableUtil.getDateTableRequestDate(aoData);
            }
            returnMap = musiclibraryService.getMusiclibraryList(requestMap);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }catch (Exception e){
            returnMap = ResponseConstant.getResponsecodeDesc(1000);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }
        return;
    }
}
