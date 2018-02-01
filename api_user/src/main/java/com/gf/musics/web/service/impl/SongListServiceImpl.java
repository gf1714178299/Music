package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.SongListMapper;
import com.gf.musics.web.model.SongList;
import com.gf.musics.web.service.SongListService;
import com.gf.musics.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "songListService")
public class SongListServiceImpl implements SongListService{
    @Autowired
    private SongListMapper songListMapper;
    @Override
    public Map songListService(Map map) {
        Map returnMap = new HashMap();
        List<Object> musicLibraryLists = new ArrayList<Object>();
        if (!StringUtil.isEmpty(String.valueOf(map.get(APIConstant.EMOTION)))){
            List<SongList> songLists = songListMapper.selectByPage(map);
            for (SongList songList:songLists){
                Map dataMap = new HashMap();
                dataMap.put("pkId",songList.getPkId());
                dataMap.put("emotionId",songList.getEmotionId());
                dataMap.put("songListImg",songList.getSongListImg());
                dataMap.put("description",songList.getDescription());
                musicLibraryLists.add(dataMap);
            }
            int count = songListMapper.selectByCount(map);
            if (count < 6){
                Map dataMap = new HashMap();
                int i = 6 - count;
                dataMap.put("i",i);
                dataMap.put("emotion",map.get(APIConstant.EMOTION));
                List<SongList> songList = songListMapper.selectByUpdateTime(dataMap);
                for (SongList songListData : songList){
                    Map resultMap = new HashMap();
                    resultMap.put("pkId",songListData.getPkId());
                    resultMap.put("emotionId",songListData.getEmotionId());
                    resultMap.put("songListImg",songListData.getSongListImg());
                    resultMap.put("description",songListData.getDescription());
                    musicLibraryLists.add(resultMap);
                }
            }
            returnMap.put("musicLibraryLists",musicLibraryLists);
            return ResponseConstant.getSuccessResult(returnMap);
        }else {
            Map dataMap = new HashMap();
            dataMap.put("i",6);
            List<SongList> songList = songListMapper.selectByUpdateTime(dataMap);
            for (SongList songListData:songList){
                Map resultMap = new HashMap();
                resultMap.put("pkId",songListData.getPkId());
                resultMap.put("emotionId",songListData.getEmotionId());
                resultMap.put("songListImg",songListData.getSongListImg());
                resultMap.put("description",songListData.getDescription());
                musicLibraryLists.add(resultMap);
            }
            returnMap.put("musicLibraryLists",musicLibraryLists);
            return ResponseConstant.getSuccessResult(returnMap);
        }
    }
}
