package com.gf.musics.web.controller;

import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.model.Manager;
import com.gf.musics.web.service.ManagerService;
import com.gf.musics.web.util.DateTableUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

;

/**
 * Created by lokey on 2017/7/7.
 */
@Controller
@RequestMapping(value = "manage")
public class ManageManagerController {
    @Autowired
    private ManagerService managerService;

    // 管理员列表页面
    @RequestMapping(value = "managermanage.html")
    public ModelAndView managermanagePage(){
        Map returnMap = new HashMap();
        return new ModelAndView("manage/manager/manager-list",returnMap);
    }

    // 管理员修改页面
    @RequestMapping(value = "managersave.html")
    public ModelAndView managersavePage(@RequestParam(value = "id",required = false,defaultValue = "-1")int id){
        Map returnMap = new HashMap();
        returnMap = managerService.getManagerSavePageInfo(id);
        return new ModelAndView("manage/manager/manager-save",returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "manager-list.json")
    public void managerListDo(
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
            returnMap = managerService.getManagerList(requestMap);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }catch (Exception e){
            returnMap = ResponseConstant.getResponsecodeDesc(10005);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }
        return;
    }


    //管理员禁用
    @ResponseBody
    @RequestMapping(value = "changeManagerStatus.json")
    public void changeManagerStatusDo(
            HttpServletResponse response,
            @RequestParam(value = "id",required = true,defaultValue = "")int id
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            returnMap = managerService.changeStatus(id);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            outWriter.write(mapper.writeValueAsString(returnMap));
            e.printStackTrace();
        }
    }

    //管理员密码重置
    @ResponseBody
    @RequestMapping(value = "resetManagerPassword.json")
    public void resetManagerPaawordDo(
            HttpServletResponse response,
            @RequestParam(value = "id",required = true,defaultValue = "")int id
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            returnMap = managerService.resetPassword(id);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            outWriter.write(mapper.writeValueAsString(returnMap));
            e.printStackTrace();
        }
    }


    @ResponseBody
    @RequestMapping(value = "saveManager.json")
    public Map saveManager(@RequestBody Manager manager){
        Map returnMap = new HashMap();
        try {
            if(manager!=null){
                if (manager.getPkId()==null||manager.getPkId().equals("")) {
                    returnMap = managerService.addManager(manager);
                    return returnMap;
                }else {
                    returnMap = managerService.editManager(manager);
                    return returnMap;
                }
            }
            returnMap= ResponseConstant.getResponsecodeDesc(0);
            return returnMap;
        }catch (Exception e){
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            e.printStackTrace();
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "deleteManagerList.json")
    public void deleteManagerListDo(
            HttpServletResponse response, HttpServletRequest request, HttpSession httpSession,
            @RequestParam(value = "ids", required = true, defaultValue = "") String ids
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map queryMap = new HashMap();
        Map returnMap = new HashMap();
        try {
            queryMap.put("ids",ids);
            returnMap = managerService.deleteManagerList(queryMap);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }


    //删除管理员
    @ResponseBody
    @RequestMapping(value = "deleteManager.json")
    public void deleteManagerDo(
            HttpServletResponse response,
            @RequestParam(value = "id",required = true,defaultValue = "")int id
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            returnMap = managerService.deleteManager(id);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            outWriter.write(mapper.writeValueAsString(returnMap));
            e.printStackTrace();
        }
    }

}
