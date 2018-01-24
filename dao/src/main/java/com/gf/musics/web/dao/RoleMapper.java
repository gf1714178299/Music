package com.gf.musics.web.dao;

import com.gf.musics.web.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component(value = "roleMapper")
public interface RoleMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByPage(Map map);

    int selectByCount(Map map);
}