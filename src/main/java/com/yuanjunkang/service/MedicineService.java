package com.yuanjunkang.service;

import com.yuanjunkang.pojo.Medicine;
import com.yuanjunkang.pojo.Paging;

import java.util.List;

public interface MedicineService {
    /**
     * 首次刷新时搜索全部
     * @param begin
     * @param size
     * @return
     */
    Paging<Medicine> searchAll(int begin,int size);
    /**
     * 分页查询
     * @param begin
     * @param size
     * @param medicine
     * @return
     */
    Paging<Medicine> searchByPage(int begin, int size, String medicinename);
    /**
     * 根据用途查药品
     * @param usage
     * @return
     */
    List<Medicine> selectByUsage(String usage);
    /**
     * 根据药品名查药品
     * @param medicinename
     * @return
     */
    List<Medicine> selectByName(String medicinename);

    /**
     * 根据id查药品
     * @param id
     * @return
     */
    Medicine selectbyid(int id);
}
