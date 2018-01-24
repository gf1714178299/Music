package com.gf.musics.web.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yinhaijin on 16/5/10.
 */
public class FileUtil {
    private String allowSuffix = "jpg,png,gif,jpeg,mp4";//允许文件格式
    private long allowSize = 250 * 10240;//允许文件大小250kb
    private float allowWidth = 30000;
    private float allowHeight = 30000;
    private String fileName;
    private String[] fileNames;

    public String getAllowSuffix() {
        return allowSuffix;
    }

    public void setAllowSuffix(String allowSuffix) {
        this.allowSuffix = allowSuffix;
    }

    public long getAllowSize() {
        return allowSize;
    }

    public void setAllowSize(long allowSize) {
        this.allowSize = allowSize;
    }

    public float getAllowWidth() {
        return allowWidth;
    }

    public void setAllowWidth(float allowWidth) {
        this.allowWidth = allowWidth;
    }

    public float getAllowHeight() {
        return allowHeight;
    }

    public void setAllowHeight(float allowHeight) {
        this.allowHeight = allowHeight;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getFileNames() {
        return fileNames;
    }

    public void setFileNames(String[] fileNames) {
        this.fileNames = fileNames;
    }

    /**
     * <p class="detail">
     * 功能：重新命名文件
     * </p>
     *
     * @return
     * @author wangsheng
     * @date 2014年9月25日
     */
    private String getFileNameNew() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return fmt.format(new Date());
    }

    /**
     * <p class="detail">
     * 功能：文件上传
     * </p>
     *
     * @param files
     * @param destDir
     * @throws Exception
     * @author wangsheng
     * @date 2014年9月25日
     */
    public Map uploads(MultipartFile[] files, String destDir, HttpServletRequest request) throws Exception {
        Map returnMap = new HashMap();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        try {
            fileNames = new String[files.length];
            int index = 0;
            for (MultipartFile file : files) {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                int length = getAllowSuffix().indexOf(suffix);
                if (length == -1) {
                    throw new Exception("请上传允许格式的文件");
                }
                /*if(file.getSize() > getAllowSize()){
                    throw new Exception("您上传的文件大小已经超出范围");
                }*/
                String realPath = GetProperties.getFilePath();
                File destFile = new File(realPath + destDir);
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                String fileNameNew = getFileNameNew() + "." + suffix;//
                File f = new File(destFile.getAbsoluteFile() + "\\" + fileNameNew);
                file.transferTo(f);//创建文件到服务器
                f.createNewFile();
//                fileNames[index++] = basePath + destDir + fileNameNew;
                fileNames[index++] =  destDir + "/" + fileNameNew;
            }
            returnMap.put("code", 0);
            Map tempMap = new HashMap();
            tempMap.put("fileurl", fileNames);
            returnMap.put("data", tempMap);
            returnMap.put("msg", "上传成功!");
        } catch (Exception e) {
            throw e;
        }
        return returnMap;
    }

    /**
     * <p class="detail">
     * 功能：文件上传
     * </p>
     *
     * @param file
     * @param destDir
     * @throws Exception
     * @author wangsheng
     * @date 2014年9月25日
     */
    public Map upload(MultipartFile file, Integer allowWidth, Integer allowHeight, String destDir, HttpServletRequest request) throws Exception {
        Map returnMap = new HashMap();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            int length = getAllowSuffix().indexOf(suffix.toLowerCase());
            if (length == -1) {
                returnMap.put("code", 1);
                returnMap.put("msg", "请上传允许格式的文件");
                return returnMap;
            }
//            String realPath = GetProperties.getFilePath();
            String realPath = request.getSession().getServletContext().getRealPath("/");
            File destFile = new File(realPath + destDir);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            String fileNameNew = getFileNameNew() + "." + suffix;
            File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
            file.transferTo(f);
            f.createNewFile();

            if (allowWidth != null && allowHeight != null) {
                BufferedImage bufferedImage = ImageIO.read(f);
                if (bufferedImage == null) {
                    f.delete();
                    returnMap.put("code", 1);
                    returnMap.put("msg", "文件已损坏，请上传有效的文件!");
                    return returnMap;
                }
                int imageWidth = bufferedImage.getWidth();
                int imageHeight = bufferedImage.getHeight();
                if (imageWidth > allowWidth.intValue() || imageHeight > allowHeight.intValue()) {
                    f.delete();
                    returnMap.put("code", 1);
                    returnMap.put("msg", String.format("请上传指定大小的图片，最大尺寸不超过%dx%d", allowWidth.intValue(), allowHeight.intValue()));
                    return returnMap;
                }
            }
            fileName = basePath + destDir + fileNameNew;
            String retFileName = destDir + "/" + fileNameNew;
            returnMap.put("code", 0);
            Map tempMap = new HashMap();
            tempMap.put("fileurl", retFileName);
            returnMap.put("data", tempMap);
            returnMap.put("msg", "上传成功!");
            return returnMap;
        } catch (Exception e) {
//            throw e;
            returnMap.put("code", -1);
            returnMap.put("msg", "文件上传失败,请重新上传");
            return returnMap;
        }
    }

    public Map uploadAttachement(MultipartFile file, Integer allowWidth, Integer allowHeight, String destDir, HttpServletRequest request) throws Exception {
        Map returnMap = new HashMap();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        try {
            String originalFileName = file.getOriginalFilename();
            String suffix = originalFileName.substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            String realPath = GetProperties.getFilePath();
            File destFile = new File(realPath + destDir);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            String fileNameNew = getFileNameNew() + "." + suffix;
            File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
            file.transferTo(f);
            f.createNewFile();

            fileName = basePath + destDir + fileNameNew;
            String retFileName = destDir + "/" + fileNameNew;
            returnMap.put("code", 0);
            Map tempMap = new HashMap();
            tempMap.put("fileurl", retFileName);
            tempMap.put("trueName", originalFileName);
            returnMap.put("data", tempMap);
            returnMap.put("msg", "上传成功!");
            return returnMap;
        } catch (Exception e) {
//            throw e;
            returnMap.put("code", -1);
            returnMap.put("msg", "文件上传失败,请重新上传");
            return returnMap;
        }
    }


    public Map uploadEditor(MultipartFile file,String destDir, HttpServletRequest request) throws Exception {
        Map returnMap = new HashMap();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        try {
            String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            int length = getAllowSuffix().indexOf(suffix.toLowerCase());
            if (length == -1) {
                returnMap.put("code", 1);
                returnMap.put("msg", "请上传允许格式的文件");
                return returnMap;
            }
            String realPath = request.getSession().getServletContext().getRealPath("/");
//            String realPath = GetProperties.getFilePath();
            File destFile = new File(realPath + destDir);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            String fileNameNew = getFileNameNew() + "." + suffix;
            File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
            file.transferTo(f);
            f.createNewFile();
            fileName = basePath + destDir + fileNameNew;
            String retFileName = destDir + "/" + fileNameNew;
            returnMap.put("resultCode", 0);
            Map tempMap = new HashMap();
            tempMap.put("filePath", retFileName);
            returnMap.put("resultData", tempMap);
            returnMap.put("msg", "上传成功!");

            String type = "SUCCESS";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String Date = dateFormat.format(new Date());
            String url = "/upload/"+Date+"/"+fileNameNew;
            String callbackcontent= "{\"name\":\""+ fileNameNew +"\", \"originalName\": \""+ fileNameNew +"\", \"size\": "+ file.getSize() +", \"state\": \""+ type +"\", \"type\": \""+ prefix +"\", \"url\": \""+ url +"\"}";
            callbackcontent = callbackcontent.replaceAll( "\\\\", "\\\\" );
            returnMap.put("callbackcontent",callbackcontent);

            return returnMap;
        } catch (Exception e) {
//            throw e;
            e.printStackTrace();
            returnMap.put("code", -1);
            returnMap.put("msg", "文件上传失败,请重新上传");
            return returnMap;

        }
    }


    /**
     * 删除单个文件
     *
     * @param file 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(File file) {
        boolean flag = false;
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param file 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File file) {
        if (file.isDirectory()) {
            String[] children = file.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(file, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return file.delete();
    }
}
