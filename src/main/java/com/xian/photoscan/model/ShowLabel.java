package com.xian.photoscan.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 前端展示标签模型
 * @author xian
 * @description
 * @createTime 2021/9/9 14:23
 */
@Data
@Accessors(chain = true)
public class ShowLabel {

    private String name;

    private String url;
    /**
     * 1-文件 2-文件
     */
    private Integer type;

    private String size;

    private String path;

}
