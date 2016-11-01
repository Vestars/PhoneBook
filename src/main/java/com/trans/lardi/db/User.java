package com.trans.lardi.db;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    @Size(min = 3)
    @Pattern(regexp = "[a-zA-Z]+")
    private String username;

    @Size(min = 5)
    private String password;

    @Size(min = 5)
    private String fullname;

    private String authority;

    private boolean enabled = false;

    public User() {
    }

    public User(String username, String password, String fullname, String authority, boolean enabled) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.authority = authority;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
