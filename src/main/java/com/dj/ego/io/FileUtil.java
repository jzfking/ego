package com.dj.ego.io;

import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 戴俊明
 * @version 1.0
 * @className FileDao
 * @description 文件操作类
 * @date 2019/5/20 18:58
 **/

@Repository
public class FileUtil {
    /**
     * @param file 文件
     * @param path 地址
     * @return java.io.File
     * @author 戴俊明
     * @description 保存一个文件到指定位置
     * @date 2019/5/20 18:58
     **/
    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return null;
        }
        String fileName = Objects.requireNonNull(file).getOriginalFilename();
        String suffix = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));
        String uuid = IdUtil.simpleUUID();
        File dest = new File(path + uuid + suffix);
        try {
            file.transferTo(dest);
            return dest;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param file 文件
     * @return boolean
     * @author 戴俊明
     * @description 删除某一个文件
     * @date 2019/5/20 18:59
     **/
    public boolean delete(File file) {
        return cn.hutool.core.io.FileUtil.del(file);
    }

}
