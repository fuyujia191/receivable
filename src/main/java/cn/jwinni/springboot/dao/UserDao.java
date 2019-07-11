package cn.jwinni.springboot.dao;

import cn.jwinni.springboot.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户信息的数据访问层接口
 */
public interface UserDao {

    /**
     * 分页按条件查询客户信息
     *
     * @param name
     * @param phone
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<UserInfo> selectUserList(@Param("name") String name, @Param("phone") String phone, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 添加客户信息接口
     *
     * @param userInfo
     * @return
     */
    Long insertUserInfo(UserInfo userInfo);

    /**
     * 删除客户信息接口
     *
     * @param id
     * @return
     */
    Long deleteUserInfoById(@Param("id") Integer id);

    /**
     * 修改客户信息接口
     *
     * @param userInfo
     * @return
     */
    Long updateUserInfo(UserInfo userInfo);

    /**
     * 查询总条数接口
     *
     * @param name
     * @param phone
     * @return
     */
    Integer getRowCount(@Param("name") String name, @Param("phone") String phone);
}
