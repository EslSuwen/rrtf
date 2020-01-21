package com.cqjtu.rrtf.mapper;


import com.cqjtu.rrtf.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author suwen
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {
    /**
     * @param username
     * @return
     */
    @Select("select * from users where username = #{username}")
    User selectByUserName(String username);

    /**
     * 更新用户头像
     *
     * @param userNo 用户编号
     * @param avatar 图片字节数组
     */
    @Select("update tbl_user set user_avatar=#{param2} where user_no=#{param1}")
    void upDateUserAvatar(String userNo, byte[] avatar);

    /**
     * @param userNo
     * @param pwd
     * @description: 更新用户密码
     * @return: void
     * @author: suwen
     * @time: 2020/1/21 8:40 下午
     */
    @Select("update tbl_user set user_pwd=#{param2} where user_no=#{param1}")
    void upDateUserPwd(String userNo, String pwd);
}
