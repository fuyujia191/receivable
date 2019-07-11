package cn.jwinni.springboot.service;

import cn.jwinni.springboot.domain.Manage;

import java.util.Map;

public interface ManageService {

    /**
     * 系统登录
     *
     * @param manageName
     * @param pwd
     * @return
     */
    Manage manageLogin(String manageName, String pwd);

    /**
     * 管理员查询（分页）
     *
     * @param telNo
     * @param pageCurrent
     * @return
     */
    Map<String, Object> selectManageList(String telNo, Integer pageCurrent);
}
