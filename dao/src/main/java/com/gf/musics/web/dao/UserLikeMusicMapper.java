package com.gf.musics.web.dao;

import com.gf.musics.web.model.UserLikeMusic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "userLikeMusicMapper")
public interface UserLikeMusicMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(UserLikeMusic record);

    int insertSelective(UserLikeMusic record);

    UserLikeMusic selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(UserLikeMusic record);

    int updateByPrimaryKey(UserLikeMusic record);

    List<UserLikeMusic>selectByAccount(Map map);
}