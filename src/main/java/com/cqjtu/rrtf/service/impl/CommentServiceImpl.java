package com.cqjtu.rrtf.service.impl;

import com.cqjtu.rrtf.entity.Comment;
import com.cqjtu.rrtf.mapper.CommentMapper;
import com.cqjtu.rrtf.mapper.UserMapper;
import com.cqjtu.rrtf.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** @Author: suwen @Date: 2020/1/26 12:44 下午 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

  @Autowired private CommentMapper commentMapper;
  @Autowired private UserMapper userMapper;

  @Override
  public void addComment(Comment comment) {
    commentMapper.insert(comment);
  }

  @Override
  public void removeComment(String cmtNo) {
    commentMapper.deleteByPrimaryKey(cmtNo);
  }

  @Override
  public void updateComment(Comment comment) {
    commentMapper.updateByPrimaryKey(comment);
  }

  @Override
  public List<Comment> loadAllByArtNo(String artNo) {
    Comment comment = new Comment();
    comment.setCmtArtNo(artNo);
    List<Comment> commentList = commentMapper.select(comment);
    for (Comment each : commentList) {
      each.setUser(userMapper.selectByPrimaryKey(each.getCmtUserNo()));
    }
    return commentList;
  }
}
