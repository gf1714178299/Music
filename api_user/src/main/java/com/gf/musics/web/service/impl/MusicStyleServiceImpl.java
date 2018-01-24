package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.MusicStyleMapper;
import com.gf.musics.web.model.MusicStyle;
import com.gf.musics.web.service.MusicStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "musicStyleService")
public class MusicStyleServiceImpl implements MusicStyleService{
    @Autowired
    private MusicStyleMapper musicStyleMapper;

    /**
     * 风格查询
     * @param map
     * @return
     */
    @Override
    public Map selectByPageApi(Map map) {
        List<MusicStyle> musicStyleList = musicStyleMapper.selectByPageApi(map);
        Map dataMap = new HashMap();
        dataMap.put("musicStyleList",musicStyleList);
        return ResponseConstant.getSuccessResult(dataMap);
    }
}
