package com.ccc.sys.io.commons.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <a>Title:FileUtils</a>
 * <a>Author：<a>
 * <a>Description：<a>
 * <p>
 * 文件上传下载工具类
 *
 * @Author ccc
 * @Date 2020/3/18 18:27
 * @Version 1.0.0
 */
public class UploadFileUtils {
    /**
     * 文件上传保存路径
     */
    public static String UPDOAD_PATH = "D:/upload";

    static {
//        获取配置文件的值
        InputStream stream = UploadFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = properties.getProperty("filepath");
        if (null != property) {
            UPDOAD_PATH = property;
        }
    }

    /**
     * 根据文件老名字获取新名字
     *
     * @param filename
     * @return
     */
    public static String createNewFileName(String filename) {
        String substring = filename.substring(filename.lastIndexOf("."), filename.length());
        return IdUtil.simpleUUID().toUpperCase() + substring;
    }

    /**
     * 文件下载
     * @param path
     * @return
     */
    public static ResponseEntity<Object> createResponseEntity(String path) {
//        1.构建文件对象
        File file = new File(UPDOAD_PATH, path);
        if (file.exists()){
            //将文件转换成 byte 类型数组 然后下载
            byte[] bytes=null;
            try {

                bytes = FileUtil.readBytes(file);
            }catch (Exception e){
                e.printStackTrace();
            }

//            创建封装响应头信息的对象
            HttpHeaders headers = new HttpHeaders();
//            封装相应类容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            设置下载的文件名
//            headers.setContentDispositionFormData("attachment","123.jpg");

//            创建ResponseEntity对象
            ResponseEntity entity = new ResponseEntity(bytes, headers, HttpStatus.CREATED);

            return entity;
        }
        return null;
    }

    /**
     * 根据路径 更改名字 并去掉_temp后缀
     * @param goodsimg
     * @return
     */
    public static String renameFile(String goodsimg) {
        File file = new File(UPDOAD_PATH, goodsimg);
        String replace = goodsimg.replace("_temp","");
        if (file.exists()){
            file.renameTo(new File(UPDOAD_PATH,replace));
        }
        return replace;
    }

    /**
     * 修改时 如果跟新图片 根据老路径则删除之前的图片
     * @param oldPaht
     */
    public static void removeFileByPath(String oldPaht) {
        File file = new File(UPDOAD_PATH, oldPaht);
//        如果文件存在，则删除
        if (file.exists()){
            file.delete();
        }
    }
}
