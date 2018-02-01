package com.gf.musics.web.dao;

import com.gf.musics.web.model.SongListMusic;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(value = "songListMusicMapper")
public interface SongListMusicMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(SongListMusic record);

    int insertSelective(SongListMusic record);

    SongListMusic selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(SongListMusic record);

    int updateByPrimaryKey(SongListMusic record);

    int selectByCount(Map map);
}