package com.gf.musics.web.dao;

import com.gf.musics.web.model.User;
import org.springframework.stereotype.Component;

@Component(value = "userMapper")
public interface UserMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByAccount(String account);

    User selectByQq(String qq);

    User selectByWechat(String wx);

    User selectByMailbox(String mail);
}