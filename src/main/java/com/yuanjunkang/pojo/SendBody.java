package com.yuanjunkang.pojo;

import lombok.Data;

@Data
public class SendBody {
    Integer currentpage;
    Integer pageSize;

    public Integer getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(Integer currentpage) {
        this.currentpage = currentpage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
