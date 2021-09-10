package com.xian.photoscan.service;

import com.xian.photoscan.model.Image;
import com.xian.photoscan.model.ShowLabel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xian
 * @description
 * @createTime 2021/9/9 14:14
 */
public interface ImageShowService {

    /**
     * 获取某个路径下的图片列表
     * @param path
     * @param request
     * @return
     */
    List<ShowLabel> getImageListByPath(String path, HttpServletRequest request);



}
