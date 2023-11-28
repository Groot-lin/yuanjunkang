package com.yuanjunkang.controller;

import com.yuanjunkang.pojo.HosRequestParams;
import com.yuanjunkang.pojo.HospitalDoc;
import com.yuanjunkang.pojo.Paging;
import com.yuanjunkang.service.impl.HospitalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hospital")
public class HospitalController {
    @Autowired
    private HospitalServiceImpl hospitalService;

    @PostMapping("list")
    public Paging<HospitalDoc> search(@RequestBody HosRequestParams params){
        System.out.println(params);
        return hospitalService.search(params);
    }
}
