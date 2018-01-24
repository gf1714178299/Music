package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.ParameterConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.*;
import com.gf.musics.web.model.*;
import com.gf.musics.web.service.ManagerService;
import com.gf.musics.web.util.MD5Util;
import com.gf.musics.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lokey on 2017/7/7.
 */
@Component(value = "managerService")
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private PermissionAssignmentMapper permissionAssignmentMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map ManagerLogin(Map map) {
        Map returnMap = new HashMap();
        Manager manager = managerMapper.selectByAccount(String.valueOf(map.get(ParameterConstant.USER_ACCOUNT)));
        if(manager==null){
            returnMap = ResponseConstant.getOneResponseMsg("用户不存在");
            return returnMap;
        }if(!manager.getPassword().equals(MD5Util.MD5Encode(String.valueOf(map.get("password"))))){
            returnMap = ResponseConstant.getOneResponseMsg("用户名或密码错误");
            return returnMap;
        }
        if(manager.getIsEnabled()==2){
            returnMap = ResponseConstant.getOneResponseMsg("用户已被停用,请联系上级管理员!");
            return returnMap;
        }
        if(manager.getPkId()!=-2){
            Role role = roleMapper.selectByPrimaryKey(manager.getRoleId());
            if(role!=null){
                if(role.getIsEnabled()==2){
                    returnMap = ResponseConstant.getOneResponseMsg("角色已被停用,请联系上级管理员!");
                    return returnMap;
                }
            }
        }
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        returnMap.put(ParameterConstant.USER_ACCOUNT,manager.getAccount());
        returnMap.put(ParameterConstant.P_ADMINID,manager.getPkId());
        returnMap.put(ParameterConstant.P_ROLEID,manager.getRoleId());
        return returnMap;
    }


    @Override
    public Map GetMenu(Integer roleId) {
        Map returnMap = new HashMap();
        if (roleId == null) {
            returnMap = ResponseConstant.getResponsecodeDesc(110);
            return returnMap;
        }
        List<Map> menuList = new ArrayList<Map>();
        if(roleId != -2) {//超管为-2
            //这里先获取用户权限
            Map reqMap = new HashMap();
            reqMap.put(ParameterConstant.P_ROLEID, roleId);
            List<PermissionAssignment> permissionAssignmentList = permissionAssignmentMapper.selectByPage(reqMap);

            //在获取系统所有一级菜单
            Map fatherMap = new HashMap();
            fatherMap.put(ParameterConstant.P_FATHERID, -1);
            List<Resource> resourceList = resourceMapper.selectByPage(fatherMap);

            for (Resource resource : resourceList) {
                Map menuMap = new HashMap();
                //根据一级菜单遍历二级菜单
                Map secondFatherMap = new HashMap();
                secondFatherMap.put(ParameterConstant.P_FATHERID, resource.getPkId());
                List<Resource> secondResourceList = resourceMapper.selectByPage(secondFatherMap);

                //找出用户权限中二级菜单
                List<Resource> secondResultList = new ArrayList<Resource>(); //记录用户二级菜单权限
                for (Resource secondResource : secondResourceList) {
                    for (PermissionAssignment permissionAssignment : permissionAssignmentList) {
                        if (secondResource.getPkId() == permissionAssignment.getResourceId() && secondResource.getIsEnabled() == 1) {
                            secondResultList.add(secondResource);
                        }
                    }
                }
                //找出用户权限中一级模块
                for (PermissionAssignment permissionAssignment : permissionAssignmentList) {
                    if (resource.getPkId() == permissionAssignment.getResourceId() && resource.getIsEnabled() == 1) {
                        menuMap.put("ModelId", resource.getPkId());
                        menuMap.put("ModelName", resource.getName());
                        menuMap.put("secondList", secondResultList); //记录二级菜单数据
                        menuList.add(menuMap); //记录模块信息
                    }
                }

            }
            returnMap = ResponseConstant.getResponsecodeDesc(0);
            returnMap.put("menus", menuList);
        }else { //超管权限
            //在获取系统所有一级菜单
            Map fatherMap = new HashMap();
            fatherMap.put(ParameterConstant.P_FATHERID, -1);
            List<Resource> resourceList = resourceMapper.selectByPage(fatherMap);
            for (Resource resource : resourceList) {
                Map menuMap = new HashMap();
                //根据一级菜单遍历二级菜单
                menuMap.put("ModelId", resource.getPkId());
                menuMap.put("ModelName", resource.getName());
                Map secondFatherMap = new HashMap();
                secondFatherMap.put(ParameterConstant.P_FATHERID, resource.getPkId());
                List<Resource> secondResourceList = resourceMapper.selectByPage(secondFatherMap);
                //找出用户权限中二级菜单
                List<Resource> secondResultList = new ArrayList<Resource>(); //记录用户二级菜单权限
                for (Resource secondResource : secondResourceList) {
                    secondResultList.add(secondResource);
                }

                menuMap.put("secondList", secondResultList); //记录二级菜单数据
                menuList.add(menuMap); //记录模块信息
            }
            returnMap = ResponseConstant.getResponsecodeDesc(0);
            returnMap.put("menus", menuList);
        }
        return returnMap;
    }

    @Override
    public Map GetButton(Map map) {
        Map returnMap = new HashMap();
        List<Integer> buttonList = new ArrayList<Integer>();
        Map fatherMap  = new HashMap();
        fatherMap.put(ParameterConstant.P_FATHERID,map.get("resourceId"));
        List<Resource> resourceList = resourceMapper.selectByPage(fatherMap);
        List<Integer> manageButtonList = new ArrayList<Integer>();
        for (Resource resource : resourceList){
            buttonList.add(resource.getPkId());
        }
        if((Integer) map.get("roleId")!=-2) { //非超管
            map.remove("resourceId");
            List<PermissionAssignment> permissionAssignmentList = permissionAssignmentMapper.selectByPage(map);
            for(PermissionAssignment permissionAssignment:permissionAssignmentList){
                if(buttonList.contains(permissionAssignment.getResourceId())){
                    manageButtonList.add(permissionAssignment.getResourceId());
                }
            }
            buttonList = manageButtonList;
        }
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        returnMap.put("buttonList", buttonList);
        return returnMap;
    }

    @Override
    public Map getManagerSavePageInfo(int id) {
        Map<String, Object> returnMap = new HashMap();
        List<Role> roleList = roleMapper.selectByPage(null);
        returnMap.put("roleList",roleList);
        if(id!=-1) { //id=-1是新增页面 其他是修改页面
            Manager manager = managerMapper.selectByPrimaryKey(id);
            if (manager == null) {
                returnMap = ResponseConstant.getResponsecodeDesc(111);
                return returnMap;
            }
            returnMap.put("pkId", manager.getPkId());
            returnMap.put("adminAccount", manager.getAccount());
            returnMap.put("roleId", manager.getRoleId());
            returnMap.put("isEnabled", manager.getIsEnabled());
            returnMap.put("name", manager.getName());

        }
        returnMap.put("expertRoleId",ParameterConstant.EXPERT_ROLE_ID);
        return returnMap;
    }



    @Override
    public Map getManagerList(Map map) {
        Map returnMap = new HashMap();
        List<Manager> managerList = managerMapper.selectByPage(map); //获取当前页面内容
        if (null == managerList){
            int totalNum = managerMapper.selectByCount(map);      //获取数据总数
            returnMap = ResponseConstant.getResponsecodeDesc(0);
            returnMap.put(ParameterConstant.RETURN_DATA,managerList);
            returnMap.put(ParameterConstant.DATA_ITOTALDISPLAYRECORDS,totalNum);
            returnMap.put(ParameterConstant.DATA_ITOTALRECORDS,totalNum);
            return returnMap;
        }
        for (Manager manager:managerList){
            Role role = roleMapper.selectByPrimaryKey(manager.getRoleId());
            if (role!=null) {
                manager.setName(role.getName());
            }
        }
        int totalNum = managerMapper.selectByCount(map);      //获取数据总数
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        returnMap.put(ParameterConstant.RETURN_DATA,managerList);
        returnMap.put(ParameterConstant.DATA_ITOTALDISPLAYRECORDS,totalNum);
        returnMap.put(ParameterConstant.DATA_ITOTALRECORDS,totalNum);
        return returnMap;
    }



    @Override
    public Map changeStatus(int id) {
        Map returnMap = new HashMap();
        Manager manager = managerMapper.selectByPrimaryKey(id);
        if(manager==null){
            returnMap.put(ParameterConstant.RETURN_CODE, 0);
            returnMap.put(ParameterConstant.RETURN_MSG, "数据不存在");
            return returnMap;
        }
        if (manager.getIsEnabled() == 1) {
            manager.setIsEnabled(2);
        } else {
            manager.setIsEnabled(1);
        }
        managerMapper.updateByPrimaryKeySelective(manager);
        returnMap.put(ParameterConstant.RETURN_CODE, 0);
        returnMap.put(ParameterConstant.RETURN_MSG, "禁用/启用成功");
        return returnMap;
    }

    @Override
    public Map resetPassword(int id) {
        Map returnMap = new HashMap();
        Manager manager = managerMapper.selectByPrimaryKey(id);
        if(manager==null){
            returnMap.put(ParameterConstant.RETURN_CODE, 0);
            returnMap.put(ParameterConstant.RETURN_MSG, "数据不存在");
            return returnMap;
        }
        manager.setPassword(ParameterConstant.DEFAULT_PASSWORD);
        managerMapper.updateByPrimaryKeySelective(manager);
        returnMap.put(ParameterConstant.RETURN_CODE, 0);
        returnMap.put(ParameterConstant.RETURN_MSG, "重置密码成功");
        return returnMap;
    }

    @Override
    public Map addManager(Manager manager) {
        Map<String, Object> returnMap = new HashMap();
        Manager chkManager = managerMapper.selectByAccount(manager.getAccount());
        if(chkManager!=null){
            returnMap = ResponseConstant.getResponsecodeDesc(112);
            return returnMap;
        }
        manager.setPassword(ParameterConstant.DEFAULT_PASSWORD);
        manager.setDeleteFlag(1);
        manager.setCreateTime(new Date());
        manager.setUpdateTime(new Date());
        managerMapper.insertSelective(manager);
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        return returnMap;
    }

    @Override
    public Map editManager(Manager manager) {
        Map<String, Object> returnMap = new HashMap();
        Manager chkManager = managerMapper.selectByAccount(manager.getAccount());
        if(chkManager!=null){
            if(chkManager.getPkId()!=manager.getPkId()) {
                returnMap = ResponseConstant.getResponsecodeDesc(112);
                return returnMap;
            }
        }
        manager.setUpdateTime(new Date());
        managerMapper.updateByPrimaryKeySelective(manager);
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        return returnMap;
    }

    @Override
    public Map deleteManagerList(Map map) {
        Map returnMap = new HashMap();
        String ids = String.valueOf(map.get("ids"));
        String[] manageid = ids.split(",");
        for (int i = 0; i<manageid.length;i++){
            Manager manager = managerMapper.selectByPrimaryKey(Integer.valueOf(manageid[i]));
            if(manager!=null) {
                manager.setDeleteFlag(2);
                managerMapper.updateByPrimaryKey(manager);
            }
        }
        returnMap.put(ParameterConstant.RETURN_CODE,0);
        returnMap.put(ParameterConstant.RETURN_MSG,"删除成功");
        return returnMap;
    }

    @Override
    public Map deleteManager(int id) {
        Map returnMap = new HashMap();
        Manager manager = managerMapper.selectByPrimaryKey(id);
        if(manager!=null) {
            manager.setDeleteFlag(2);
            managerMapper.updateByPrimaryKey(manager);
        }
        returnMap.put(ParameterConstant.RETURN_CODE,0);
        returnMap.put(ParameterConstant.RETURN_MSG,"删除成功");
        return returnMap;
    }

//    @Override
//    public Map getZGH(Map map) {
//        Map<String, Object> returnMap = new HashMap();
//
//        //调用对接接口
//        JavaNetURLRESTFulClient resfulClient = new JavaNetURLRESTFulClient();
//        Map resultMap = resfulClient.restfulPost(RestfulConstant.ZGH_URL,new HashMap());
//        String code = String.valueOf(resultMap.get("status"));
//        List<Manager> managerList = new ArrayList<Manager>();
//        if ("200".equals(code)){//200请求成功
//            String msg = String.valueOf(resultMap.get("msg"));
//            String total = String.valueOf(resultMap.get("total"));
//            List<Map> list = (List<Map>) resultMap.get("result");
//            List<Manager> managers = managerMapper.selectByPage(null);
//            String[] employeeNum = new String[managers.size()];
//            for (int i =0;i<managers.size();i++){
//                employeeNum[i] = managers.get(i).getEmployeeNum();
//            }
//            for(int i = 0;i < list.size() - 1;i++){
//                for  (int j = list.size() - 1;j > i;j--){
//                    String ZGHi = String.valueOf(list.get(i).get("ZGH")).trim();
//                    String ZGHj = String.valueOf(list.get(j).get("ZGH")).trim();
//                    if(ZGHj.equals(ZGHi)){
//                        list.remove(j);
//                    }
//                }
//            }
//
//            for (int i = 0;i <list.size();i++) {
//                Map user = list.get(i);
//                String ZGH = String.valueOf(user.get("ZGH")).trim();
//                //匹配工号字符串
//                String str = Arrays.toString(  employeeNum );
//                Pattern rex = Pattern.compile( ZGH );
//                Matcher m = rex.matcher(str);
//                boolean flag = m.find();
//                if (!flag){
//                    Manager manager = new Manager();
//                    manager.setAccount(ZGH);
//                    manager.setEmployeeNum(ZGH);
//                    manager.setName(String.valueOf(user.get("XM")));
//                    manager.setIsEnabled(1);
//                    manager.setRoleId(31);//小编角色
//                    manager.setPassword(MD5Util.MD5Encode(ZGH));
//                    manager.setDeleteFlag(1);
//                    manager.setCreateTime(new Date());
//                    manager.setUpdateTime(new Date());
//                    managerList.add(manager);
//                }
//            }
//        }
//        managerMapper.batchInsertManager(managerList);
//        returnMap = ResponseConstant.getResponsecodeDesc(0);
//        return returnMap;
//    }

    @Override
    public Map ChangePassword(String account, String oldPassword, String password) {
        Map returnMap = new HashMap();
        if(StringUtil.isEmpty(account)){
            returnMap.put(ParameterConstant.RETURN_CODE, 1);
            returnMap.put(ParameterConstant.RETURN_MSG, "登陆状态过期，请重新登陆");
            return returnMap;
        }
        Manager manager = managerMapper.selectByAccount(account);
        if(!manager.getPassword().equals(MD5Util.MD5Encode(oldPassword))){
            returnMap.put(ParameterConstant.RETURN_CODE, 1);
            returnMap.put(ParameterConstant.RETURN_MSG, "原密码错误");
            return returnMap;
        }
        manager.setPassword(MD5Util.MD5Encode(password));
        managerMapper.updateByPrimaryKey(manager);
        returnMap.put(ParameterConstant.RETURN_CODE, 0);
        returnMap.put(ParameterConstant.RETURN_MSG, "修改成功");
        return returnMap;
    }
}
