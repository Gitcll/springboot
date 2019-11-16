package com.softroad.service;
import com.softroad.pojo.Users;
import java.util.List;

public interface UserService {

    //查询全部用户
    List<Users>  selectAllUser();

    //分页查询
    List<Users> pageSelect(int start, int pageSize);

    //计算总数据量
    int pageSize();

    //通过ID查询用户
    Users selectByUserId(int userid);

    //删除用户
    int deleteUser(Integer userid);

    //修改用户信息
    int updateUser(Users users);

    //添加用户
    int addUser(Users users);

    //通过Name查询用户
    List<Users> selectByName(String username);

    //用户登陆
    Users loginUser(int userid);

}
