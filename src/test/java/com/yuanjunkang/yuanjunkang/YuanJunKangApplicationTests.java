package com.yuanjunkang.yuanjunkang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuanjunkang.mapper.HospitalMapper;
import com.yuanjunkang.pojo.Hospital;
import com.yuanjunkang.pojo.Medicine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class YuanJunKangApplicationTests {

    @Autowired
    private HospitalMapper hospitalMapper;
    @Test
    void testGetall() {
        List<Hospital> hospitals = hospitalMapper.selectList(null);
        System.out.println(hospitals);
    }
    @Test
    void queryselect(){
        QueryWrapper qw = new QueryWrapper();
        qw.lt("score",13);
        List<Medicine> medicineList = hospitalMapper.selectList(qw);
        System.out.println(medicineList);
    }

}
