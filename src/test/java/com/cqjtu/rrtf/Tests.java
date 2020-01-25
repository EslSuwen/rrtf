package com.cqjtu.rrtf;

import com.cqjtu.rrtf.service.ArticleService;
import com.cqjtu.rrtf.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** @Author: suwen @Date: 2020/1/23 4:28 下午 */
class Tests {

  @Test
  void contextLoads() {
    String date = "2019-02-11";
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 设置日期格式
    System.out.println(df.format(new Date())); // new Date () 为获取当前系统时间
    System.out.println(date);
  }

  @Test
  void loadAllArticl() {
    ArticleService articleService = new ArticleServiceImpl();

    System.out.println(articleService.loadAll().size());
  }

  @Test
  void listTest() {
    List<String> numbers = new ArrayList<>();

    for (int i = 1; i < 10; i++) {
      numbers.add("" + i);
    }

    for (int i = 6; i < numbers.size() - 1; i++) {
      numbers.remove(i);
    }

    for (String each : numbers) {
      System.out.println(each);
    }
  }

  @Test
  void testStringLength(){
    System.out.println("温格承诺追分施压切尔西 拒绝曼城挖角不卖小威".length());
  }

}
