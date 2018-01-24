package com.gf.musics.web.dao;

import com.gf.musics.web.model.Manager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component(value = "managerMapper")
public interface ManagerMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    Manager selectByAccount(String account);

    List<Manager> selectByPage(Map map);

    int selectByCount(Map map);
}