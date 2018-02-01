package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.SongListMusicMapper;
import com.gf.musics.web.dao.UserSongListMapper;
import com.gf.musics.web.model.UserSongList;
import com.gf.musics.web.service.UserSongListService;
import com.gf.musics.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component(value = "userSongListService")
public class UserSongListServiceImpl implements UserSongListService{
    @Autowired
    private UserSongListMapper userSongListMapper;
    @Autowired
    private SongListMusicMapper songListMusicMapper ;
    @Override
    public Map getUserSongListMusic(Map map) {
        Map returnMap = new HashMap();
        List<Object> userSongListMusic = new ArrayList<Object>();
        List<UserSongList> userSongLists = userSongListMapper.selectByPage(map);
        for (UserSongList userSongList:userSongLists){
            Map requestMap = new HashMap();
            Map dataMap = new HashMap();
            dataMap.put(APIConstant.USER_SONG_LIST,userSongList.getSongListId());
            int count = new Integer("0");
            count = songListMusicMapper.selectByCount(dataMap);
            userSongList.setStatistics(count);
            requestMap.put("songListId",userSongList.getSongListId());
            requestMap.put("songListName",userSongList.getSongListName());
            requestMap.put("statistics",userSongList.getStatistics());
            userSongListMusic.add(requestMap);
        }
        returnMap.put("userSongListMusic",userSongListMusic);
        return ResponseConstant.getSuccessResult(returnMap);
    }

    @Override
    public Map UserInsertSongList(Map map) {
        Map returnMap = new HashMap();
        UserSongList userSongList = new UserSongList();
        String userId = String.valueOf(map.get(APIConstant.USER_ID));
        String songListNameString = String.valueOf(map.get(APIConstant.SONG_LIST_NAME));
        if (!StringUtil.isEmpty(userId)&&!StringUtil.isEmpty(songListNameString)){
            userSongList.setUserId(userId);
            userSongList.setSongListName(songListNameString);
            userSongList.setDeleteFlag(0);
            userSongList.setCreateTime(new Date());
            userSongList.setUpdateTime(new Date());
            userSongListMapper.insertSelective(userSongList);
            userSongList.setPkId(userSongList.getPkId());
            userSongList.setSongListId(userSongList.getPkId());
            userSongListMapper.updateByPrimaryKeySelective(userSongList);
        }
        returnMap.put(APIConstant.CODE, 0);
        returnMap.put(APIConstant.MSG, "返回成功");
        return returnMap;
    }

    @Override
    public Map deleteUserSongList(Map map) {
        Map returnMap = new HashMap();
        String userId = String.valueOf(map.get(APIConstant.USER_ID));
        String songListIdString = String.valueOf(map.get(APIConstant.SONG_LIST_ID));
        UserSongList userSongList = new UserSongList();
        if (!StringUtil.isEmpty(userId)&&!StringUtil.isEmpty(songListIdString)){
            userSongList = userSongListMapper.selectByUserIdAndSongListId(map);
            if (userSongList != null){
                userSongList.setPkId(userSongList.getPkId());
                userSongList.setDeleteFlag(1);
                userSongListMapper.updateByPrimaryKeySelective(userSongList);
            }else {
                returnMap.put(APIConstant.CODE, 1);
                returnMap.put(APIConstant.MSG, "该歌单不存在");
                return returnMap;
            }
        }else {
            returnMap.put(APIConstant.CODE, 1);
            returnMap.put(APIConstant.MSG, "所给参数不全");
            return returnMap;
        }

        returnMap.put(APIConstant.CODE, 0);
        returnMap.put(APIConstant.MSG, "返回成功");
        return returnMap;
    }


}
