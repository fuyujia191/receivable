package cn.jwinni.springboot.dao;

import cn.jwinni.springboot.domain.Manage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户的数据访问层接口
 */
public interface ManageDao {


    /**
     * 系统用户登录接口
     *
     * @param manageName
     * @return
     */
    Manage manageLogin(@Param("manageName") String manageName);

    /**
     * 系统用户分页查询接口
     *
     * @param telNo
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Manage> selectManageList(@Param("telNo") String telNo, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 系统用户查询总条数接口
     *
     * @param telNo
     * @return
     */
    Integer getRowCount(@Param("telNo") String telNo);

}
