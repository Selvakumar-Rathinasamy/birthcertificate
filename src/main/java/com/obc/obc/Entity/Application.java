package com.obc.obc.Entity;
import java.sql.Timestamp;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "Application")
public class Application 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userid;
    private String applicationid;
    private String name;
    private String DoB;
    private String gender;
    private String birthplace;
    private String fathername;
    private String mothername;
    private String permanetadd;
    private String postaladd;
    private String mobno;
    private String email;
    private LocalDate applyieddate;
    private String remark;
    private String status;
    private Timestamp updationdate;
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getApplicationid() {
        return applicationid;
    }
    public void setApplicationid(String applicationid) {
        this.applicationid = applicationid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDoB() {
        return DoB;
    }
    public void setDoB(String DoB) {
        this.DoB=DoB;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getBirthplace() {
        return birthplace;
    }
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }
    public String getFathername() {
        return fathername;
    }
    public void setFathername(String fathername) {
        this.fathername = fathername;
    }
    public String getMothername() {
        return mothername;
    }
    public void setMothername(String mothername) {
        this.mothername = mothername;
    }
    public String getPermanetadd() {
        return permanetadd;
    }
    public void setPermanetadd(String permanetadd) {
        this.permanetadd = permanetadd;
    }
    public String getPostaladd() {
        return postaladd;
    }
    public void setPostaladd(String postaladd) {
        this.postaladd = postaladd;
    }
    public String getMobno() {
        return mobno;
    }
    public void setMobno(String mobno) {
        this.mobno=mobno;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getApplyieddate() {
        return applyieddate;
    }
    public void setApplyieddate(LocalDate applyieddate) {
        this.applyieddate = LocalDate.now();
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Timestamp getUpdationdate() {
        return updationdate;
    }
    public void setUpdationdate(Timestamp updationdate) {
        this.updationdate = new Timestamp(System.currentTimeMillis());
    }   
}
