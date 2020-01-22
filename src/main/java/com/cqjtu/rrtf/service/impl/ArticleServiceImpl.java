package com.cqjtu.rrtf.service.impl;

import com.cqjtu.rrtf.entity.Article;
import com.cqjtu.rrtf.mapper.ArticleMapper;
import com.cqjtu.rrtf.mapper.UserMapper;
import com.cqjtu.rrtf.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** @Author: suwen @Date: 2020/1/22 3:17 下午 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

  @Autowired private ArticleMapper articleMapper;

  @Autowired private UserMapper userMapper;

  @Override
  public Article getArticle(String artNo) {

    return articleMapper.selectByPrimaryKey(artNo);
  }

  @Override
  public void addArticle(Article article) {

    articleMapper.insert(article);
  }

  @Override
  public List<Article> loadAll() {

    List<Article> articleList= articleMapper.selectAll();
    for (Article each : articleList) {
      each.setUser(userMapper.selectByPrimaryKey(each.getUserNo()));
    }

    return articleList;
  }
}
