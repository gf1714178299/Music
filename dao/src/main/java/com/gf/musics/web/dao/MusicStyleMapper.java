package com.gf.musics.web.dao;

import com.gf.musics.web.model.MusicStyle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "musicStyleMapper")
public interface MusicStyleMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(MusicStyle record);

    int insertSelective(MusicStyle record);

    MusicStyle selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(MusicStyle record);

    int updateByPrimaryKey(MusicStyle record);

    List<MusicStyle>selectByPageApi(Map map);
}