package com.cqjtu.rrtf.controller;

import com.cqjtu.rrtf.entity.Article;
import com.cqjtu.rrtf.entity.User;
import com.cqjtu.rrtf.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    map.put("article", new Article());

    return "托福人/托福人发布页";
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
  public String create(
      @RequestParam("artImgUpload") MultipartFile file, Article article, HttpServletRequest request)
      throws Exception {

    System.out.println(article.getArtTitle());
    System.out.println(article.getArtType());
    System.out.println(article.getArtText());

    User user = (User) request.getSession().getAttribute("USER_SESSION_KEY");
    article.setUserNo(user.getUserNo());

    // 设置日期格式 new Date () 为获取当前系统时间
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    article.setArtDate(df.format(new Date()));

    // 读取文件数据，转成字节数组
    if (file != null) {
      System.out.println("file is not null");
      article.setArtImg(file.getBytes());
    }

    articleService.addArticle(article);

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

    // 文章字数限制
    for (Article each : articleList) {
      if (each.getArtText().length() >= 200) {
        each.setArtText(each.getArtText().substring(0, 200) + "......");
      }
    }
    PageInfo<Article> page = new PageInfo<>(articleList);

    map.put("page", page);
    map.put("area", "newclass");

    return "个人资料/用户中心-我的托福人";
  }

  @GetMapping("/followArticle")
  public String followArticle(Map<String, Object> map) {
    map.put("user", new User());

    return "登陆注册/注册页";
  }

  /**
   * 根据文章编号获得文章图片
   *
   * @param artNo 文章编号
   * @param response Http 响应消息
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/23 6:16 下午
   */
  @GetMapping(value = "/getArtImg/{artNo}")
  public String getArtImg(@PathVariable(value = "artNo") String artNo, HttpServletResponse response)
      throws Exception {

    byte[] artImg = null;
    try {
      artImg = articleService.getArticle(artNo).getArtImg();
    } catch (Exception e) {
      System.out.println("使用默认文章头像");
    }

    if (artImg == null) {
      artImg = articleService.getArticle("-1").getArtImg();
    }

    // 向浏览器发通知，我要发送是图片
    response.setContentType("image/jpg");
    ServletOutputStream sos = response.getOutputStream();
    sos.write(artImg);
    sos.flush();
    sos.close();

    return null;
  }

  /**
   * 根据文章编号获得文章详情
   *
   * @param artNo 文章编号
   * @param map 参数集
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/23 6:32 下午
   */
  @GetMapping(value = "/getArtInfo/{artNo}")
  public String getArticleInfo(
      @PathVariable(value = "artNo") String artNo, Map<String, Object> map) {

    map.put("article", articleService.getArticle(artNo));

    return "托福人/托福人详情页";
  }
}
