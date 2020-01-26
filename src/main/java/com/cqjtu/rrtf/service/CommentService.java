package com.cqjtu.rrtf.service;

import com.cqjtu.rrtf.entity.Comment;

import java.util.List;

/** @Author: suwen @Date: 2020/1/26 12:44 下午 */
public interface CommentService {

  /**
   * 增加一条评论记录
   *
   * @param comment 评论
   * @return: void
   * @author: suwen
   * @time: 2020/1/26 12:48 下午
   */
  void addComment(Comment comment);

  /**
   * 删除一条评论记录
   *
   * @param cmtNo 评论编号
   * @return: void
   * @author: suwen
   * @time: 2020/1/26 12:48 下午
   */
  void removeComment(String cmtNo);

  /**
   * 更新一条评论记录
   *
   * @param comment 评论
   * @return: void
   * @author: suwen
   * @time: 2020/1/26 12:48 下午
   */
  void updateComment(Comment comment);

  /**
   * 根据文章编号获得所有评论
   *
   * @param artNo 文章编号
   * @return: java.util.List<com.cqjtu.rrtf.entity.Comment>
   * @author: suwen
   * @time: 2020/1/26 12:49 下午
   */
  List<Comment> loadAllByArtNo(String artNo);
}
