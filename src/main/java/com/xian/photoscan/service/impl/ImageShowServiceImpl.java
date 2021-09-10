package com.xian.photoscan.service.impl;

import com.xian.photoscan.model.Image;
import com.xian.photoscan.model.ShowLabel;
import com.xian.photoscan.service.ImageShowService;
import com.xian.photoscan.utils.FileUtils;
import com.xian.photoscan.utils.ServerConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xian
 * @description
 * @createTime 2021/9/9 14:17
 */
@Service
public class ImageShowServiceImpl implements ImageShowService {

    @Value("${server.port}")
    private String port;

    @Override
    public List<ShowLabel> getImageListByPath(String path, HttpServletRequest request) {
        List<ShowLabel> labels = new ArrayList<>();

        // 先获取文件夹，文件夹的url和文件的url不一样
        List<String> dirList = FileUtils.getDirList(path);
        String serverIp = ServerConstant.SERVER_IP;
        dirList.forEach(s -> {
            ShowLabel label = new ShowLabel();
            label.setName(s);
            String url = serverIp + ":" + port + "/photo/index/list/" + label.getName();
            label.setUrl(url);
            label.setType(ServerConstant.DIR_TYPE);
            label.setPath(path);
            labels.add(label);
        });
        // 获取文件
        List<String> fileList = FileUtils.getFileList(path);
        fileList.forEach(s -> {
            ShowLabel label = new ShowLabel();
            label.setName(s);
            String url = serverIp + ":" + port + "/photo/download/" + label.getName();
            label.setUrl(url);
            label.setPath(path);
            label.setType(ServerConstant.FILE_TYPE);
            labels.add(label);
        });

        return labels;
    }

    public static void main(String[] args) {
        try {
            InetAddress localHost = Inet4Address.getLocalHost();
            String hostAddress = localHost.getHostAddress();
            System.out.println(hostAddress);
            short i = 1;
            short j = 2;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}
