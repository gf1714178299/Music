package com.gf.musics.web.dao;

import com.gf.musics.web.model.MyAlbum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "myAlbumMapper")
public interface MyAlbumMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(MyAlbum record);

    int insertSelective(MyAlbum record);

    MyAlbum selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(MyAlbum record);

    int updateByPrimaryKey(MyAlbum record);

    List<MyAlbum> selectByMyAlbum(Map map);
}