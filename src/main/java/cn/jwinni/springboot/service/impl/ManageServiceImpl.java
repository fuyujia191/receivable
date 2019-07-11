package cn.jwinni.springboot.service.impl;

import cn.jwinni.springboot.common.JsonResult;
import cn.jwinni.springboot.common.MD5;
import cn.jwinni.springboot.common.PageObject;
import cn.jwinni.springboot.dao.ManageDao;
import cn.jwinni.springboot.domain.Manage;
import cn.jwinni.springboot.domain.Prodect;
import cn.jwinni.springboot.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageDao manageMapper;


    /**
     * 系统登录
     * @param manageName
     * @param pwd
     * @return
     */
    @Override
    public Manage manageLogin(String manageName, String pwd) {
        Manage manage =  manageMapper.manageLogin(manageName);
        return manage;
    }

    /**
     * 管理员查询（分页）
     * @param telNo
     * @param pageCurrent
     * @return
     */
    @Override
    public Map<String, Object> selectManageList(String telNo, Integer pageCurrent) {
        //1.通过dao对象的方法获取当前页项目信息
        //1.1定义每页最多显示2条数据
        int pageSize=5;
        //1.2计算当前页开始查找的位置
        int startIndex=(pageCurrent-1)*pageSize;
        //1.3开始查询当前页的数据
        List<Manage> list=
                manageMapper.selectManageList(
                        telNo,startIndex,pageSize);
        //2.获得总记录数,计算总页数,然后进行封装
        //2.1 查询总记录数
        int rowCount=
                manageMapper.getRowCount(telNo);
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
}
