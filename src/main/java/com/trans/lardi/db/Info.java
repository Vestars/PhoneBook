package com.trans.lardi.db;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Info {
    private long id;

    private long user_id;

    @Size(min=4)
    private String firstname;

    @Size(min=4)
    private String secondname;

    @Size(min=4)
    private String middlename;

    @Pattern(regexp = "^\\+380\\([0-9]{2}\\)[0-9]{7}")
    private String mobilephone;

    private String homephone;

    private String email;

    private String adress;

    public Info(){}

    public Info(long id, long user_id, String firstname, String secondname, String middlename, String mobilephone, String homephone, String email, String adress) {
        this.id = id;
        this.user_id = user_id;
        this.firstname = firstname;
        this.secondname = secondname;
        this.middlename = middlename;
        this.mobilephone = mobilephone;
        this.homephone = homephone;
        this.email = email;
        this.adress = adress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondName() {
        return secondname;
    }

    public void setSecondName(String secondname) {
        this.secondname = secondname;
    }

    public String getMiddleName() {
        return middlename;
    }

    public void setMiddleName(String middlename) {
        this.middlename = middlename;
    }

    public String getMobilePhone() {
        return mobilephone;
    }

    public void setMobilePhone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getHomePhone() {
        return homephone;
    }

    public void setHomePhone(String homephone) {
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
}
