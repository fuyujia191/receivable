package cn.jwinni.springboot.service.impl;

import cn.jwinni.springboot.dao.UserDao;
import cn.jwinni.springboot.domain.userInfo;
import cn.jwinni.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userMapper;

    @Override
    public List getAllUser() {

        List list = userMapper.selectAllUser();
        return list;
    }
}
