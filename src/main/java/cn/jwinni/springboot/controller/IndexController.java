package cn.jwinni.springboot.controller;

import cn.jwinni.springboot.common.JsonResult;
import cn.jwinni.springboot.domain.Prodect;
import cn.jwinni.springboot.domain.UserInfo;
import cn.jwinni.springboot.service.ProdectService;
import cn.jwinni.springboot.service.UserService;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProdectService prodectService;


    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/index")
    public String getIndex() {
        return "index";
    }

    /**
     * 列表
     *
     * @return
     */
    @RequestMapping("/userlistUI")
    public String listUI() {
        return "userInfo/userInfo_list";
    }

    /**
     * 获取报名列表(分页)
     *
     * @param name
     * @param phone
     * @param pageCurrent
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserList")
    public JsonResult getUserList(String name, String phone, Integer pageCurrent) {

        Map<String, Object> users = userService.selectUserList(name, phone, pageCurrent);
        return new JsonResult(users);
    }

    /**
     * 项目列表页面页面
     *
     * @return
     */
    @RequestMapping("/prodectListUI")
    public String projectUI() {
        return "userInfo/project_list";
    }

    /**
     * 获取项目列表(分页)
     *
     * @param name
     * @param pageCurrent
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProdectList")
    public JsonResult getProdectList(String name, Integer pageCurrent) {

        Map<String, Object> users = prodectService.selectProdectList(name, pageCurrent);
        return new JsonResult(users);
    }


    /**
     * 项目编辑页面
     *
     * @return
     */
    @RequestMapping("/projectEditUI")
    public String projectEditUI() {
        return "userInfo/project_edit";
    }

    /**
     * 添加项目
     *
     * @param projectName
     * @param price
     * @param startDate
     * @param endDate
     * @return
     */
    @ResponseBody
    @RequestMapping("/InsertProject")
    public JsonResult insertProdect(String projectName, String price, String startDate, String endDate) {
        Prodect prodect = new Prodect();
        prodect.setProjectName(projectName);
        prodect.setProjectPrice(new BigDecimal(price));
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        ParsePosition pos = new ParsePosition(0);
        prodect.setStartDate(format.parse(startDate, pos));
        ParsePosition pos1 = new ParsePosition(0);
        prodect.setEndDate(format.parse(endDate, pos1));
        prodectService.insertProdect(prodect);
        return new JsonResult();
    }

    /**
     * 修改项目
     *
     * @param id
     * @param projectName
     * @param price
     * @param startDate
     * @param endDate
     * @return
     */
    @ResponseBody
    @RequestMapping("/UpdateProject")
    public JsonResult updateProdect(Integer id, String projectName, String price, String startDate, String endDate) {
        Prodect prodect = new Prodect();
        prodect.setId(id);
        prodect.setProjectName(projectName);
        prodect.setProjectPrice(new BigDecimal(price));
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        ParsePosition pos = new ParsePosition(0);
        prodect.setStartDate(format.parse(startDate, pos));
        ParsePosition pos1 = new ParsePosition(0);
        prodect.setEndDate(format.parse(endDate, pos1));
        prodectService.updateProdect(prodect);
        return new JsonResult();
    }

    /**
     * 通过id查询项目
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProdectById")
    public JsonResult getProdectById(Integer id) {
        Prodect p = prodectService.getProdectById(id);
        return new JsonResult(p);
    }

    /**
     * 删除项目
     *
     * @param checkedIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/DeleteProject")
    public JsonResult DeleteProjectById(String checkedIds) {
        prodectService.deleteProdectById(checkedIds);
        return new JsonResult();
    }
}
