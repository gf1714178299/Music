package com.gf.musics.web.dao;

import com.gf.musics.web.model.Album;
import org.springframework.stereotype.Component;

@Component(value = "albumMapper")
public interface AlbumMapper {
    int deleteByPrimaryKey(Integer pkid);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(Integer pkid);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);
}