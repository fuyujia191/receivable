package cn.jwinni.springboot.service;

import cn.jwinni.springboot.domain.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 查询用户报名列表（分页）
     *
     * @param name
     * @param phone
     * @param pageCurrent
     * @return
     */
    Map<String, Object> selectUserList(String name, String phone, Integer pageCurrent);

    /**
     * 用户报名
     *
     * @param userInfo
     * @return
     */
    Long insertUserInfo(UserInfo userInfo);

    /**
     * 删除用户报名信息
     *
     * @param id
     * @return
     */
    Long deleteUserInfoById(Integer id);

    /**
     * 修改用户报名信息
     *
     * @param userInfo
     * @return
     */
    Long updateUserInfo(UserInfo userInfo);

    /**
     * 查询报名总条数
     *
     * @param name
     * @param phone
     * @return
     */
    Integer getRowCount(String name, String phone);
}
