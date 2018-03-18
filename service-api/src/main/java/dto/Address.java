package dto;

import java.io.Serializable;

/**
 * 省市区地址详情四级地址实体
 */
public class Address implements Serializable {

    private String province;
    private String city;
    private String area;
    private String detail;

    public Address() {
    }

    public Address(String province, String city, String area, String detail) {
        this.province = province;
        this.city = city;
        this.area = area;
        this.detail = detail;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
