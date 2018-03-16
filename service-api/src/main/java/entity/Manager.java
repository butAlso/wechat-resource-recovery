package entity;

import dto.Address;

public class Manager extends User {
    private final Integer userKind = 3;
    private String gender;

    public Manager() {
    }

    public Manager(Integer id, String name) {
        super(id, name);
    }

    public Manager(String name, String password) {
        super(name, password);
    }

    public Manager(String name, String password, String gender,
                   String phone, Address address) {
        super(name, password);
        this.gender = gender;
        this.setPhone(phone);
        this.setAddress(address);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public Integer getUserKind() {
        return userKind;
    }

    @Override
    public String toString() {
        return "Manager{" +
                super.toString() +
                "userKind=" + userKind +
                '}';
    }
}
