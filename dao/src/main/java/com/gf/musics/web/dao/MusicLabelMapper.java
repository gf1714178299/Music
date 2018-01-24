package com.gf.musics.web.dao;

import com.gf.musics.web.model.MusicLabel;
import com.gf.musics.web.model.MusicLibrary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "musicLavelMapper")
public interface MusicLabelMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(MusicLabel record);

    int insertSelective(MusicLabel record);

    MusicLabel selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(MusicLabel record);

    int updateByPrimaryKey(MusicLabel record);
}