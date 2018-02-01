package com.gf.musics.web.dao;

import com.gf.musics.web.model.MySinger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component(value = "mySingerMapper")
public interface MySingerMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(MySinger record);

    int insertSelective(MySinger record);

    MySinger selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(MySinger record);

    int updateByPrimaryKey(MySinger record);

    List<MySinger>selectByMySinger(Integer userId);
}