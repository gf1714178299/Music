package com.gf.musics.web.controller;

import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.service.TestService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "test")
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TestService testService;
    @RequestMapping(value = "getList.action")
    public void setTestService(HttpServletResponse response)throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try{
            String s = new String();
            s="OK";
            returnMap = testService.selectBys(s);
            System.out.print(s);
        }catch (Exception e){
            e.printStackTrace();
            returnMap = ResponseConstant.getResponsecodeDesc(1000);
        }
        outWriter.write(mapper.writeValueAsString(returnMap));
        return;
    }
}
