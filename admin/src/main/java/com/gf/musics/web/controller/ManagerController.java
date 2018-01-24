package com.gf.musics.web.controller;

import com.gf.musics.web.constant.ParameterConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.service.ManagerService;
import com.gf.musics.web.util.DateTableUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "manage")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    //     管理员登录页面
    @RequestMapping(value = "login.html")
    public ModelAndView loginPage() {
        Map returnMap = new HashMap();
        return new ModelAndView("manage/login", returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "loginCheck.json")
    public void loginCheck(
            HttpSession session,
            HttpServletResponse response,
            @RequestParam Map map
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            returnMap = managerService.ManagerLogin(map);
            if (String.valueOf(returnMap.get(ParameterConstant.RETURN_CODE)).equals("0")) {
                session.setAttribute(ParameterConstant.USER_ACCOUNT, returnMap.get(ParameterConstant.USER_ACCOUNT));
                session.setAttribute(ParameterConstant.P_ADMINID, returnMap.get(ParameterConstant.P_ADMINID));
                session.setAttribute(ParameterConstant.P_ROLEID, returnMap.get(ParameterConstant.P_ROLEID));
            }
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            System.out.println("------>" + e);
            returnMap = ResponseConstant.getOneResponseMsg("登录失败");
            outWriter.write(mapper.writeValueAsString(returnMap));
        }
    }

    //     管理员登录页面
    @RequestMapping(value = "index.html")
    public ModelAndView indexPage() {
        Map returnMap = new HashMap();
        return new ModelAndView("manage/index", returnMap);
    }

    @RequestMapping(value = "changePassword.html")
    public ModelAndView changePassword() {
        Map returnMap = new HashMap();
        return new ModelAndView("manage/changePassword", returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "changePassword.json")
    public void changePassword(
            HttpSession session,
            HttpServletResponse response,
            @RequestParam(value = "oldPassword", required = true, defaultValue = "") String oldPassword,
            @RequestParam(value = "password", required = true, defaultValue = "") String password
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            String account = (String) session.getAttribute(ParameterConstant.USER_ACCOUNT);
            returnMap = managerService.ChangePassword(account, oldPassword, password);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            returnMap = ResponseConstant.getOneResponseMsg("修改密码失败");
            outWriter.write(mapper.writeValueAsString(returnMap));
        }
    }

    @RequestMapping(value = "loginout.html")
    public ModelAndView loginout(HttpSession session) {
        Map returnMap = new HashMap();
        session.removeAttribute(ParameterConstant.P_ADMINID);
        session.removeAttribute(ParameterConstant.USER_ACCOUNT);
        session.removeAttribute(ParameterConstant.P_ROLEID);
        return new ModelAndView("manage/login", returnMap);
    }

    @ResponseBody
    @RequestMapping(value = "getMenu.json")
    public void getMenu(
            HttpSession session,
            HttpServletResponse response
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            Integer roleId = (Integer) session.getAttribute(ParameterConstant.P_ROLEID);
            returnMap = managerService.GetMenu(roleId);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            returnMap = ResponseConstant.getOneResponseMsg("登陆过期，获取功能菜单失败");
            outWriter.write(mapper.writeValueAsString(returnMap));
        }
    }


    @ResponseBody
    @RequestMapping(value = "getPermissonByMenu.json")
    public void getPermissonByMenu(
            HttpSession session,
            HttpServletResponse response,
            @RequestParam(value = "menuId", required = false, defaultValue = "-1") Integer menuId
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            Map reqMap = new HashMap();
            Integer roleId = (Integer) session.getAttribute(ParameterConstant.P_ROLEID);
            reqMap.put("roleId", roleId);
            reqMap.put("resourceId", menuId);
            returnMap = managerService.GetButton(reqMap);
            outWriter.write(mapper.writeValueAsString(returnMap));
        } catch (Exception e) {
            returnMap = ResponseConstant.getOneResponseMsg("登陆过期，获取功能菜单失败");
            outWriter.write(mapper.writeValueAsString(returnMap));
        }
    }

}
