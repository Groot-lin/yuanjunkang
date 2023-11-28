package com.yuanjunkang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanjunkang.pojo.HosRequestParams;
import com.yuanjunkang.pojo.Hospital;
import com.yuanjunkang.pojo.HospitalDoc;
import com.yuanjunkang.pojo.Paging;

public interface HospitalService extends IService<Hospital> {
    Paging<HospitalDoc> search(HosRequestParams params);
}
