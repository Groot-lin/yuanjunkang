package com.yuanjunkang.mapper;

import com.yuanjunkang.pojo.Medicine;
import org.apache.ibatis.annotations.*;
import java.util.List;
@Mapper
public interface MedicineMapper {

    //根据药品名查药信息
    List<Medicine> selectByName(@Param("medicinename")String medicine);

    //根据症状查药品信息
    List<Medicine> selectByUsage(@Param("usage")String usage);

    //分页查询
    List<Medicine> searchByPage(@Param("begin") int begin,@Param("size") int size,@Param("medicinename") String medicinename);
    //条件查询总数查询
    int selectCountByCondition(@Param("medicinename")String medicinename);

    //搜索全部
    List<Medicine> searchAll(@Param("begin")int bigin,@Param("size") int size);

    //总数
    @Select("select count(*) from medicine")
    int selectCount();
    //根据id查药(test)
    @Select("select * from medicine where id = #{id}")
    Medicine selectbyid(@Param("id")int id);
}
