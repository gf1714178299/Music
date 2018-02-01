package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.*;
import com.gf.musics.web.model.*;
import com.gf.musics.web.service.UserService;
import com.gf.musics.web.util.DateUtils;
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
    @Autowired
    private MyAlbumMapper myAlbumMapper;
    @Autowired
    private MySingerMapper mySingerMapper;
    @Autowired
    private MyMvMapper myMvMapper;
    @Autowired
    private SingerMapper singerMapper;
    @Autowired
    private AlbumMapper albumMapper;

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
            remap.put(APIConstant.MUSIC_DURATION, musicLibrary.getMusicDuration());
            remap.put(APIConstant.MUSIC_SIZE, musicLibrary.getMusicSize());
            remap.put(APIConstant.CLICKS, musicLibrary.getClicks());
            remap.put(APIConstant.ALBUM, musicLibrary.getAlbum());
            remap.put(APIConstant.PUBLISH_DATE, DateUtils.parseYYYY(musicLibrary.getPublishDate()));
            musicLibraryLists.add(remap);
        }
        Map dataMap = new HashMap();
        dataMap.put("musicLibraryLists",musicLibraryLists);
        return ResponseConstant.getSuccessResult(dataMap);
    }

    @Override
    public Map selectMySinger(Map map) {
        String userIdString = String.valueOf(map.get(APIConstant.USER_ID));
        Integer userId = Integer.valueOf(userIdString);
        List<MySinger> mySingers = mySingerMapper.selectByMySinger(userId);
        List<Object> mySingerList = new ArrayList<Object>();
        for (MySinger mySinger:mySingers){
            Map requestMap = new HashMap();
            requestMap.put("singerId",mySinger.getSingerId());
            Integer singId = Integer.valueOf(mySinger.getSingerId());
            Singer singer = singerMapper.selectByPrimaryKey(singId);
            int albumCount  = albumMapper.selectSingerAlbumByCount(String.valueOf(mySinger.getSingerId()));
            int musicCount = musicLibraryMapper.selectByCount(requestMap);
            requestMap.put("singIcon",singer.getSingerImg());
            requestMap.put("albumCount",albumCount);
            requestMap.put("musicCount",musicCount);
            mySingerList.add(requestMap);
        }
        Map dataMap = new HashMap();
        dataMap.put("mySingerList",mySingerList);
        return ResponseConstant.getSuccessResult(dataMap);
    }

    @Override
    public Map selectMyAlbum(Map map) {
        List<MyAlbum> myAlbums = myAlbumMapper.selectByMyAlbum(map);
        List<Object> myAlbumList = new ArrayList<Object>();
        for(MyAlbum myAlbum:myAlbums){
            Map requestMap = new HashMap();
            int albumCount = musicLibraryMapper.selectByAlbumCount(String.valueOf(myAlbum.getAlbumId()));
            Album album = albumMapper.selectByPrimaryKey(myAlbum.getAlbumId());
            Singer singer = singerMapper.selectByPrimaryKey(Integer.valueOf(album.getSingerId()));
            requestMap.put("singerName",singer.getSingerName());
            requestMap.put("albumImg",album.getAlbumImg());
            requestMap.put("albumName",album.getAlbumName());
            requestMap.put("musicCount",albumCount);
            myAlbumList.add(requestMap);
        }
        Map dataMap = new HashMap();
        dataMap.put("myAlbumList",myAlbumList);
        return ResponseConstant.getSuccessResult(dataMap);
    }

    @Override
    public Map selectMyMv(Map map) {
        List<MyMv> myMvs = myMvMapper.selectByMyMv(map);
        Map dataMap = new HashMap();
        dataMap.put("myMvs",myMvs);
        return ResponseConstant.getSuccessResult(dataMap);
    }


}
