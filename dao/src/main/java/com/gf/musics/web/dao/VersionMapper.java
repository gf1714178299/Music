package com.gf.musics.web.dao;

import com.gf.musics.web.model.Version;
import org.springframework.stereotype.Component;

@Component(value = "versionMapper")
public interface VersionMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(Version record);

    int insertSelective(Version record);

    Version selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(Version record);

    int updateByPrimaryKey(Version record);
}