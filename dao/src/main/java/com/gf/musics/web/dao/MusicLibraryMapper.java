package com.gf.musics.web.dao;

import com.gf.musics.web.model.MusicLibrary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component(value = "musicLibraryMapper")
public interface MusicLibraryMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(MusicLibrary record);

    int insertSelective(MusicLibrary record);

    MusicLibrary selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(MusicLibrary record);

    int updateByPrimaryKey(MusicLibrary record);

    List<MusicLibrary>selectByPage(Map map);

    int selectByCount(Map map);

    List<MusicLibrary> selectMusiclibraryByNewApi(Map map);

    List<MusicLibrary> selectRecommendMusiclibraryByApi(Map map);
}