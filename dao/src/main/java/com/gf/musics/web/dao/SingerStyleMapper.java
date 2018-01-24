package com.gf.musics.web.dao;

import com.gf.musics.web.model.SingerStyle;
import org.springframework.stereotype.Component;

@Component(value = "singerStyleMapper")
public interface SingerStyleMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(SingerStyle record);

    int insertSelective(SingerStyle record);

    SingerStyle selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(SingerStyle record);

    int updateByPrimaryKey(SingerStyle record);
}