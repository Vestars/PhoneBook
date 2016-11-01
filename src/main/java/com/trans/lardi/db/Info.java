package com.trans.lardi.db;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Info {

    private long id;

    @Size(min = 4)
    private String firstname;

    @Size(min = 4)
    private String secondname;

    @Size(min = 4)
    private String middlename;

    @Pattern(regexp = "^\\+380\\([0-9]{2}\\)[0-9]{7}")
    private String mobilephone;

    private String homephone;

    private String email;

    private String adress;

    private String users_name;

    public Info() {
    }

    public Info(long id, String firstname, String secondname, String middlename, String mobilephone, String homephone, String email, String adress, String users_name) {
        this.id = id;
        this.firstname = firstname;
        this.secondname = secondname;
        this.middlename = middlename;
        this.mobilephone = mobilephone;
        this.homephone = homephone;
        this.email = email;
        this.adress = adress;
        this.users_name = users_name;
    }

    public Info(String firstname, String secondname, String middlename, String mobilephone, String homephone, String email, String adress, String users_name) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.middlename = middlename;
        this.mobilephone = mobilephone;
        this.homephone = homephone;
        this.email = email;
        this.adress = adress;
        this.users_name = users_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }
}