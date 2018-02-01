package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.ParameterConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.AlbumMapper;
import com.gf.musics.web.dao.EmotionMapper;
import com.gf.musics.web.dao.MusicLibraryMapper;
import com.gf.musics.web.model.MusicLibrary;
import com.gf.musics.web.service.MusiclibraryService;
import com.gf.musics.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(value = "musiclibraryService")
public class MusiclibraryServiceImpl implements MusiclibraryService{
    @Autowired
    private MusicLibraryMapper musicLibraryMapper;
    @Autowired
    private EmotionMapper emotionMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Override
    public Map getMusiclibraryList(Map map) {
        Map returnMap = new HashMap();
        List<Object> musicLibraryLists = new ArrayList<Object>();
        List<MusicLibrary> musicLibraryList = musicLibraryMapper.selectByPage(map);
        int totalNum = musicLibraryMapper.selectByCount(map);      //获取数据总数

        for (MusicLibrary musicLibraryList1 : musicLibraryList) {
            Map remap = new HashMap();
            remap.put(ParameterConstant.PK_ID, musicLibraryList1.getPkId());
            remap.put(ParameterConstant.MUSIC_NAME, musicLibraryList1.getMusicName());
            remap.put(ParameterConstant.SINGER_NAME, musicLibraryList1.getSingerName());
            remap.put(ParameterConstant.MUSIC_IMG_URL, musicLibraryList1.getMusicImgUrl());
            remap.put(ParameterConstant.ALBUM, musicLibraryList1.getAlbum());
            remap.put(ParameterConstant.STYLE_ID, musicLibraryList1.getStyleId());
            remap.put(ParameterConstant.CLICKS, musicLibraryList1.getClicks());
            if (!StringUtil.isEmpty(musicLibraryList1.getAlbum())){
                remap.put(ParameterConstant.ALBUM_NAME,albumMapper.selectByPrimaryKey(Integer.valueOf(musicLibraryList1.getAlbum())).getAlbumName());
            }else {
                remap.put(ParameterConstant.ALBUM_NAME,"");
            }
            if (null != musicLibraryList1.getStyleId()){
                remap.put(ParameterConstant.SINGER_STYLE_NAME,emotionMapper.selectByPrimaryKey(Integer.valueOf(musicLibraryList1.getStyleId())).getEmotion());
            }else {
                remap.put(ParameterConstant.SINGER_STYLE_NAME,"");
            }
            if (musicLibraryList1.getPublishDate() != null) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date=musicLibraryList1.getPublishDate();
                String str=sdf.format(date);
                remap.put(ParameterConstant.PUBLISH_DATE,str);
            } else {
                remap.put(ParameterConstant.PUBLISH_DATE, "");
            }
            musicLibraryLists.add(remap);
        }
        returnMap = ResponseConstant.getResponsecodeDesc(0);
        returnMap.put(ParameterConstant.RETURN_DATA,musicLibraryLists);
        returnMap.put(ParameterConstant.DATA_ITOTALDISPLAYRECORDS,totalNum);
        returnMap.put(ParameterConstant.DATA_ITOTALRECORDS,totalNum);
        return returnMap;
    }
}