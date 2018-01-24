package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.dao.RoleMapper;
import com.gf.musics.web.model.Role;
import com.gf.musics.web.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component(value = "testService")
public class TestServiceImpl implements TestService{
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Map selectBys(String s) {
        Map map = new HashMap();
        Role role = new Role();
        role.setName("小明");
        role.setIsEnabled(1);
        role.setDeleteFlag(1);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        map.put(APIConstant.PKID,s);
        roleMapper.insertSelective(role);
        return map;
    }
}
