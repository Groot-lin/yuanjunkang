package com.yuanjunkang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("hospital")
public class Hospital {
    @TableId(type = IdType.INPUT)
    private Integer id;
    private String hospitalname;
    private String address;//地址
    private Integer score;//评分
    private String city;//城市
    private String grade;//等级
    private String longitude;//经度
    private String latitude;//纬度
    private Boolean ispublic;//是否是公立医院
    private String image;//图片

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", hospitalname='" + hospitalname + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                ", city='" + city + '\'' +
                ", grade='" + grade + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", ispublic=" + ispublic +
                ", image='" + image + '\'' +
                '}';
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Boolean getIspublic() {
        return ispublic;
    }

    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }
}
