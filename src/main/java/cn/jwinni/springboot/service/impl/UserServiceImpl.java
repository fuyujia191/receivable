package cn.jwinni.springboot.service.impl;

import cn.jwinni.springboot.common.PageObject;
import cn.jwinni.springboot.dao.UserDao;
import cn.jwinni.springboot.domain.UserInfo;
import cn.jwinni.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userMapper;


    /**
     * 查询用户报名列表（分页）
     * @param name
     * @param phone
     * @param pageCurrent
     * @return
     */
    @Override
    public Map<String,Object> selectUserList(String name, String phone, Integer pageCurrent) {


        //1.通过dao对象的方法获取当前页项目信息
        //1.1定义每页最多显示2条数据
        int pageSize=5;
        //1.2计算当前页开始查找的位置
        int startIndex=(pageCurrent-1)*pageSize;
        //1.3开始查询当前页的数据
        int rowCount=
                userMapper.getRowCount(name,phone);
        List<UserInfo> list=
                userMapper.selectUserList(
                        name,phone,startIndex,pageSize);
        //2.获得总记录数,计算总页数,然后进行封装
        //2.1 查询总记录数
//        int rowCount=
//                userMapper.getRowCount(name,phone);
        //2.3封装分页信息(自己定义PageObject)
        PageObject pageObject=new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setStartIndex(startIndex);
        //3.将数据封装到map(两个对象需要传回页面)
        Map<String,Object> map=
                new HashMap<String,Object>();
        //3.1封装当前页数据
        map.put("list", list);
        //3.2封装分页对象信息
        map.put("pageObject", pageObject);
        return map;

    }

    /**
     * 用户报名
     * @param userInfo
     * @return
     */
    @Override
    public Long insertUserInfo(UserInfo userInfo) {
        return userMapper.insertUserInfo(userInfo);
    }

    /**
     * 删除用户报名信息
     * @param id
     * @return
     */
    @Override
    public Long deleteUserInfoById(Integer id) {
        return userMapper.deleteUserInfoById(id);
    }

    /**
     * 修改用户报名信息
     * @param userInfo
     * @return
     */
    @Override
    public Long updateUserInfo(UserInfo userInfo) {
        return userMapper.updateUserInfo(userInfo);
    }

    /**
     * 查询报名总条数
     * @param name
     * @param phone
     * @return
     */
    @Override
    public Integer getRowCount(String name, String phone) {
        return null;
    }
}
