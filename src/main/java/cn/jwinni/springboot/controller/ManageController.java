package cn.jwinni.springboot.controller;

import cn.jwinni.springboot.common.JsonResult;
import cn.jwinni.springboot.common.MD5;
import cn.jwinni.springboot.domain.Manage;
import cn.jwinni.springboot.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ManageController {

    @Autowired
    private ManageService manageService;

    /**
     * 登录页
     * @param req
     * @return
     */
    @RequestMapping("/")
    public String getRoot(HttpServletRequest req) {

        return "login";
    }

    @RequestMapping("/manageEditUI")
    public  String getManageEditUI(){

        return "userInfo/manage_edit";
    }
    /**
     * 登录
     * @param req
     * @param loginName
     * @param pwd
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult getLogin(HttpServletRequest req , String loginName, String pwd) {

        Manage manage = manageService.manageLogin(loginName,pwd);

        if (manage==null){
            return new JsonResult("账号不存在");
        }
        if (manage.getPassword()!=null && MD5.md5(pwd).equals(manage.getPassword())){

            //        HttpSession session = req.getSession();
            //        session.setAttribute("userToken", "admin");
            //        session.setMaxInactiveInterval(30*60);
            return new JsonResult();
        }
        else {

            return new JsonResult("密码错误");
        }

    }

    /**
     * 系统用户列表页面
     * @return
     */
    @RequestMapping("/manageListUI")
    public String manageUI() {
        return "userInfo/manage_list";
    }

    /**
     * 系统管理员列表
     * @param telNo
     * @param pageCurrent
     * @return
     */
    @ResponseBody
    @RequestMapping("/getManageList")
    public JsonResult getManageList(String telNo,  Integer pageCurrent) {

        Map<String, Object> users = manageService.selectManageList(telNo, pageCurrent);
        return new JsonResult(users);
    }
}
