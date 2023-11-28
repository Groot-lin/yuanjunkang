package com.yuanjunkang.service.impl;

import com.yuanjunkang.mapper.MedicineMapper;
import com.yuanjunkang.pojo.Medicine;
import com.yuanjunkang.pojo.Paging;
import com.yuanjunkang.service.MedicineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class MedicineServiceImpl implements MedicineService {
    @Autowired
    MedicineMapper medicineMapper;

    @Override
    public Paging<Medicine> searchAll(int currentpage, int pagesize) {
        int begin = (currentpage-1)*pagesize;
        int size = pagesize;
        System.out.println(begin);
        List<Medicine> medicines = medicineMapper.searchAll(begin,size);
        int count = medicineMapper.selectCount();
        Paging<Medicine> paging = new Paging<>();
        paging.setRows(medicines);
        paging.setTotalcount(count);
        return paging;
    }

    @Override
    public Paging<Medicine> searchByPage(int currentpage, int pagesize, String medicinename) {

        System.out.println(medicinename);
        if(medicinename==null||medicinename==""||medicinename.length()==0)
            return searchAll(currentpage,pagesize);
        System.out.println(currentpage+","+pagesize);
        int begin = (currentpage-1)*pagesize;
        int size = pagesize;

        List<Medicine> medicines = medicineMapper.searchByPage(begin,size,medicinename);
        int count = medicineMapper.selectCountByCondition(medicinename);
        Paging<Medicine> paging = new Paging<>();
        paging.setRows(medicines);
        paging.setTotalcount(count);
        return paging;
    }
    @Override
    public List<Medicine> selectByUsage(String usage) {
        List<Medicine> medicines = medicineMapper.selectByUsage(usage);
        System.out.println(medicines);
        return medicines;
    }

    @Override
    public List<Medicine> selectByName(String medicinename){
        List<Medicine> medicines = medicineMapper.selectByName(medicinename);
        System.out.println(medicines);
        return medicines;
    }


    @Override
    public Medicine selectbyid(int id) {
        Medicine medicine = medicineMapper.selectbyid(id);
        System.out.println(medicine);
        return medicine;
    }
}
