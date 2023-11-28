package com.yuanjunkang.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Medicine {
    Integer id;
    String medicinename;
    String brand;
    Integer price;
    Date date;
    String usage;
    String way;
    String image;
    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + medicinename + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", usage='" + usage + '\'' +
                ", way='" + way + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return medicinename;
    }

    public void setName(String name) {
        this.medicinename = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
