package com.cqjtu.rrtf.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/** @Author: suwen @Date: 2020/1/26 12:39 下午 */
@Component
@Table(name = "tbl_comment")
public class Comment {

  @Id
  @Column(name = "cmt_no")
  private String cmtNo;

  @Column(name = "cmt_user_no")
  private String cmtUserNo;

  @Column(name = "cmt_art_no")
  private String cmtArtNo;

  @Column(name = "cmt_date")
  private String cmtDate;

  @Column(name = "cmt_text")
  private String cmtText;

  @Transient private User user;

  public Comment() {}

  public Comment(String cmtNo, String cmtUserNo, String cmtArtNo, String cmtDate, String cmtText) {
    this.cmtNo = cmtNo;
    this.cmtUserNo = cmtUserNo;
    this.cmtArtNo = cmtArtNo;
    this.cmtDate = cmtDate;
    this.cmtText = cmtText;
  }

  public String getCmtNo() {
    return cmtNo;
  }

  public void setCmtNo(String cmtNo) {
    this.cmtNo = cmtNo;
  }

  public String getCmtUserNo() {
    return cmtUserNo;
  }

  public void setCmtUserNo(String cmtUserNo) {
    this.cmtUserNo = cmtUserNo;
  }

  public String getCmtArtNo() {
    return cmtArtNo;
  }

  public void setCmtArtNo(String cmtArtNo) {
    this.cmtArtNo = cmtArtNo;
  }

  public String getCmtDate() {
    return cmtDate;
  }

  public void setCmtDate(String cmtDate) {
    this.cmtDate = cmtDate;
  }

  public String getCmtText() {
    return cmtText;
  }

  public void setCmtText(String cmtText) {
    this.cmtText = cmtText;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
