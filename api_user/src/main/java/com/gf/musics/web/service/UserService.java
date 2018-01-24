package com.gf.musics.web.service;

import com.gf.musics.web.model.User;

import java.util.Map;

public interface UserService {


    Map getUserData(Map map);

    Map userLogin(Map map);

    Map userLikeMusic(Map map);
}
