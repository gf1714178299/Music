package com.gf.musics.web.dao;

import com.gf.musics.web.model.MusicLibrary;

import java.util.List;
import java.util.Map;

public interface MusicLibraryMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(MusicLibrary record);

    int insertSelective(MusicLibrary record);

    MusicLibrary selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(MusicLibrary record);

    int updateByPrimaryKey(MusicLibrary record);

    List<MusicLibrary> selectMusiclibraryByNewApi(Map map);

    List<MusicLibrary> selectRecommendMusiclibraryByApi(Map map);

    List<MusicLibrary> selectByPage(Map map);

    int selectByCount(Map map);

    int selectByAlbumCount(String album);
}