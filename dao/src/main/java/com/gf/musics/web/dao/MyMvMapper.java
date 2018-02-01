package com.gf.musics.web.dao;

import com.gf.musics.web.model.MyMv;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component(value = "myMvMapper")
public interface MyMvMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(MyMv record);

    int insertSelective(MyMv record);

    MyMv selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(MyMv record);

    int updateByPrimaryKey(MyMv record);

    List<MyMv> selectByMyMv(Map map);
}