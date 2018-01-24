package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.MusicLibraryMapper;
import com.gf.musics.web.model.MusicLibrary;
import com.gf.musics.web.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.split;

@Component(value = "recommendService")
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private MusicLibraryMapper musicLibraryMapper;
    @Override
    public Map getRecommendMusic(Map map) {
        String strs = String.valueOf(map.get(APIConstant.STYLE_ID));
        String [] str =  strs.split(",");
        map.put(APIConstant.STYLE_ID,str);
        System.out.println(String.valueOf(map.get(APIConstant.STYLE_ID)));
        List<MusicLibrary> musicLibraryList = musicLibraryMapper.selectRecommendMusiclibraryByApi(map);
        Map dataMap = new HashMap();
        dataMap.put("musicLibraryLists",musicLibraryList);
        return ResponseConstant.getSuccessResult(dataMap);
    }
}
