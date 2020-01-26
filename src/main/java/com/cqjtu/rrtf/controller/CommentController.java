package com.cqjtu.rrtf.controller;

import com.cqjtu.rrtf.entity.Comment;
import com.cqjtu.rrtf.entity.User;
import com.cqjtu.rrtf.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** @Author: suwen @Date: 2020/1/26 1:00 下午 */
@Controller
@RequestMapping("/cmt")
public class CommentController {

  @Autowired private CommentService commentService;

  /**
   * 增加评论
   *
   * @param comment 评论
   * @param request http 请求
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/26 1:08 下午
   */
  @PostMapping(value = "/create/{artNo}")
  public String createComment(
      @PathVariable(name = "artNo") String artNo, Comment comment, HttpServletRequest request) {

    User user = (User) request.getSession().getAttribute("USER_SESSION_KEY");

    // 设置日期格式 new Date () 为获取当前系统时间
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    comment.setCmtDate(df.format(new Date()));
    comment.setCmtArtNo(artNo);
    comment.setCmtUserNo(user.getUserNo());

    commentService.addComment(comment);

    return "redirect:/cmt//getCmts/" + artNo;
  }

  /**
   * 根据文章编号获得评论
   *
   * @param artNo 文章编号
   * @param pageNoStr 页码
   * @param map 参数集
   * @return: java.lang.String
   * @author: suwen
   * @time: 2020/1/26 1:14 下午
   */
  @RequestMapping("/getCmts/{artNo}")
  public String getCmtsByArtNo(
      @PathVariable(name = "artNo") String artNo,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
      Map<String, Object> map) {

    // 对 pageNo 的校验
    int pageNo = Integer.parseInt(pageNoStr);
    if (pageNo < 1) {
      pageNo = 1;
    }

    PageHelper.startPage(pageNo, 3);
    List<Comment> commentList = commentService.loadAllByArtNo(artNo);

    PageInfo<Comment> page = new PageInfo<>(commentList);

    for (Comment each : commentList) {
      System.out.println(each.getCmtUserNo());
    }
    map.put("page", page);
    map.put("artNo", artNo);
    map.put("newComment", new Comment());

    return "托福人/comment";
  }
}
