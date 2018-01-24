package com.gf.musics.web.service.impl;

import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.constant.ParameterConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.dao.AlbumMapper;
import com.gf.musics.web.dao.MusicLibraryMapper;
import com.gf.musics.web.dao.SingerStyleMapper;
import com.gf.musics.web.model.Album;
import com.gf.musics.web.model.MusicLibrary;
import com.gf.musics.web.service.MusicLibraryService;
import com.gf.musics.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "musicLibraryService")
public class MusicLibraryServiceImpl implements MusicLibraryService{
    @Autowired
    private MusicLibraryMapper musicLibraryMapper;
    @Autowired
    private SingerStyleMapper singerStyleMapper;
    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 歌曲搜索(以点击量排序)
     * @param map
     * @return
     */
    @Override
    public Map selectMusiclibraryByPage(Map map) {
        Map returnMap = new HashMap();
        List<Object> musicLibraryLists = new ArrayList<Object>();
        List<MusicLibrary> musicLibraryList = musicLibraryMapper.selectByPage(map);
        int totalNum = musicLibraryMapper.selectByCount(map);      //获取数据总数
        for (MusicLibrary musicLibraryList1 : musicLibraryList) {
            Map remap = new HashMap();
            remap.put(APIConstant.PK_ID, musicLibraryList1.getPkId());
            remap.put(APIConstant.MUSIC_NAME, musicLibraryList1.getMusicName());
            remap.put(APIConstant.SINGER_NAME, musicLibraryList1.getSingerName());
            remap.put(APIConstant.MUSIC_IMG_URL, musicLibraryList1.getMusicImgUrl());
            remap.put(APIConstant.ALBUM, musicLibraryList1.getAlbum());
            remap.put(APIConstant.STYLE_ID, musicLibraryList1.getStyleId());
            remap.put(APIConstant.CLICKS, musicLibraryList1.getClicks());
            if (!StringUtil.isEmpty(musicLibraryList1.getAlbum())){
                remap.put(APIConstant.ALBUM_NAME,albumMapper.selectByPrimaryKey(Integer.valueOf(musicLibraryList1.getAlbum())).getAlbumName());
            }else {
                remap.put(APIConstant.ALBUM_NAME,"");
            }
            if (null != musicLibraryList1.getStyleId()){
                remap.put(APIConstant.SINGER_STYLE_NAME,singerStyleMapper.selectByPrimaryKey(Integer.valueOf(musicLibraryList1.getStyleId())).getStyleName());
            }else {
                remap.put(APIConstant.SINGER_STYLE_NAME,"");
            }
            if (musicLibraryList1.getPublishDate() != null) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date=musicLibraryList1.getPublishDate();
                String str=sdf.format(date);
                remap.put(APIConstant.PUBLISH_DATE,str);
            } else {
                remap.put(APIConstant.PUBLISH_DATE, "");
            }
            musicLibraryLists.add(remap);
        }
        Map dataMap = new HashMap();
        dataMap.put("musicLibraryLists",musicLibraryLists);
        return ResponseConstant.getSuccessResult(dataMap);
    }

    /**
     * 歌曲搜索(以发行时间排序)
     * @param map
     * @return
     */
    @Override
    public Map selectMusiclibraryByNew(Map map) {
        Map returnMap = new HashMap();
        List<Object> musicLibraryLists = new ArrayList<Object>();
        List<MusicLibrary> musicLibraryList = musicLibraryMapper.selectMusiclibraryByNewApi(map);
        int totalNum = musicLibraryMapper.selectByCount(map);      //获取数据总数
        for (MusicLibrary musicLibraryList1 : musicLibraryList) {
            Map remap = new HashMap();
            remap.put(APIConstant.PK_ID, musicLibraryList1.getPkId());
            remap.put(APIConstant.MUSIC_NAME, musicLibraryList1.getMusicName());
            remap.put(APIConstant.SINGER_NAME, musicLibraryList1.getSingerName());
            remap.put(APIConstant.MUSIC_IMG_URL, musicLibraryList1.getMusicImgUrl());
            remap.put(APIConstant.ALBUM, musicLibraryList1.getAlbum());
            remap.put(APIConstant.STYLE_ID, musicLibraryList1.getStyleId());
            remap.put(APIConstant.CLICKS, musicLibraryList1.getClicks());
            if (!StringUtil.isEmpty(musicLibraryList1.getAlbum())){
                remap.put(APIConstant.ALBUM_NAME,albumMapper.selectByPrimaryKey(Integer.valueOf(musicLibraryList1.getAlbum())).getAlbumName());
            }else {
                remap.put(APIConstant.ALBUM_NAME,"");
            }
            if (null != musicLibraryList1.getStyleId()){
                remap.put(APIConstant.SINGER_STYLE_NAME,singerStyleMapper.selectByPrimaryKey(Integer.valueOf(musicLibraryList1.getStyleId())).getStyleName());
            }else {
                remap.put(APIConstant.SINGER_STYLE_NAME,"");
            }
            if (musicLibraryList1.getPublishDate() != null) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date=musicLibraryList1.getPublishDate();
                String str=sdf.format(date);
                remap.put(APIConstant.PUBLISH_DATE,str);
            } else {
                remap.put(APIConstant.PUBLISH_DATE, "");
            }
            musicLibraryLists.add(remap);
        }
        Map dataMap = new HashMap();
        dataMap.put("musicLibraryLists",musicLibraryLists);
        return ResponseConstant.getSuccessResult(dataMap);
    }
}
