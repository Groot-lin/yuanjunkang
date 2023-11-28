package com.yuanjunkang.pojo;

import lombok.Data;

@Data
public class HosRequestParams {
    private String key;//搜索关键字
    private Integer page;//当前页码
    private Integer size;//该页的尺寸
    private String sortBy;//根据字段排序
    private String city;//城市
    private String grade;//等级
    private String location;//位置
    private Boolean ispublic;//是否公立
}
