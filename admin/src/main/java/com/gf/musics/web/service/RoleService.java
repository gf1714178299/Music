package com.gf.musics.web.service;

import com.gf.musics.web.model.Role;

import java.util.Map;

/**
 * Created by lokey on 2017/7/7.
 */
public interface RoleService {

    Map getRoleList(Map map);

    Map changeStatus(int id);

    Map addRole(Role role);

    Map editRole(Role role);

    Map getRoleInfo(int id);

    Map deleteRoleList(Map map);

    Map deleteRole(int id);
    //以下为权限管理
    Map getPermissionPage();

    Map getPermission(int id);

    Map changePermission(Map map);
}
