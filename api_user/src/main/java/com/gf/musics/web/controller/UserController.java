package com.gf.musics.web.controller;

import com.gf.musics.web.Covert.RequestCovert;
import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.service.UserService;
import com.gf.musics.web.service.UserSongListService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserSongListService userSongListService;
    /**
     * 获取用户信息
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "getUserData.action")
    public void getUserData(HttpServletResponse response,
                            @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try{
            Map requeryMap = RequestCovert.getParamMap(requestBody);
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                returnMap = userService.getUserData((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("getUserData.action:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }

    /**
     * 用户登陆账号信息录入、编辑
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "userLogin.action")
    public void userLogin(HttpServletResponse response,
                          @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try{
            Map requeryMap = RequestCovert.getParamMap(requestBody);
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                returnMap = userService.userLogin((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("userLogin.action:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }

    /**
     * 我喜欢的歌曲
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "userLikeMusic.action")
    public void userLikeMusic(HttpServletResponse response,
                              @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try{
            Map requeryMap = RequestCovert.getParamMap(requestBody);
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                returnMap = userService.userLikeMusic((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("userLikeMusic.action:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }

    /**
     * 获取用户歌单及歌单里面的歌曲数量
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "usersongListMusic.action")
    public void usersongListMusic(HttpServletResponse response,
                                  @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        Map requeryMap = RequestCovert.getParamMap(requestBody);
        try{
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                returnMap = userSongListService.getUserSongListMusic((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("usersongListMusic.action:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }

    /**
     * 新增我的歌单
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "UserInsertSongList.action")
    public void UserInsertSongList(HttpServletResponse response,
                                   @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        Map requeryMap = RequestCovert.getParamMap(requestBody);
        try{
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                returnMap = userSongListService.UserInsertSongList((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("UserInsertSongList.action:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }

    /**
     * 删除我的歌单
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "deleteUserSongList.action")
    public void deleteUserSongList(HttpServletResponse response,
                                   @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        Map requeryMap = RequestCovert.getParamMap(requestBody);
        try{
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                returnMap = userSongListService.deleteUserSongList((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("deleteUserSongList.action:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }

    /**
     * 我收藏的歌手
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "selectMySinger.action")
    public void mySinger(HttpServletResponse response,
                             @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        Map requeryMap = RequestCovert.getParamMap(requestBody);
        try{
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                returnMap = userService.selectMySinger((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("selectMySinger.action:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }
    /**
     * 我收藏的专辑
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "selectMyAlbum.action")
    public void MyAlbum(HttpServletResponse response,
                         @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        Map requeryMap = RequestCovert.getParamMap(requestBody);
        try{
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                returnMap = userService.selectMyAlbum((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("selectMyAlbum.action:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }
    /**
     * 我收藏的Mv
     * @param response
     * @param requestBody
     * @throws IOException
     */
    @RequestMapping(value = "selectMyMv.action")
    public void MyMv(HttpServletResponse response,
                        @RequestParam(value = "requestBody" , required = false ,defaultValue = "") String requestBody
    )throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        Map requeryMap = RequestCovert.getParamMap(requestBody);
        try{
            if(((Integer)requeryMap.get(APIConstant.CODE)) == 0){
                returnMap = userService.selectMyMv((Map) requeryMap.get(APIConstant.BODY));
            }else {
                requeryMap.put(APIConstant.CODE,1);
                requeryMap.put(APIConstant.MSG,"参数有误，请校验后重新请求");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("selectMyMv.action:" + returnMap, e);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }
}
