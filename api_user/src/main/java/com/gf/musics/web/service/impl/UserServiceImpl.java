package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.MusicLibraryMapper;
import com.gf.musics.web.dao.UserLikeMusicMapper;
import com.gf.musics.web.dao.UserMapper;
import com.gf.musics.web.model.MusicLibrary;
import com.gf.musics.web.model.User;
import com.gf.musics.web.model.UserLikeMusic;
import com.gf.musics.web.service.UserService;
import com.gf.musics.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MusicLibraryMapper musicLibraryMapper;
    @Autowired
    private UserLikeMusicMapper userLikeMusicMapper;

    @Override
    public Map getUserData(Map map) {
        Map returnMap = new HashMap();
        String account = String.valueOf(map.get(APIConstant.USER_ACCOUNT));
        User userData = userMapper.selectByAccount(account);
        returnMap.put("avartr",userData.getUserAvartr());
        returnMap.put("name",userData.getUserName());
        returnMap.put("gender",userData.getUserGender());
        returnMap.put("style",userData.getUserStyle());
        return ResponseConstant.getSuccessResult(returnMap);
    }

    @Override
    public Map userLogin(Map map) {
        Map returnMap = new HashMap();
        String userAccount = String.valueOf(map.get(APIConstant.USER_ACCOUNT));
        if (StringUtil.isEmpty(userAccount)){
            returnMap = ResponseConstant.getOneResponseMsg("登录参数为空");
            return returnMap;
        }
        User userData = userMapper.selectByAccount(userAccount);
        if (null == userData){
            User user = new User();
            user.setUserAccount(userAccount);
            userMapper.insertSelective(user);
        }else {
            String avartr = String.valueOf(map.get(APIConstant.USER_AVARTR));
            String name = String.valueOf(map.get(APIConstant.USER_NAME));
            String genderString = String.valueOf(map.get(APIConstant.USER_GENDER));
            Integer gender = Integer.valueOf(genderString);
            String style = String.valueOf(map.get(APIConstant.USER_STYLE));
            userData.setUserAvartr(avartr);
            userData.setUserName(name);
            userData.setUserGender(gender);
            userData.setUserStyle(style);
            userMapper.updateByPrimaryKeySelective(userData);
        }
        returnMap.put("data",null);
        return ResponseConstant.getSuccessResult(returnMap);
    }

    @Override
    public Map userLikeMusic(Map map) {
        List<UserLikeMusic> userLikeMusicList = userLikeMusicMapper.selectByAccount(map);
        List<Object> musicLibraryLists = new ArrayList<Object>();
        for(UserLikeMusic userLikeMusic : userLikeMusicList){
            MusicLibrary musicLibrary = musicLibraryMapper.selectByPrimaryKey(userLikeMusic.getMusicId());
            Map remap = new HashMap();
            remap.put(APIConstant.PK_ID, musicLibrary.getPkId());
            remap.put(APIConstant.MUSIC_NAME, musicLibrary.getMusicName());
            remap.put(APIConstant.SINGER_ID, musicLibrary.getSingerId());
            remap.put(APIConstant.SINGER_NAME, musicLibrary.getSingerName());
            remap.put(APIConstant.MUSIC_IMG_URL, musicLibrary.getMusicImgUrl());
            remap.put(APIConstant.MUSIC_URL, musicLibrary.getMusicUrl());
            remap.put(APIConstant.MUSIC_MV_URL, musicLibrary.getMusicMvUrl());
            remap.put(APIConstant.MUSIC_GECI, musicLibrary.getMusicGeci());
            remap.put(APIConstant.MUSIC_DURATION, musicLibrary.getMusicDuration());
            remap.put(APIConstant.MUSIC_SIZE, musicLibrary.getMusicSize());
            remap.put(APIConstant.CLICKS, musicLibrary.getClicks());
            remap.put(APIConstant.ALBUM, musicLibrary.getAlbum());
            remap.put(APIConstant.STYLE_ID, musicLibrary.getStyleId());
            remap.put(APIConstant.PUBLISH_DATE, musicLibrary.getPublishDate());
            musicLibraryLists.add(remap);
        }
        Map dataMap = new HashMap();
        dataMap.put("musicLibraryLists",musicLibraryLists);
        return ResponseConstant.getSuccessResult(dataMap);
    }


}
