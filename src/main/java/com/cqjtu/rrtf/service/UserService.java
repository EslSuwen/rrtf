package com.cqjtu.rrtf.service;

import com.cqjtu.rrtf.entity.User;

import java.util.List;


public interface UserService {

    /**
     * 获取一条用户数据
     *
     * @param  userNo
     * @return User
     *
     */
    User get(String userNo);


    /**
     * 新增一条用户数据
     *
     * @param  user
     * @return User
     *
     */
    void addUser(User user);


    /**
     * 删除一条用户数据记录
     *
     * @param  userId
     *
     */
    void removeUser(Integer userId);

    /**
     * 更新一条用户记录
     *
     * @param user
     *
     */
    void updateUser(User user);

    /**
     * 获取一条用户记录
     *
     * @param User
     * @return User
     *
     */
    User getOneUser(User User);

    /**
     *  获取所有用户记录
     *
     * @return List
     *
     */
    List<User> loadAll();

    /**
     * 更新用户头像
     * @param userNo 用户编号
     * @param avatar 图片字节数组
     */
    void upDateUserAvatar(String userNo,byte[] avatar);

    /**
     * 根据用户编号，获得用户头像
     *
     * @param userNo
     * @return byte[]
     *
     */
    byte[] getUserAvatar(String userNo);
}

