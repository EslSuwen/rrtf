package com.cqjtu.rrtf.service;

import com.cqjtu.rrtf.entity.Article;

import java.util.List;

/** @Author: suwen @Date: 2020/1/22 3:06 下午 */
public interface ArticleService {

  /**
   * 查找一条文章数据
   *
   * @param artNo 文章编号
   * @return: com.cqjtu.rrtf.entity.Article
   * @author: suwen
   * @time: 2020/1/22 3:09 下午
   */
  Article getArticle(String artNo);

  /**
   * 增加一条文章数据
   *
   * @param article 文章
   * @return: void
   * @author: suwen
   * @time: 2020/1/22 3:10 下午
   */
  void addArticle(Article article);

  /**
   * 更新文章
   *
   * @param article 文章
   * @return: void
   * @author: suwen
   * @time: 2020/1/24 6:13 下午
   */
  void updateArticle(Article article);

  /**
   * 获取所有文章记录
   *
   * @return: java.util.List<com.cqjtu.rrtf.entity.Article>
   * @author: suwen
   * @time: 2020/1/22 4:42 下午
   */
  List<Article> loadAll();

  /**
   * 根据作者获取其所有文章记录
   *
   * @param userNo 作者编号
   * @return: java.util.List<com.cqjtu.rrtf.entity.Article>
   * @author: suwen
   * @time: 2020/1/22 4:42 下午
   */
  List<Article> loadAllByUserNo(String userNo);

  /**
   * 删除一条用户数据记录
   *
   * @param userId
   */
  /*void removeUser(Integer userId);

  */
  /**
   * 更新一条用户记录
   *
   * @param article
   */
  /*
  void updateUser(Article article);

  */
  /**
   * 获取一条用户记录
   *
   * @param article
   * @return Article
   */
  /*
  Article getOneUser(Article article);

  */
  /**
   * 获取所有用户记录
   *
   * @return List
   */
  /*
  List<Article> loadAll();

  */
  /**
   * 更新用户头像
   *
   * @param userNo 用户编号
   * @param avatar 图片字节数组
   */
  /*
  void upDateUserAvatar(String userNo, byte[] avatar);

  */
  /**
   * 根据用户编号，获得用户头像
   *
   * @param userNo
   * @return byte[]
   */
  /*
  byte[] getUserAvatar(String userNo);

  */
  /**
   * @param userNo
   * @param pwd
   * @description: 更新用户密码
   * @return: void
   * @author: suwen
   * @time: 2020/1/21 8:40 下午
   */
  /*
  void upDateUserPwd(String userNo, String pwd);*/
}
