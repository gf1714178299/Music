package com.gf.musics.web.controller;

import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.model.Role;
import com.gf.musics.web.service.RoleService;
import com.gf.musics.web.util.DateTableUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Created by lokey on 2017/7/7.
 */
@Controller
@RequestMapping(value = "manage")
public class ManageRoleController {

    private final static Logger logger = LoggerFactory.getLogger(ManageRoleController.class);
    @Autowired
    private RoleService roleService;

    // 角色列表页面
    @RequestMapping(value = "rolemanage.html")
    public ModelAndView rolemanagePage(){
        Map returnMap = new HashMap();
        return new ModelAndView("manage/role/role-list",returnMap);
    }

    // 角色新增修改页面
    @RequestMapping(value = "rolesave.html")
    public ModelAndView rolesavePage(@RequestParam(value = "id",required = false,defaultValue = "-1")int id){
        Map returnMap = new HashMap();
        if(id!=-1) {
            returnMap = roleService.getRoleInfo(id);
        }
        return new ModelAndView("manage/role/role-save",returnMap);
    }

    // 角色新增修改页面
    @RequestMapping(value = "rolepermission.html")
    public ModelAndView rolepermissionPage(@RequestParam(value = "id",required = false,defaultValue = "-1")int id){
        Map returnMap = new HashMap();
        returnMap = roleService.getPermissionPage();
        returnMap.put("id",id);
        return new ModelAndView("manage/role/role-permission",returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "role-list.json")
    public void roleListDo(
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
            returnMap = roleService.getRoleList(requestMap);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }catch (Exception e){
            logger.error("roleListDo:" + returnMap, e);
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }
        return;
    }


    //角色禁用
    @ResponseBody
    @RequestMapping(value = "changeRoleStatus.json")
    public void changeRoleStatusDo(
            HttpServletResponse response,
            @RequestParam(value = "id",required = true,defaultValue = "")int id
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            returnMap = roleService.changeStatus(id);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            logger.error("changeRoleStatus:" + returnMap, e);
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            outWriter.write(mapper.writeValueAsString(returnMap));
            e.printStackTrace();
        }
    }


    @ResponseBody
    @RequestMapping(value = "saverole.json")
    public Map saveRole(@RequestBody Role role){
        Map returnMap = new HashMap();
        try {
            if(role!=null){
                if (role.getPkId()==null||role.getPkId().equals("")) {
                    returnMap = roleService.addRole(role);
                    return returnMap;
                }else {
                    returnMap = roleService.editRole(role);
                    return returnMap;
                }
            }
            returnMap= ResponseConstant.getResponsecodeDesc(0);
            return returnMap;
        }catch (Exception e){
            logger.error("saveRole:" + returnMap, e);
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            e.printStackTrace();
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping(value = "deleteRoleList.json")
    public void deleteRoleListDo(
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
            returnMap = roleService.deleteRoleList(queryMap);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            logger.error("deleteRoleList:" + returnMap, e);
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            outWriter.write(mapper.writeValueAsString(returnMap));
            e.printStackTrace();
        }
        return;
    }


    //角色删除
    @ResponseBody
    @RequestMapping(value = "deleteRole.json")
    public void deleteRoleDo(
            HttpServletResponse response,
            @RequestParam(value = "id",required = true,defaultValue = "")int id
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            returnMap = roleService.deleteRole(id);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            logger.error("deleteRoleDo:" + returnMap, e);
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            outWriter.write(mapper.writeValueAsString(returnMap));
            e.printStackTrace();
        }
    }


    @ResponseBody
    @RequestMapping(value = "promissionList.json")
    public void promissionListDo(
            HttpServletResponse response,
            @RequestParam(value = "id", required = true, defaultValue = "-1") Integer id
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            if (id!=-1){ //判断id不为空
                returnMap = roleService.getPermission(id);
            }
            outWriter.write(mapper.writeValueAsString(returnMap));
        }catch (Exception e){
            logger.error("promissionList:" + returnMap, e);
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }
        return;
    }

    @ResponseBody
    @RequestMapping(value = "changePermission.json")
    public void changePermissionDo(
            HttpServletResponse response, HttpServletRequest request, HttpSession httpSession,
            @RequestParam(value = "id", required = false, defaultValue = "-1") Integer id,
            @RequestParam(value = "ids", required = true, defaultValue = "") String ids
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map queryMap = new HashMap();
        Map returnMap = new HashMap();
        try {
            queryMap.put("id",id);
            queryMap.put("ids",ids);
            returnMap = roleService.changePermission(queryMap);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            logger.error("changePermission:" + returnMap, e);
            returnMap = ResponseConstant.getResponsecodeDesc(200);
            outWriter.write(mapper.writeValueAsString(returnMap));
            e.printStackTrace();
        }
        return;
    }

}
