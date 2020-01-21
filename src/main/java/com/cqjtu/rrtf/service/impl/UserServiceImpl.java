package com.cqjtu.rrtf.service.impl;

import com.cqjtu.rrtf.entity.User;
import com.cqjtu.rrtf.mapper.UserMapper;
import com.cqjtu.rrtf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @author suwen
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(String userNo) {

        return userMapper.selectByPrimaryKey(userNo);

    }

    @Override
    public void addUser(User user) {

        userMapper.insert(user);

    }

    @Override
    public List<User> loadAll() {

        return userMapper.selectAll();
/**
 * 测试 mapper.select()
 *
 User user = new User();
 user.setUserNo("000007");
 user.setUserName("厂子7");

 List<User> userList = userMapper.select(user);

 Set<User> userSet = new TreeSet<>(Comparator.comparing(User::getUserName));
 userSet.addAll(userList);
 return new ArrayList<>(userSet);
 */

    }

    @Override
    public void removeUser(Integer userId) {

        userMapper.deleteByPrimaryKey(userId);

    }

    @Override
    public void updateUser(User user) {

        userMapper.updateByPrimaryKey(user);

    }

    @Override
    public User getOneUser(User user) {

        return userMapper.selectOne(user);

    }

    /**
     * 注解式
     * 自定义 mappper 方法调用
     *
     * @param userName
     * @return
     */
    public User getUserByName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    /**
     * 注解式
     * 自定义 mappper 方法调用
     * <p>
     * 更新用户头像
     *
     * @param userNo 用户编号
     * @param avatar 图片字节数组
     */
    @Override
    public void upDateUserAvatar(String userNo, byte[] avatar) {
        userMapper.upDateUserAvatar(userNo, avatar);
    }


    /**
     * 根据用户编号，获得用户头像
     *
     * @param userNo
     * @return byte[]
     */
    @Override
    public byte[] getUserAvatar(String userNo) {

        byte[] avatar = null;
        try {
            avatar = userMapper.selectByPrimaryKey(userNo).getUserAvatar();
        } catch (Exception e) {
            System.out.println("使用默认游客头像");
        }
        return avatar;

    }

    /**
     * @param userNo
     * @param pwd
     * @description: 更新用户密码
     * @return: void
     * @author: suwen
     * @time: 2020/1/21 8:40 下午
     */
    @Override
    public void upDateUserPwd(String userNo, String pwd) {
        userMapper.upDateUserPwd(userNo, pwd);
    }
}