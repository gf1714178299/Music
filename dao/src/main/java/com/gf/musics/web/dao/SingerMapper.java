package com.gf.musics.web.dao;

import com.gf.musics.web.model.MusicLibrary;
import com.gf.musics.web.model.Singer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "singerMapper")
public interface SingerMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(Singer record);

    int insertSelective(Singer record);

    Singer selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(Singer record);

    int updateByPrimaryKeyWithBLOBs(Singer record);

    int updateByPrimaryKey(Singer record);

    List<Singer> getBlurrySingerNameList(Map map);
}