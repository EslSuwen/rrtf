package com.cqjtu.rrtf;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/** @Author: suwen @Date: 2020/1/23 4:28 下午 */
public class Tests {

  @Test
  void contextLoads() {
    String date = "2019-02-11";
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 设置日期格式
    System.out.println(df.format(new Date())); // new Date () 为获取当前系统时间
    System.out.println(date);
  }
}
