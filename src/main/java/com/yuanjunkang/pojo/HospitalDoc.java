package com.yuanjunkang.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HospitalDoc {
    private Integer id;
    private String hospitalname;
    private String address;
    private Integer score;
    private String city;
    private String grade;
    private String location;//位置
    private Object distance;//距离
    private Boolean ispublic;//是否公立
    private String image;
    public HospitalDoc(Hospital hospital){
        this.id = hospital.getId();
        this.hospitalname = hospital.getHospitalname();
        this.address = hospital.getAddress();
        this.score = hospital.getScore();
        this.city = hospital.getCity();
        this.location = hospital.getLatitude()+", "+hospital.getLongitude();
        this.ispublic = hospital.getIspublic();
        this.grade = hospital.getGrade();
        this.image = hospital.getImage();
    }
}
