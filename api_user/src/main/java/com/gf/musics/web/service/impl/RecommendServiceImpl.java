package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.MusicLibraryMapper;
import com.gf.musics.web.dao.RecommendedSongListMapper;
import com.gf.musics.web.model.MusicLibrary;
import com.gf.musics.web.model.RecommendedSongList;
import com.gf.musics.web.service.RecommendService;
import com.gf.musics.web.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.lang.StringUtils.split;

@Component(value = "recommendService")
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private MusicLibraryMapper musicLibraryMapper;
    @Autowired
    private RecommendedSongListMapper recommendedSongListMapper;
    @Override
    public Map getRecommendMusic(Map map) {
        List<Object> recommendMusic = new ArrayList<Object>();
        String strs = String.valueOf(map.get(APIConstant.STYLE_ID));
        String [] str =  strs.split(",");
        map.put(APIConstant.STYLE_ID,str);
        System.out.println(String.valueOf(map.get(APIConstant.STYLE_ID)));
        List<MusicLibrary> musicLibraryList = musicLibraryMapper.selectRecommendMusiclibraryByApi(map);
        for (MusicLibrary musicLibrary : musicLibraryList){
            Map requestMap = new HashMap();
            requestMap.put("pkId",musicLibrary.getPkId());
            requestMap.put("musicName",musicLibrary.getMusicName());
            requestMap.put("singerId",musicLibrary.getSingerId());
            requestMap.put("singerName",musicLibrary.getSingerName());
            requestMap.put("musicImgUrl",musicLibrary.getMusicImgUrl());
            requestMap.put("musicDuration",musicLibrary.getMusicDuration());
            requestMap.put("musicSize",musicLibrary.getMusicSize());
            requestMap.put("cClicks",musicLibrary.getClicks());
            requestMap.put("album",musicLibrary.getAlbum());
            requestMap.put("quality",musicLibrary.getQuality());
            requestMap.put("publishDate",DateUtils.parseYYYY(musicLibrary.getPublishDate()));
            recommendMusic.add(requestMap);
        }
        Map dataMap = new HashMap();
        dataMap.put("recommendMusic",recommendMusic);
        return ResponseConstant.getSuccessResult(dataMap);
    }

}
