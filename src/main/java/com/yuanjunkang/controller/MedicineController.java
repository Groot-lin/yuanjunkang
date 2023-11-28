package com.yuanjunkang.controller;


import com.yuanjunkang.pojo.Medicine;
import com.yuanjunkang.pojo.Paging;
import com.yuanjunkang.service.impl.MedicineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    private MedicineServiceImpl medicineService;

    /**
     * 查询所有(分页)
     * @param medicinename
     * @param currentPage
     * @param pageSize
     * @return
     */

    @PostMapping("searchall")
    public Paging<Medicine> searchAll(@RequestBody Map<String, String> map) {
        String medicinename = map.get("medicinename");
        String currentPage = map.get("currentPage");
        String pageSize = map.get("pageSize");
        return medicineService.searchByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize), medicinename);
    }
    /**
     * 根据名字查药品
     * @param name
     * @return
     */
    @PostMapping("/medicinename")
    public List<Medicine> selectbByName(@RequestBody String name){
        return medicineService.selectByName(name);
    }
    /**
     * 根据id查药品
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Medicine selectbyid(@PathVariable("id") Integer id) {
        return medicineService.selectbyid(id);
    }
    
    /**
     * 根据用途查药品
     * @param usage
     * @return
     */
    @PostMapping("usage")
    public List<Medicine> selectbByUsage(@RequestBody String usage){
        return medicineService.selectByUsage(usage);
    }
}
