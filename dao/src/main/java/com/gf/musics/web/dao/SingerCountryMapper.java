package com.gf.musics.web.dao;

import com.gf.musics.web.model.SingerCountry;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "singerCountryMapper")
public interface SingerCountryMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(SingerCountry record);

    int insertSelective(SingerCountry record);

    SingerCountry selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(SingerCountry record);

    int updateByPrimaryKey(SingerCountry record);

    List<SingerCountry> selectByPageApi(Map map);
}