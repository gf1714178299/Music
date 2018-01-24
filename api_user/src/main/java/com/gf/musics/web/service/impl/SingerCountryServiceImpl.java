package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.SingerCountryMapper;
import com.gf.musics.web.model.SingerCountry;
import com.gf.musics.web.service.SingerCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "singerCountryService")
public class SingerCountryServiceImpl implements SingerCountryService{
    @Autowired
    private SingerCountryMapper singerCountryMapper;

    /**
     * 地区查询
     * @param map
     * @return
     */
    @Override
    public Map selectByPageApi(Map map) {
        List<SingerCountry> singerCountryList = singerCountryMapper.selectByPageApi(map);
        Map dataMap = new HashMap();
        dataMap.put("singerCountryList",singerCountryList);
        return ResponseConstant.getSuccessResult(dataMap);
    }
}
