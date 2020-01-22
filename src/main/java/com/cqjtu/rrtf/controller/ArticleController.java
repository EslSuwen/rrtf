package com.cqjtu.rrtf.controller;

import com.cqjtu.rrtf.entity.Article;
import com.cqjtu.rrtf.entity.User;
import com.cqjtu.rrtf.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/** @Author: suwen @Date: 2020/1/22 3:21 下午 */
@Controller
@RequestMapping("/article")
public class ArticleController {

  @Autowired private ArticleService articleService;

  /**
   * 访问文章增加页面
   *
   * @param map 参数集
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/22 3:22 下午
   */
  @GetMapping("/toInput")
  public String input(Map<String, Object> map) {
    map.put("user", new User());

    return "登陆注册/注册页";
  }

  /**
   * 新增文章
   *
   * @param article 文章
   * @param request http 请求
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/22 3:24 下午
   */
  @PostMapping(value = "/create")
  public String create(Article article, HttpServletRequest request) {

    System.out.println(article);
    articleService.addArticle(article);
    // 获取session并将userName存入session对象

    return "redirect:/";
  }

  //  @GetMapping("/myArticle")
  public String myArticle(Map<String, Object> map) {

    Article article = articleService.getArticle("-1");
    System.out.println("article: " + article.getArtNo() + article.getArtText());

    List<Article> articleList = articleService.loadAll();
    map.put("article", article);
    map.put("articleList", articleList);

    return "个人资料/用户中心-我的托福人";
  }

  @RequestMapping("/myArticle")
  public String list(
      @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
      Map<String, Object> map) {

    // 对 pageNo 的校验
    int pageNo = Integer.parseInt(pageNoStr);
    if (pageNo < 1) {
      pageNo = 1;
    }

    PageHelper.startPage(pageNo, 3);
    List<Article> articleList = articleService.loadAll();
    PageInfo<Article> page = new PageInfo<Article>(articleList);

    map.put("page", page);
    map.put("area", "newclass");

    return "个人资料/用户中心-我的托福人";
  }

  @GetMapping("/followArticle")
  public String followArticle(Map<String, Object> map) {
    map.put("user", new User());

    return "登陆注册/注册页";
  }
}
