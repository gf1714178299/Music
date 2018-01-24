package com.gf.musics.web.controller;

import com.aliyun.oss.OSSClient;
import com.gf.musics.web.constant.ParameterConstant;
import com.gf.musics.web.constant.ResponseConstant;
import com.gf.musics.web.util.AliyunOSSClientUtil;
import com.gf.musics.web.util.OSSClientConstants;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxin on 2017/7/17.
 */
@Controller
@RequestMapping(value = "manage")
public class uploadController {

    private final static Logger logger = LoggerFactory.getLogger(uploadController.class);


    //图片上传用
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//
//    @RequestMapping(value = "upload.action", method = RequestMethod.POST)
//    @ResponseBody
//    public void upload(
//            HttpServletResponse response,
//            HttpServletRequest request,
//            @RequestParam(value = "upfile") MultipartFile file
//    ) throws Exception {
//        response.setContentType("application/json;charset=UTF-8");
//        PrintWriter outWriter = response.getWriter();
//        ObjectMapper mapper = new ObjectMapper();
//        Map returnMap = new HashMap();
//        try {
//            FileUtil fileUtil = new FileUtil();
//            returnMap = fileUtil.uploadEditor(file, "/upload/" + simpleDateFormat.format(new Date()), request);
//            String callbackcontent = String.valueOf(returnMap.get("callbackcontent"));
//            outWriter.write(callbackcontent);
//
//        } catch (Exception e) {
//            returnMap = ResponseConstant.getResponsecodeDesc(10017);
//            outWriter.write(mapper.writeValueAsString(returnMap));
//        }
//        return;
//    }

    @RequestMapping(value = "upload.action", method = RequestMethod.POST)
    @ResponseBody
    public void upload(
            HttpServletResponse response,
            HttpServletRequest request,
            @RequestParam(value = "upfile") MultipartFile file
    ) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            if (file != null) {
                    //初始化OSSClient
                OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
                    //将内存中的数据传到OSS
                String newFileName = AliyunOSSClientUtil.uploadUeditor(ossClient, file, OSSClientConstants.BUCKET_NAME, OSSClientConstants.IMAGE_FOLDER);
                outWriter.write(newFileName);
            } else {
                returnMap = ResponseConstant.getResponsecodeDesc(-2);
                outWriter.write(mapper.writeValueAsString(returnMap));
            }

        } catch (Exception e) {
            returnMap = ResponseConstant.getResponsecodeDesc(10017);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }
        return;
    }



    /**
     * 上传图片到OSS（绝对限制）
     *
     * @param response
     * @param request
     * @param file
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "uploadImg.action", method = RequestMethod.POST)
    public void uploadImg(
            HttpServletResponse response,
            HttpServletRequest request,
            @RequestParam(value = "file") MultipartFile file
    ) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            int width = Integer.parseInt(request.getParameter("width"));
            int height = Integer.parseInt(request.getParameter("height"));
            if (file != null) {
                BufferedImage bufferedImg = ImageIO.read(file.getInputStream());
                int imgWidth = bufferedImg.getWidth();
                int imgHeight = bufferedImg.getHeight();
//                if(width != imgWidth || height != imgHeight){
//                    returnMap = ResponseConstant.getResponsecodeDesc(-3);
//                    returnMap.put(ParameterConstant.RETURN_MSG, "图片大小限定为" + width + "*" + height);
//                    outWriter.write(mapper.writeValueAsString(returnMap));
//                }
                float size = file.getSize()/1024;//获取图片大小
                //限制宽高比
                if ((imgHeight*width) != (imgWidth*height)){
                    returnMap = ResponseConstant.getResponsecodeDesc(-3);
                    returnMap.put(ParameterConstant.RETURN_MSG, "图片宽高比限制为" + width + ":" + height);
                    outWriter.write(mapper.writeValueAsString(returnMap));
                }else if (size>200){//限制图片大小为200k
                    returnMap = ResponseConstant.getResponsecodeDesc(-3);
                    returnMap.put(ParameterConstant.RETURN_MSG, "图片最大不超过200k");
                    outWriter.write(mapper.writeValueAsString(returnMap));
                }
                else{
                    //初始化OSSClient
                    OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
                    //将内存中的数据传到OSS
                    String newFileName = AliyunOSSClientUtil.uploadObjectToOSS(ossClient, file, OSSClientConstants.BUCKET_NAME, OSSClientConstants.IMAGE_FOLDER);
                    returnMap = ResponseConstant.getResponsecodeDesc(0);
                    returnMap.put(ParameterConstant.IMG_URL, newFileName);
                    outWriter.write(mapper.writeValueAsString(returnMap));
                }
            } else {
                returnMap = ResponseConstant.getResponsecodeDesc(-2);
                outWriter.write(mapper.writeValueAsString(returnMap));
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("uploadImg:" + returnMap, e);
            returnMap = ResponseConstant.getResponsecodeDesc(10005);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }

        return;
    }

    /**
     * 上传图片到OSS（相对限制）
     *
     * @param response
     * @param request
     * @param file
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "uploadPropImg.action", method = RequestMethod.POST)
    public void uploadPropImg(
            HttpServletResponse response,
            HttpServletRequest request,
            @RequestParam(value = "file") MultipartFile file
    ) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            int width = Integer.parseInt(request.getParameter("width"));
            int height = Integer.parseInt(request.getParameter("height"));
            int proportion = Integer.parseInt(request.getParameter("proportion"));
            if (file != null) {
                BufferedImage bufferedImg = ImageIO.read(file.getInputStream());
                int imgWidth = bufferedImg.getWidth();
                int imgHeight = bufferedImg.getHeight();
                if((width < imgWidth || height < imgHeight)){
                    if((proportion != imgWidth / imgHeight) && imgWidth % imgHeight == 0){
                        returnMap = ResponseConstant.getResponsecodeDesc(-3);
                        returnMap.put(ParameterConstant.RETURN_MSG, "图片宽高比限定为" + proportion + "：1");
                        outWriter.write(mapper.writeValueAsString(returnMap));
                    }else{
                        returnMap = ResponseConstant.getResponsecodeDesc(-3);
                        returnMap.put(ParameterConstant.RETURN_MSG, "图片最大规格限定为" + width + "*" + height);
                        outWriter.write(mapper.writeValueAsString(returnMap));
                    }
                }else{
                    //初始化OSSClient
                    OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
                    //将内存中的数据传到OSS
                    String newFileName = AliyunOSSClientUtil.uploadObjectToOSS(ossClient, file, OSSClientConstants.BUCKET_NAME, OSSClientConstants.IMAGE_FOLDER);
                    returnMap = ResponseConstant.getResponsecodeDesc(0);
                    returnMap.put(ParameterConstant.IMG_URL, newFileName);
                    outWriter.write(mapper.writeValueAsString(returnMap));
                }
            } else {
                returnMap = ResponseConstant.getResponsecodeDesc(-2);
                outWriter.write(mapper.writeValueAsString(returnMap));
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("uploadPropImg:" + returnMap, e);
            returnMap = ResponseConstant.getResponsecodeDesc(10005);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }
        return;
    }

    /**
     *
     * @param response
     * @param request
     * @param file
     * @throws Exception
     * 1寸照片高宽比1.4
     */
    @ResponseBody
    @RequestMapping(value = "uploadAvatar.action", method = RequestMethod.POST)
    public void uploadAvatar(
            HttpServletResponse response,
            HttpServletRequest request,
            @RequestParam(value = "file") MultipartFile file
    ) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter outWriter = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map returnMap = new HashMap();
        try {
            int width = Integer.parseInt(request.getParameter("width"));
            int height = Integer.parseInt(request.getParameter("height"));
            if (file != null) {
                BufferedImage bufferedImg = ImageIO.read(file.getInputStream());
                int imgWidth = bufferedImg.getWidth();
                int imgHeight = bufferedImg.getHeight();

                double percent = (double)imgHeight/(double)imgWidth;
                DecimalFormat format = (DecimalFormat)DecimalFormat.getInstance();
                format.applyPattern("####.#");
                String m = format.format(percent);
                if(!("1.4").equals(m) ){
                    returnMap = ResponseConstant.getResponsecodeDesc(-3);
                    returnMap.put(ParameterConstant.RETURN_MSG, "图片大小限定为" + width + "*" + height);
                    outWriter.write(mapper.writeValueAsString(returnMap));
                }else{
                    //初始化OSSClient
                    OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
                    //将内存中的数据传到OSS
                    String newFileName = AliyunOSSClientUtil.uploadObjectToOSS(ossClient, file, OSSClientConstants.BUCKET_NAME, OSSClientConstants.IMAGE_FOLDER);
                    returnMap = ResponseConstant.getResponsecodeDesc(0);
                    returnMap.put(ParameterConstant.IMG_URL, newFileName);
                    outWriter.write(mapper.writeValueAsString(returnMap));
                }
            } else {
                returnMap = ResponseConstant.getResponsecodeDesc(-2);
                outWriter.write(mapper.writeValueAsString(returnMap));
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("uploadImg:" + returnMap, e);
            returnMap = ResponseConstant.getResponsecodeDesc(10005);
            outWriter.write(mapper.writeValueAsString(returnMap));
        }

        return;
    }

}
