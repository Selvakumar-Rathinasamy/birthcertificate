package com.obc.obc.Entity;
import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String adminname;
    private String username;
    private long mobno;
    private String email;
    private String password;
    private Timestamp adminregdate;
    private String role;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getAdminname() {
        return adminname;
    }
    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public long getMobno() {
        return mobno;
    }
    public void setMobno(long mobno) {
        this.mobno = mobno;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Timestamp getAdminregdate() {
        return adminregdate;
    }
    public void setAdminregdate(Timestamp adminregdate) {
        this.adminregdate = adminregdate;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
