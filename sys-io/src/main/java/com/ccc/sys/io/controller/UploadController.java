package com.ccc.sys.io.controller;

import cn.hutool.core.date.DateUtil;
import com.ccc.sys.io.commons.utils.UploadFileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <a>Title:uploadController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 * <p>
 * 图像上传
 *
 * @Author ccc
 * @Date 2020/3/18 18:20
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/file")
public class UploadController {

    /**
     * 文件上传
     *
     * @param mf
     * @return
     */
    @RequestMapping(value = "uploadFile")
    public Map<String, Object> uploadFile(MultipartFile mf) {
        //1. 得到文件名
        String filename = mf.getOriginalFilename();
        //2. 根据文件名 生产新的文件名
        String newFileName = UploadFileUtils.createNewFileName(filename);
        //3. 得到当前日期的字符串 作为文件名
        String dirName = DateUtil.format(new Date(), "yyyy-MM-dd");
        //4. 构造文件夹
        File dirFile = new File(UploadFileUtils.UPDOAD_PATH, dirName);
        //5. 判断当前文件夹是否存在  如果文件夹不存在 就创建文件夹 
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        //6. 构造文件对象 文件夹 file   文件名 newFileName
//        + "_temp" 防止恶意上传 便于定时清理垃圾图片
        File newFile = new File(dirFile, newFileName + "_temp");
        //7. 把图片信息写入file
        try {
            mf.transferTo(newFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("path", dirName + "/" + newFileName + "_temp");
        return map;
    }

    /**
     * 图片下载
     */
    @RequestMapping(value = "showImageByPath")
    public ResponseEntity<Object> showImageByPath(String path) {
        return UploadFileUtils.createResponseEntity(path);
    }
}
