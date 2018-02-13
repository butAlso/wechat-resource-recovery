package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dto.Address;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统的使用者，Customer和Collector和Company的父类
 */

public class User implements Serializable {

    private int id;
    private String name;
    private String password;
    private String phone;
    private Date createTime;
    /* 传递给web时将地址id转化为地址 */
    private Address address;

    public User() {
    }

    public User(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getUserKind() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", address=" + address +
                '}';
    }
}