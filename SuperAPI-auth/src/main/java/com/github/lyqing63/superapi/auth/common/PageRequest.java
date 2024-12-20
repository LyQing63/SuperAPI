package com.github.lyqing63.superapi.auth.common;

import com.github.lyqing63.superapi.auth.Constant.CommonConstant;
import lombok.Data;

@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;

    /**
     * 排序条件（默认相等）
     */
    private String type = CommonConstant.EQUAL_TO;

}
