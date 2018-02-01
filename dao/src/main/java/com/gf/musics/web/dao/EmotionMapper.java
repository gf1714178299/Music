package com.gf.musics.web.dao;

import com.gf.musics.web.model.Emotion;
import org.springframework.stereotype.Component;

@Component(value = "emotionMapper")
public interface EmotionMapper {
    int deleteByPrimaryKey(Integer pkId);

    int insert(Emotion record);

    int insertSelective(Emotion record);

    Emotion selectByPrimaryKey(Integer pkId);

    int updateByPrimaryKeySelective(Emotion record);

    int updateByPrimaryKey(Emotion record);
}