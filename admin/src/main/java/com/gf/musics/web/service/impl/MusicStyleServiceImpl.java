package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.ParameterConstant;
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
    @Override
    public Map selectByPage(Map map) {
        Map returnMap = new HashMap();
        List<MusicStyle>musicStyleList = musicStyleMapper.selectByPageApi(map);
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        returnMap.put(ParameterConstant.RETURN_DATA,musicStyleList);
        return returnMap;
    }
}
