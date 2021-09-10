package com.xian.photoscan.controller;

import com.alibaba.fastjson.JSON;
import com.xian.photoscan.model.ShowLabel;
import com.xian.photoscan.service.ImageShowService;
import com.xian.photoscan.service.impl.ImageShowServiceImpl;
import com.xian.photoscan.utils.ServerConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xian
 * @description
 * @createTime 2021/9/9 14:39
 */
@Controller
@RequestMapping("/photo/index")
public class IndexController {


    @Value("${photo.default.path}")
    private String path;

    @Resource
    private ImageShowService showService;

    @RequestMapping
    public String toIndex(HttpServletRequest request) {
        String host = request.getHeader("Host");
        String[] strings = host.split(":");
        String serverIp = ServerConstant.SERVER_IP;
        if (StringUtils.isEmpty(serverIp)) {
            ServerConstant.SERVER_IP = strings[0];
        }
        List<ShowLabel> imageListByPath = showService.getImageListByPath(path, null);
        request.setAttribute("list",imageListByPath);
        return "index";
    }

    @RequestMapping("/default")
    public @ResponseBody Object  getList() {
        List<ShowLabel> imageListByPath = showService.getImageListByPath(path, null);
        Map<String,Object> map = new HashMap<>(8);
        map.put("code",200);
        map.put("data", JSON.toJSONString(imageListByPath));
        return JSON.toJSONString(map);
    }

    @RequestMapping("/getImgExp")
    public void getImgExp(HttpServletRequest request, HttpServletResponse response) {
        String imgPath = "D:\\picture_video\\like\\迪丽热巴\\0026.jpg";
        try(InputStream inputStream = new FileInputStream(imgPath);
            OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("image/jpg");
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer,0,read);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
