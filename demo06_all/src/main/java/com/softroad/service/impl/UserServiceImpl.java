package com.softroad.service.impl;

import com.softroad.mapper.UsersMapper;
import com.softroad.pojo.Users;
import com.softroad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> selectAllUser() {
        return usersMapper.selectAllUser();
    }

    @Override
    public Users selectByUserId(int userid) {
        return usersMapper.selectByPrimaryKey(userid);
    }

    @Override
    public List<Users> pageSelect(int start, int pageSize) {
        return usersMapper.pageSelect(start,pageSize);
    }

    @Override
    public int pageSize() {
        return usersMapper.pageSize();
    }

    @Override
    @Transactional
    public int deleteUser(Integer userid) {

       Users users =  selectByUserId(userid);

       if(users == null){
            int a = 10 / 0;
       }

        return usersMapper.deleteByPrimaryKey(userid);
    }

    @Override
    @Transactional
    public int updateUser(Users users) {
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    @Transactional
    public int addUser(Users users) {
        return usersMapper.insertSelective(users);
    }

    @Override
    public List<Users> selectByName(String username) {
        return usersMapper.selectByName(username);
    }

    @Override
    public Users loginUser(int userid) {
        return usersMapper.loginUser(userid);
    }
}
