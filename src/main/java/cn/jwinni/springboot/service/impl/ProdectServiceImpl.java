package cn.jwinni.springboot.service.impl;

import cn.jwinni.springboot.common.PageObject;
import cn.jwinni.springboot.common.StringUtil;
import cn.jwinni.springboot.dao.ProdectDao;
import cn.jwinni.springboot.domain.Prodect;
import cn.jwinni.springboot.domain.UserInfo;
import cn.jwinni.springboot.domain.ValidateCode;
import cn.jwinni.springboot.service.ProdectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdectServiceImpl implements ProdectService {


    @Autowired
    private ProdectDao prodectMapper;

    /**
     * 项目列表（分页）
     *
     * @param projectname
     * @param pageCurrent
     * @return
     */
    @Override
    public Map<String, Object> selectProdectList(String projectname, Integer pageCurrent) {
        //1.通过dao对象的方法获取当前页项目信息
        //1.1定义每页最多显示2条数据
        int pageSize = 5;
        //1.2计算当前页开始查找的位置
        int startIndex = (pageCurrent - 1) * pageSize;
        //1.3开始查询当前页的数据
        List<Prodect> list =
                prodectMapper.selectProdectList(
                        projectname, startIndex, pageSize);
        //2.获得总记录数,计算总页数,然后进行封装
        //2.1 查询总记录数
        int rowCount =
                prodectMapper.getRowCount(projectname);
        //2.3封装分页信息(自己定义PageObject)
        PageObject pageObject = new PageObject();
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setStartIndex(startIndex);
        //3.将数据封装到map(两个对象需要传回页面)
        Map<String, Object> map =
                new HashMap<String, Object>();
        //3.1封装当前页数据
        map.put("list", list);
        //3.2封装分页对象信息
        map.put("pageObject", pageObject);
        return map;

    }

    /**
     * 添加项目
     *
     * @param prodect
     * @return
     */
    @Override
    public Long insertProdect(Prodect prodect) {

        Long ret = prodectMapper.insertProdect(prodect);
        return ret;
    }

    /**
     * 删除项目
     *
     * @param idstr
     * @return
     */
    @Override
    public Long deleteProdectById(String idstr) {
        Long ret = null;
        if (idstr != null && idstr.trim().length() > 0) {

            ret = prodectMapper.deleteProdectById(idstr);
        }
        return ret;
    }


    /**
     * 修改项目
     *
     * @param prodect
     * @return
     */
    @Override
    public Long updateProdect(Prodect prodect) {

        Long ret = prodectMapper.updateProdect(prodect);
        return ret;
    }

    /**
     * 通过id查询项目
     *
     * @param id
     * @return
     */
    @Override
    public Prodect getProdectById(Integer id) {
        Prodect p = prodectMapper.getProdectById(id);
        return p;
    }

    /**
     * 查询有效的项目
     *
     * @return
     */
    @Override
    public List<Prodect> getAffectiveProject() {
        return prodectMapper.getAffectiveProject();
    }

    /**
     * 保存验证码信息
     *
     * @param vcode
     * @return
     */
    @Override
    public Long insertVCode(ValidateCode vcode) {

        Long ret = prodectMapper.insertVCode(vcode);
        return ret;
    }

    /**
     * 校验验证码
     *
     * @param code
     * @param phone
     * @return
     */
    @Override
    public Integer checkVCode(String code, String phone) {
        return prodectMapper.checkVCode(code, phone);
    }

}
