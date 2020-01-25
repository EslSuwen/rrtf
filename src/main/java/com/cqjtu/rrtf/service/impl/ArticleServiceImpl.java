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

    Article article = articleMapper.selectByPrimaryKey(artNo);
    article.setUser(userMapper.selectByPrimaryKey(article.getUserNo()));

    return article;
  }

  @Override
  public void addArticle(Article article) {

    articleMapper.insert(article);
  }

  @Override
  public List<Article> loadAll() {

    List<Article> articleList = articleMapper.selectAll();
    for (Article each : articleList) {
      each.setUser(userMapper.selectByPrimaryKey(each.getUserNo()));
    }

    return articleList;
  }

  @Override
  public List<Article> loadAllByUserNo(String userNo) {
    Article article = new Article();
    article.setUserNo(userNo);
    List<Article> articleList = articleMapper.select(article);
    for (Article each : articleList) {
      each.setUser(userMapper.selectByPrimaryKey(userNo));
    }
    return articleList;
  }

  @Override
  public void updateArticle(Article article) {
    articleMapper.updateByPrimaryKey(article);
  }

  @Override
  public void removeArticle(String artNo) {
    articleMapper.deleteByPrimaryKey(artNo);
  }

  @Override
  public List<Article> loadAllByType(String artType) {
    Article article = new Article();
    article.setArtType(artType);
    List<Article> articleList = articleMapper.select(article);
    for (Article each : articleList) {
      each.setUser(userMapper.selectByPrimaryKey(each.getUserNo()));
    }
    return articleList;
  }
}
