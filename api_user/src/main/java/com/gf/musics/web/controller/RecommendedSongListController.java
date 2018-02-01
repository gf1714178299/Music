package com.gf.musics.web.controller;

import com.gf.musics.web.Covert.RequestCovert;
import com.gf.musics.web.constant.APIConstant;
import com.gf.musics.web.service.RecommendService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "recommendSongList")
public class RecommendedSongListController {
    private final static Logger logger = LoggerFactory.getLogger(RecommendedSongListController.class);
    @Autowired
    private RecommendService recommendService;
}
