package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.ResponseConstant;
//import com.gf.musics.web.dao.SongListMusicMapper;
//import com.gf.musics.web.dao.UserSongListMapper;
import com.gf.musics.web.model.UserSongList;
import com.gf.musics.web.service.UserSongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "userSongListService")
public class UserSongListServiceImpl implements UserSongListService {
    @Override
    public Map getUserSongListMusic(Map map) {
        return null;
    }
//    @Autowired
//    private UserSongListMapper userSongListMapper;
//    @Autowired
//    private SongListMusicMapper songListMusicMapper;
//
//    @Override
//    public Map getUserSongListMusic(Map map) {
//        Map returnMap = new HashMap();
//        List<UserSongList> userSongLists = userSongListMapper.selectByPage(map);
//        for(UserSongList userSongList:userSongLists){
//            Map dataMap = new HashMap();
//            dataMap.put(APIConstant.SONGLIST_MUSIC_ID,userSongList.getSongListId());
//            int count = songListMusicMapper.selectByCount(dataMap);
//            userSongList.setCount(count);
//        }
//        returnMap.put("userSongLists",userSongLists);
//        return ResponseConstant.getSuccessResult(returnMap);
//    }
}
