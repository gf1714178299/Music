package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.ParameterConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.PermissionAssignmentMapper;
import com.gf.musics.web.dao.ResourceMapper;
import com.gf.musics.web.dao.RoleMapper;
import com.gf.musics.web.model.PermissionAssignment;
import com.gf.musics.web.model.Resource;
import com.gf.musics.web.model.Role;
import com.gf.musics.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Lokey on 2017/7/7.
 */
@Component(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private PermissionAssignmentMapper permissionAssignmentMapper;
    @Override
    public Map getRoleList(Map map) {
        Map returnMap = new HashMap();
        List<Role> roleList = roleMapper.selectByPage(map); //获取当前页面内容
        int totalNum = roleMapper.selectByCount(map);      //获取数据总数
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        returnMap.put(ParameterConstant.RETURN_DATA,roleList);
        returnMap.put(ParameterConstant.DATA_ITOTALDISPLAYRECORDS,totalNum);
        returnMap.put(ParameterConstant.DATA_ITOTALRECORDS,totalNum);
        return returnMap;
    }

    @Override
    public Map changeStatus(int id) {
        Map returnMap = new HashMap();
        Role role = roleMapper.selectByPrimaryKey(id);
        if(role==null){
            returnMap.put(ParameterConstant.RETURN_CODE, 0);
            returnMap.put(ParameterConstant.RETURN_MSG, "数据不存在");
            return returnMap;
        }
        if (role.getIsEnabled() == 0) {
            role.setIsEnabled(1);
        } else {
            role.setIsEnabled(0);
        }
        roleMapper.updateByPrimaryKeySelective(role);
        returnMap.put(ParameterConstant.RETURN_CODE, 0);
        returnMap.put(ParameterConstant.RETURN_MSG, "禁用/启用成功");
        return returnMap;
    }

    @Override
    public Map addRole(Role role) {
        Map<String, Object> returnMap = new HashMap();
        role.setDeleteFlag(1);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        roleMapper.insertSelective(role);
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        return returnMap;
    }

    @Override
    public Map editRole(Role role) {
        Map<String, Object> returnMap = new HashMap();
        role.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKeySelective(role);
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        return returnMap;
    }

    @Override
    public Map getRoleInfo(int id) {
        Map<String, Object> returnMap = new HashMap();
        Role role = roleMapper.selectByPrimaryKey(id);
        if(role==null){
            returnMap = ResponseConstant.getResponsecodeDesc(111);
            return returnMap;
        }
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        returnMap.put("pkId", role.getPkId());
        returnMap.put("name", role.getName());
        returnMap.put("isEnabled", role.getIsEnabled());
        return returnMap;
    }

    @Override
    public Map deleteRoleList(Map map) {
        Map returnMap = new HashMap();
        String ids = String.valueOf(map.get("ids"));
        String[] roleid = ids.split(",");
        for (int i = 0; i<roleid.length;i++){
            Role role = roleMapper.selectByPrimaryKey(Integer.valueOf(roleid[i]));
            if(role!=null) {
                role.setDeleteFlag(2);
                roleMapper.updateByPrimaryKey(role);
            }
        }
        returnMap.put(ParameterConstant.RETURN_CODE,0);
        returnMap.put(ParameterConstant.RETURN_MSG,"删除成功");
        return returnMap;
    }

    @Override
    public Map deleteRole(int id) {
        Map returnMap = new HashMap();
        Role role = roleMapper.selectByPrimaryKey(id);
        if(role!=null) {
            role.setDeleteFlag(2);
            roleMapper.updateByPrimaryKey(role);
        }
        returnMap.put(ParameterConstant.RETURN_CODE,0);
        returnMap.put(ParameterConstant.RETURN_MSG,"删除成功");
        return returnMap;
    }

    @Override
    public Map getPermissionPage() {
        Map returnMap = new HashMap();
        List<Map> resultMapList = new ArrayList<Map>(); //记录结果信息
        //获取一级模块
        Map fatherMap = new HashMap();
        fatherMap.put(ParameterConstant.P_FATHERID,-1);
        List<Resource> resourceList = resourceMapper.selectByPage(fatherMap);
        //获取二级权限
        for(Resource resource:resourceList){
            Map resultMap = new HashMap();
            List<Map> secondResultMapList = new ArrayList<Map>();
            resultMap.put("name",resource.getName());
            resultMap.put("pkId",resource.getPkId());
            Map secondMap = new HashMap();
            secondMap.put(ParameterConstant.P_FATHERID,resource.getPkId());
            List<Resource> secondResourceList = resourceMapper.selectByPage(secondMap);
            //获取按钮权限
            for(Resource secondResource:secondResourceList){
                Map secondResultMap = new HashMap();
                secondResultMap.put("name",secondResource.getName());
                secondResultMap.put("pkId",secondResource.getPkId());
                Map buttonMap = new HashMap();
                buttonMap.put(ParameterConstant.P_FATHERID,secondResource.getPkId());
                List<Resource> buttonResourceList = resourceMapper.selectByPage(buttonMap);
                secondResultMap.put("sons",buttonResourceList);
                secondResultMapList.add(secondResultMap);
            }
            resultMap.put("sons",secondResultMapList);
            resultMapList.add(resultMap);
        }
        returnMap.put("permissionList",resultMapList);
        return returnMap;
    }

    @Override
    public Map getPermission(int id) {
        Map returnMap = new HashMap();
        Map reqMap = new HashMap();
        reqMap.put(ParameterConstant.P_ROLEID,id);
        List<PermissionAssignment> permissionAssignmentList = permissionAssignmentMapper.selectByPage(reqMap);
        returnMap.put(ParameterConstant.RETURN_DATA,permissionAssignmentList);
        returnMap.put(ParameterConstant.RETURN_CODE,0);
        returnMap.put(ParameterConstant.RETURN_MSG,"获取权限列表");
        return returnMap;
    }

    @Override
    public Map changePermission(Map map) {
        Map returnMap = new HashMap();
        returnMap.put(ParameterConstant.RETURN_CODE, 1);
        returnMap.put(ParameterConstant.RETURN_MSG, "权限修改失败");
        Integer  roleId = (Integer)map.get("id");
        if (roleId!=-1) {
            Map reqMap = new HashMap();
            reqMap.put(ParameterConstant.P_ROLEID, roleId);
            List<PermissionAssignment> permissionAssignmentList = permissionAssignmentMapper.selectByPage(reqMap);
            List<String> permissionAssignments = new ArrayList<String>();
            for(PermissionAssignment permissionAssignment:permissionAssignmentList){
                permissionAssignments.add(String.valueOf(permissionAssignment.getPkId()));
            }
            String idString = String.valueOf(map.get("ids"));
            String[] ids = idString.split(",");
            List<String> postlist = new ArrayList<String>();
            for(int i=0;i<ids.length;i++){
                postlist.add(ids[i]);
            }
            for (String permissionAssignmentId:permissionAssignments){
                if (!postlist.contains(permissionAssignmentId)) {//删除
                    permissionAssignmentMapper.deleteByPrimaryKey(Integer.valueOf(permissionAssignmentId));
                }
            }
            for(String addId : postlist){
                if (!permissionAssignments.contains(addId)) {//新增
                    PermissionAssignment permissionAssignment = new PermissionAssignment();
                    permissionAssignment.setRoleId(roleId);
                    permissionAssignment.setResourceId(Integer.valueOf(addId));
                    permissionAssignment.setDeleteFlag(0);
                    permissionAssignment.setIsEnabled(0);
                    permissionAssignment.setUpdateTime(new Date());
                    permissionAssignment.setCreateTime(new Date());
                    permissionAssignmentMapper.insert(permissionAssignment);
                }
            }
            returnMap.put(ParameterConstant.RETURN_CODE, 0);
            returnMap.put(ParameterConstant.RETURN_MSG, "权限修改成功");
        }
        return returnMap;
    }
}
