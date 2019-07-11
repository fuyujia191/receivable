package cn.jwinni.springboot.service;

import cn.jwinni.springboot.domain.Prodect;
import cn.jwinni.springboot.domain.ValidateCode;

import java.util.List;
import java.util.Map;

public interface ProdectService {

    /**
     * 项目列表（分页）
     *
     * @param projectname
     * @param pageCurrent
     * @return
     */
    Map<String, Object> selectProdectList(String projectname, Integer pageCurrent);

    /**
     * 添加项目
     *
     * @param prodect
     * @return
     */
    Long insertProdect(Prodect prodect);

    /**
     * 删除项目
     *
     * @param ids
     * @return
     */
    Long deleteProdectById(String ids);

    /**
     * 修改项目
     *
     * @param prodect
     * @return
     */
    Long updateProdect(Prodect prodect);


    /**
     * 通过id查询项目
     *
     * @param id
     * @return
     */
    Prodect getProdectById(Integer id);

    /**
     * 查询有效的项目
     *
     * @return
     */
    List<Prodect> getAffectiveProject();

    /**
     * 保存验证码信息
     *
     * @param vcode
     * @return
     */
    Long insertVCode(ValidateCode vcode);

    /**
     * 校验验证码
     *
     * @param code
     * @param phone
     * @return
     */
    Integer checkVCode(String code, String phone);
}
