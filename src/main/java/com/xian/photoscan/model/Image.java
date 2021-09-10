package com.xian.photoscan.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author xian
 * @description
 * @createTime 2021/9/9 14:14
 */
@Data
@Accessors(chain = true)
public class Image implements Serializable {

    private String name;

    private String url;

    private String size;


}
