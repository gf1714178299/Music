package com.gf.musics.web.dao;

import com.gf.musics.web.model.SongList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "songListMapper")
public interface SongListMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(SongList record);

    int insertSelective(SongList record);

    SongList selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(SongList record);

    int updateByPrimaryKey(SongList record);

    List<SongList>selectByPage(Map map);

    int selectByCount(Map map);

    List<SongList> selectByUpdateTime(Map map);
}