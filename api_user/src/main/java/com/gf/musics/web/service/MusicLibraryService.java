package com.gf.musics.web.service;

import java.util.Map;

public interface MusicLibraryService {
    /**
     * 歌曲搜索(以点击量排序)
     * @param map
     * @return
     */
    Map selectMusiclibraryByPage(Map map);

    /**
     * 歌曲搜索(以发行时间排序)
     * @param map
     * @return
     */
    Map selectMusiclibraryByNew(Map map);
}
