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

  /**
   * 访问托福人文章
   *
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/24 2:19 下午
   */
  @GetMapping("/Article")
  public String myArticle() {

    return "个人资料/用户中心-我的托福人";
  }

  /**
   * 访问最新发布文章
   *
   * @param pageNoStr 页码
   * @param map 参数集
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/24 2:12 下午
   */
  @RequestMapping("newArticle")
  public String newArticle(
      @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
      Map<String, Object> map) {

    // 对 pageNo 的校验
    int pageNo = Integer.parseInt(pageNoStr);
    if (pageNo < 1) {
      pageNo = 1;
    }

    PageHelper.startPage(pageNo, 3);
    List<Article> articleList = articleService.loadAll();
    System.out.println(articleList.size());

    // 文章字数限制
    for (Article each : articleList) {
      if (each.getArtText().length() >= 200) {
        each.setArtText(each.getArtText().substring(0, 200) + "......");
      }
    }
    PageInfo<Article> page = new PageInfo<>(articleList);

    map.put("page", page);

    return "托福人/newArticle";
  }

  /**
   * 访问用户发布文章
   *
   * @param pageNoStr 页码
   * @param map 参数集
   * @param request http 请求
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/24 3:56 下午
   */
  @RequestMapping("myArticle")
  public String myArticle(
      @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
      Map<String, Object> map,
      HttpServletRequest request) {

    User user = (User) request.getSession().getAttribute("USER_SESSION_KEY");
    String userNo = user.getUserNo();

    // 对 pageNo 的校验
    int pageNo = Integer.parseInt(pageNoStr);
    if (pageNo < 1) {
      pageNo = 1;
    }

    PageHelper.startPage(pageNo, 3);
    List<Article> articleList;
    if (user.getUserTab().equals("0")) {
      articleList = articleService.loadAll();
    } else {
      articleList = articleService.loadAllByUserNo(userNo);
    }

    // 文章字数限制
    for (Article each : articleList) {

      if (!each.getUserNo().equals(userNo)) {
        continue;
      }
      if (each.getArtText().length() >= 200) {
        each.setArtText(each.getArtText().substring(0, 200) + "......");
      }
    }
    PageInfo<Article> page = new PageInfo<>(articleList);

    map.put("page", page);

    return "托福人/myArticle";
  }

  /**
   * @param map
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/24 2:12 下午
   */
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

  /**
   * 用户删除文章
   *
   * @param artNo 文章编号
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/24 4:44 下午
   */
  @GetMapping(value = "/remove/{artNo}")
  public String remove(@PathVariable("userNo") Integer userNo) {

    return "redirect:/";
  }

  /**
   * 用户访问文章修改页面
   *
   * @param artNo 文章编号
   * @param map
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/24 4:46 下午
   */
  @GetMapping(value = "/preUpdate/{artNo}")
  public String preUpdate(@PathVariable("artNo") String artNo, Map<String, Object> map) {

    map.put("article", articleService.getArticle(artNo));

    return "托福人/editArticle";
  }

  @RequestMapping(value = "/update")
  public String update(@RequestParam("artImgUpload") MultipartFile file, Article article)
      throws Exception {

    Article oldArticle = articleService.getArticle(article.getArtNo());
    oldArticle.setArtTitle(article.getArtTitle());
    oldArticle.setArtText(article.getArtText());
    oldArticle.setArtType(article.getArtType());

    // 读取文件数据，转成字节数组
    if (!file.isEmpty()) {
      System.out.println("file is not null");
      oldArticle.setArtImg(file.getBytes());
    }

    articleService.updateArticle(oldArticle);

    return "redirect:/";
  }
}
