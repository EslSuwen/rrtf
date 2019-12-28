package com.cqjtu.rrtf.entity;


import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Component
@Table(name = "tbl_user")
public class User implements Serializable {

    @Id
    @Column(name = "user_no")
    private String userNo;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_tab")
    private String userTab;

    @Column(name = "user_email")
    private String userEmail;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserTab() {
        return userTab;
    }

    public void setUserTab(String userTab) {
        this.userTab = userTab;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return this.userNo + this.userName + this.userPwd + this.userTab;
    }


}
