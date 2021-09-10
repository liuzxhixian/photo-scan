package com.xian.photoscan.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xian
 * @description
 * @createTime 2021/9/9 14:18
 */
public class FileUtils {

    /**
     * 获取路径下的文件列表
     * @param path
     * @return
     */
    public static List<String> getFileList(String path) {
        List<String> list = new ArrayList<>();
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (!file1.isDirectory()) {
                    list.add(file1.getName());
                }
            }
        } else {
            list.add(file.getName());
        }
        return list;
    }

    /**
     * 获取路径下的路径列表
     * @param path
     * @return
     */
    public static List<String> getDirList(String path) {
        List<String> list = new ArrayList<>();
        File file = new File(path);
        boolean exists = file.exists();
        if (exists && file.isDirectory()) {
            File[] files = file.listFiles(File::isDirectory);
            for (File file1 : files) {
                list.add(file1.getName());
            }
        }
        return list;
    }



}
