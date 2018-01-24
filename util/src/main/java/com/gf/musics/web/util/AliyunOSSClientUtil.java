package com.gf.musics.web.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: D丶Cheng
 * Date: 2017/7/20
 * Time: 11:27
 * Description:阿里云OSS上传工具类
 */
public class AliyunOSSClientUtil {
    //log日志
    private static Logger logger = Logger.getLogger(AliyunOSSClientUtil.class);
    //阿里云API的内或外网域名
    private static String ENDPOINT;
    //阿里云API的密钥Access Key ID
    private static String ACCESS_KEY_ID;
    //阿里云API的密钥Access Key Secret
    private static String ACCESS_KEY_SECRET;
    //阿里云API的bucket名称
    private static String BUCKET_NAME;

    //初始化属性
    static {
        ENDPOINT = OSSClientConstants.ENDPOINT;
        ACCESS_KEY_ID = OSSClientConstants.ACCESS_KEY_ID;
        ACCESS_KEY_SECRET = OSSClientConstants.ACCESS_KEY_SECRET;
        BUCKET_NAME = OSSClientConstants.BUCKET_NAME;
    }

    /**
     * 获取阿里云OSS客户端对象
     *
     * @return ossClient
     */
    public static OSSClient getOSSClient() {
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 创建存储空间
     *
     * @param ossClient  OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public static String createBucketName(OSSClient ossClient, String bucketName) {
        //存储空间
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            //创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            System.out.println("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     *
     * @param ossClient  oss对象
     * @param bucketName 存储空间
     */
    public static void deleteBucket(OSSClient ossClient, String bucketName) {
        ossClient.deleteBucket(bucketName);
        System.out.println("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     *
     * @param ossClient  oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名如"bandou/"
     * @return 文件夹名
     */
    public static String createFolder(OSSClient ossClient, String bucketName, String folder) {
        //文件夹名
        final String keySuffixWithSlash = folder;
        //判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            //创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            System.out.println("创建文件夹成功");
            //得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param ossClient  oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"bandou/"
     * @param key        Bucket下的文件的路径名+文件名 如："upload/test.jpg"
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key) {
        key = folder + key;
        if(key.startsWith("/")){
            key = key.substring(1);
        }
        ossClient.deleteObject(bucketName, folder + key);
        System.out.println("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    /**
     * 上传图片至OSS
     *
     * @param ossClient  oss连接
     * @param file       上传文件（文件全路径如：D:\\image\\test.jpg）
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"bandou/"
     * @return String 返回文件名
     */
    public static String uploadObjectToOSS(OSSClient ossClient, MultipartFile file, String bucketName, String folder) {
        String resultStr = null;
        try {
            //以输入流的形式上传文件
            InputStream is = file.getInputStream();
            //原始名称
            String originalFilename = file.getOriginalFilename();
            //新的图片名称
            String fileName = UUID.randomUUID().toString().trim().replaceAll("-", "").toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //文件大小
            Long fileSize = file.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
            //解析结果
            //resultStr = putResult.getETag(); //返回的唯一MD5数字签名
            resultStr = folder + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 上传图片至OSS
     *
     * @param ossClient  oss连接
     * @param file       上传文件（文件全路径如：D:\\image\\test.jpg）
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"bandou/"
     * @return String 返回文件名
     */
    public static String uploadUeditor(OSSClient ossClient, MultipartFile file, String bucketName, String folder) {
        String resultStr = null;
        try {
            String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //以输入流的形式上传文件
            InputStream is = file.getInputStream();
            //原始名称
            String originalFilename = file.getOriginalFilename();
            //新的图片名称
            String fileName = UUID.randomUUID().toString().trim().replaceAll("-", "").toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //文件大小
            Long fileSize = file.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
            //解析结果
            //resultStr = putResult.getETag(); //返回的唯一MD5数字签名
            resultStr = folder + fileName;

            String type = "SUCCESS";
            String callbackcontent= "{\"name\":\""+ fileName +"\", \"originalName\": \""+ fileName +"\", \"size\": "+ file.getSize() +", \"state\": \""+ type +"\", \"type\": \""+ prefix +"\", \"url\": \""+ folder+fileName +"\"}";
            callbackcontent = callbackcontent.replaceAll( "\\\\", "\\\\" );
            resultStr = callbackcontent;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 上传语音至OSS
     *
     * @param ossClient  oss连接
     * @param file       上传文件（文件全路径如：D:\\image\\test.jpg）
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"bandou/"
     * @return String 返回文件名
     */
    public static String uploadWareToOSS(OSSClient ossClient, MultipartFile file, String bucketName, String folder) {
        String resultStr = null;
        try {
            String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //以输入流的形式上传文件
            InputStream is = file.getInputStream();
            //新的图片名称
            String newsName = UUID.randomUUID().toString().trim().replaceAll("-", "").toString() + prefix;
            //原始名称
            String fileName = file.getOriginalFilename();
            //文件大小
            Long fileSize = file.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(newsName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + newsName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + newsName, is, metadata);
            //解析结果
            //resultStr = putResult.getETag(); //返回的唯一MD5数字签名
            String type = "SUCCESS";
            String callbackcontent= "{\"name\":\""+ fileName +"\", \"originalName\": \""+ newsName +"\", \"size\": "+ file.getSize()
                    +", \"state\": \""+ type +"\", \"type\": \""+ prefix +"\", \"url\": \""+ folder+newsName +"\"}";
            callbackcontent = callbackcontent.replaceAll( "\\\\", "\\\\" );
            resultStr = callbackcontent;
//            resultStr = folder + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 上传视频至OSS
     *
     * @param ossClient  oss连接
     * @param file       上传文件（文件全路径如：D:\\image\\test.jpg）
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"bandou/"
     * @return String 返回文件名
     */
    public static String uploadToVideo(OSSClient ossClient, MultipartFile file, String bucketName, String folder) {
        String resultStr = null;
        try {
            String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //以输入流的形式上传文件
            InputStream is = file.getInputStream();
            //原始名称
            String fileName = UUID.randomUUID().toString().trim().replaceAll("-", "").toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//            String fileName = file.getOriginalFilename();
            //文件大小
            Long fileSize = file.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
            //解析结果
            //resultStr = putResult.getETag(); //返回的唯一MD5数字签名
            String type = "SUCCESS";
            String callbackcontent= "{\"name\":\""+ fileName +"\", \"originalName\": \""+ fileName +"\", \"size\": "
                    + file.getSize() +", \"state\": \""+ type +"\", \"type\": \""+ prefix +"\", \"url\": \""+ folder+fileName +"\"}";
            callbackcontent = callbackcontent.replaceAll( "\\\\", "\\\\" );
            resultStr = callbackcontent;
//            resultStr = folder + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 上传图片至OSS
     *
     * @param ossClient  oss连接
     * @param is
     * @param suffix 文件后缀名
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"bandou/"
     * @return String 返回文件名
     */
    public static String uploadInputStreamToOSS(OSSClient ossClient, InputStream is,String suffix, String bucketName, String folder) {
        String resultStr = null;
        try {
            //新的图片名称
            String fileName = UUID.randomUUID().toString().trim().replaceAll("-", "").toString() + "." + suffix;
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(new PutObjectRequest(bucketName, folder + fileName, is));
            //解析结果
            //resultStr = putResult.getETag(); //返回的唯一MD5数字签名
            resultStr = folder + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".jpg".equalsIgnoreCase(fileExtension)) {
            return "image/jpg";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || ".pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || ".docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xls".equalsIgnoreCase(fileExtension) || ".xlsx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".pdf".equalsIgnoreCase(fileExtension)) {
            return "application/pdf";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "application/octet-stream";
    }

    /**
     * 通过文件名，返回上传阿里的文件目录
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getSuffix(String fileName) {
        //文件的后缀名
//        String fileExtension = fileName.substring(fileName.lastIndexOf("."));

        Integer tokenNum =  fileName.lastIndexOf(".");
        String token = fileName.substring(tokenNum+1, fileName.length());
        if (token.length() > 1 && null != token) {
            String audioArray []={"mp3","OGG","WAV","RM","ASF","WMA","WAV","APE","MIDI","VQF","CD","ACC","M4A","MP2","FLAC","3GP","AVC","AMR"};
            String videoArray []={"MP4","AVI","RMVB","WMV","3GP","MKV","MPG","MOV","FLV","SWF"};
            String imgageArray [] = {"JPG","PNG","ICO","BMP","GIF","TIF","PXC","TGA","PSD","PSDW","PDF"};
            //音频
            for (String audio : audioArray) {
                if (audio.equalsIgnoreCase(token)) {
                    return OSSClientConstants.AUDIO_FOLDER;
                }
            }
            //视频
            for (String video : videoArray) {
                if (video.equalsIgnoreCase(token)) {
                    return OSSClientConstants.VIDEO_FOLDER;
                }
            }
            //图片
            for (String imgage : imgageArray) {
                if (imgage.equalsIgnoreCase(token)) {
                    return OSSClientConstants.IMAGE_FOLDER;
                }
            }
        }
        //默认返回类型
        return OSSClientConstants.IMAGE_FOLDER;
    }

    //测试
    public static void main(String[] args) {
        //初始化OSSClient
        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        //上传文件
//        String files = "G:\\test\\a.jpg,G:\\test\\b.jpg,G:\\test\\c.jpg";
//        String[] file = files.split(",");
//        for (String filename : file) {
//            //System.out.println("filename:"+filename);
//            File filess = new File(filename);
//            String md5key = AliyunOSSClientUtil.uploadObjectToOSS(ossClient, filess, BUCKET_NAME, OSSClientConstants.IMAGE_FOLDER);
//            System.out.println("上传后的文件MD5数字唯一签名:" + md5key);
//            //上传后的文件MD5数字唯一签名:40F4131427068E08451D37F02021473A
//        }
        //删除文件
        deleteFile(ossClient, BUCKET_NAME, "", "test/c.jpg");
    }
}
