package com.gf.musics.web.dao;

import com.gf.musics.web.model.UserSongList;

import java.util.List;
import java.util.Map;

public interface UserSongListMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(UserSongList record);

    int insertSelective(UserSongList record);

    UserSongList selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(UserSongList record);

    int updateByPrimaryKey(UserSongList record);

    List<UserSongList>selectByPage(Map map);

    UserSongList selectByUserIdAndSongListId(Map map);
}