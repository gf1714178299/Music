package com.gf.musics.web.service;



import com.gf.musics.web.model.Manager;

import java.util.Map;


public interface ManagerService {

    Map ManagerLogin(Map map);

    Map ChangePassword(String adminId, String oldPassword, String password);

    Map GetMenu(Integer roleId);

    Map GetButton(Map map);
    //获取角色信息
    Map getManagerSavePageInfo(int id);

    Map getManagerList(Map map);

    Map changeStatus(int id);

    Map resetPassword(int id);

    Map addManager(Manager manager);

    Map editManager(Manager manager);

    Map deleteManagerList(Map map);

    Map deleteManager(int id);

}
