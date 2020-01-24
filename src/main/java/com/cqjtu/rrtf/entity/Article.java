package com.cqjtu.rrtf.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/** @Author: suwen @Date: 2020/1/22 2:48 下午 */
@Component
@Table(name = "tbl_article")
public class Article {

  /** 文章编号 */
  @Id
  @Column(name = "art_no")
  private String artNo;

  /** 文章所属用户账号 */
  @Column(name = "user_no")
  private String userNo;

  /** 文章所属用户 */
  @Transient private User user;

  /** 文章标题 */
  @Column(name = "art_title")
  private String artTitle;

  /** 文章类型 */
  @Column(name = "art_type")
  private String artType;

  /** 文章发表日期 */
  @Column(name = "art_date")
  private String artDate;

  /** 文章图片 */
  @Column(name = "art_img")
  private byte[] artImg;

  /** 文章内容 */
  @Column(name = "art_text")
  private String artText;

  public Article() {}

  public Article(
      String artNo,
      String userNo,
      User user,
      String artTitle,
      String artType,
      String artDate,
      byte[] artImg,
      String artText) {
    this.artNo = artNo;
    this.userNo = userNo;
    this.user = user;
    this.artTitle = artTitle;
    this.artType = artType;
    this.artDate = artDate;
    this.artImg = artImg;
    this.artText = artText;
  }

  public String getArtNo() {
    return artNo;
  }

  public void setArtNo(String artNo) {
    this.artNo = artNo;
  }

  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getArtTitle() {
    return artTitle;
  }

  public void setArtTitle(String artTitle) {
    this.artTitle = artTitle;
  }

  public String getArtType() {
    return artType;
  }

  public void setArtType(String artType) {
    this.artType = artType;
  }

  public String getArtDate() {
    return artDate;
  }

  public void setArtDate(String artDate) {
    this.artDate = artDate;
  }

  public byte[] getArtImg() {
    return artImg;
  }

  public void setArtImg(byte[] artImg) {
    this.artImg = artImg;
  }

  public String getArtText() {
    return artText;
  }

  public void setArtText(String artText) {
    this.artText = artText;
  }
}
