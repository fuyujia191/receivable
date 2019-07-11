package cn.jwinni.springboot.dao;

import cn.jwinni.springboot.domain.Prodect;

import cn.jwinni.springboot.domain.ValidateCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 夏令营项目的数据访问层接口
 */
public interface ProdectDao {

    /**
     * 夏令营项目分页查询接口
     *
     * @param projectName
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Prodect> selectProdectList(@Param("projectName") String projectName, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 夏令营项目添加接口
     *
     * @param prodect
     * @return
     */
    Long insertProdect(Prodect prodect);

    /**
     * 夏令营项目删除接口
     *
     * @param ids
     * @return
     */
    Long deleteProdectById(@Param("ids") String ids);

    /**
     * 夏令营项目更新接口
     *
     * @param prodect
     * @return
     */
    Long updateProdect(Prodect prodect);

    /**
     * 夏令营项目查询总条数接口
     *
     * @param projectName
     * @return
     */
    Integer getRowCount(@Param("projectName") String projectName);

    /**
     * 夏令营项目通过id查询项目接口
     *
     * @param id
     * @return
     */
    Prodect getProdectById(Integer id);

    /**
     * 查询有效的夏令营项目接口（微信页面夏令营下拉框）
     */
    List<Prodect> getAffectiveProject();

    /**
     * 保存验证码信息接口
     *
     * @param vcode
     * @return
     */
    Long insertVCode(ValidateCode vcode);

    /**
     * 校验验证码接口
     *
     * @param code
     * @param phone
     * @return
     */
    Integer checkVCode(@Param("code") String code, @Param("phone") String phone);
}
