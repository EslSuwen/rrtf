package com.cqjtu.rrtf.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/** @Author: suwen @Date: 2020/1/22 2:48 下午 */
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

  @Column(name = "user_sign")
  private String userSign;

  @Column(name = "user_sex")
  private String userSex;

  @Column(name = "user_tel")
  private String userTel;

  @Column(name = "user_birth")
  private String userBirth;

  @Column(name = "user_avatar")
  private byte[] userAvatar;

  public User(String userNo, String userName, String userPwd, String userTab, String userEmail) {
    this.userNo = userNo;
    this.userName = userName;
    this.userPwd = userPwd;
    this.userTab = userTab;
    this.userEmail = userEmail;
  }

  public User() {}

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

  public User(
      String userNo,
      String userName,
      String userPwd,
      String userTab,
      String userEmail,
      String userSign,
      String userSex,
      String userTel,
      String userBirth,
      byte[] userAvatar) {
    this.userNo = userNo;
    this.userName = userName;
    this.userPwd = userPwd;
    this.userTab = userTab;
    this.userEmail = userEmail;
    this.userSign = userSign;
    this.userSex = userSex;
    this.userTel = userTel;
    this.userBirth = userBirth;
    this.userAvatar = userAvatar;
  }

  public String getUserSign() {
    return userSign;
  }

  public void setUserSign(String userSign) {
    this.userSign = userSign;
  }

  public String getUserSex() {
    return userSex;
  }

  public void setUserSex(String userSex) {
    this.userSex = userSex;
  }

  public String getUserTel() {
    return userTel;
  }

  public void setUserTel(String userTel) {
    this.userTel = userTel;
  }

  public String getUserBirth() {
    return userBirth;
  }

  public void setUserBirth(String userBirth) {
    this.userBirth = userBirth;
  }

  public byte[] getUserAvatar() {
    return userAvatar;
  }

  public void setUserAvatar(byte[] userAvatar) {
    this.userAvatar = userAvatar;
  }

  @Override
  public String toString() {
    return "userNo: "
        + this.userNo
        + " userName: "
        + this.userName
        + " userPwd: "
        + this.userPwd
        + "\nuserEmail:"
        + this.userEmail
        + " userTab: "
        + this.userTab;
  }
}
