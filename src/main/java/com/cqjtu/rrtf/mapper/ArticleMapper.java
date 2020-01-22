package com.cqjtu.rrtf.mapper;

import com.cqjtu.rrtf.entity.Article;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
/** @Author: suwen @Date: 2020/1/22 3:03 下午 */
@org.apache.ibatis.annotations.Mapper
public interface ArticleMapper extends Mapper<Article> {
  /**
   * 根据编号查找文章
   *
   * @param artNo 文章编号
   * @return
   */
  @Select("select * from tbl_article where art_no = #{param1}")
  Article selectByArticleNo(String artNo);
}
