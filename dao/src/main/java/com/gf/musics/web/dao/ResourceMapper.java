package com.gf.musics.web.dao;

import com.gf.musics.web.model.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component(value = "resourceMapper")
public interface ResourceMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> selectByPage(Map map);
}