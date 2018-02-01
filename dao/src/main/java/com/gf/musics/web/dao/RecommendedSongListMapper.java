package com.gf.musics.web.dao;

import com.gf.musics.web.model.RecommendedSongList;

public interface RecommendedSongListMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(RecommendedSongList record);

    int insertSelective(RecommendedSongList record);

    RecommendedSongList selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(RecommendedSongList record);

    int updateByPrimaryKey(RecommendedSongList record);
}