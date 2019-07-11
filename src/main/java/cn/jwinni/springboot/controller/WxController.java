package cn.jwinni.springboot.controller;

import cn.jwinni.springboot.common.Https;
import cn.jwinni.springboot.common.JsonResult;
import cn.jwinni.springboot.domain.Prodect;
import cn.jwinni.springboot.domain.ValidateCode;
import cn.jwinni.springboot.service.ProdectService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.scene.chart.ValueAxis;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class WxController {
    public static Logger logs = Logger.getLogger(WxController.class);

    @Autowired
    private ProdectService prodectService;

    /**
     * 短信请求地址url
     */
    @Value("${smsUrl}")
    private String smsUrl;
    /**
     * 短信id
     */
    @Value("${corpId}")
    private String corpId;
    /**
     * 短息账号
     */
    @Value("${LoginName}")
    private String LoginName;
    /**
     * 短信密码
     */
    @Value("${SecretKey}")
    private String SecretKey;

    /**
     * 微信报名页面
     * @return
     */
    @RequestMapping("/wx")
    public String wxSubUser() {

        return "/wx/wxSubUser";
    }

    /**
     * 查询有效的项目
     * @return
     */
    @ResponseBody
    @RequestMapping("/wxAffectiveProject")
    public JsonResult wxAffectiveProject() {
        List<Prodect> prodects = prodectService.getAffectiveProject();
        return new JsonResult(prodects);
    }

    /**
     * 校验短信验证码
     * @param code
     * @param telNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkVCode")
    public  JsonResult checkValidateCode(String code,String telNo) {

        Integer ret = prodectService.checkVCode(code, telNo);
        if (ret>0){
            return  new JsonResult();
        }
        else {

            return  new JsonResult("验证码输入错误或失效");
        }

    }

    /**
     * 通过手机号获取验证码（短信验证码发送）
     * @param Tel
     * @return
     */
    @ResponseBody
    @RequestMapping("/getVCode")
    public  JsonResult setValidateCode(String Tel){

        ValidateCode validateCode = new ValidateCode();
        String VCode = String
                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
        // 设置验证码失效时间为10分钟
        Calendar cDate = Calendar.getInstance();
        cDate.add(Calendar.MINUTE, 10);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        validateCode.setCode(VCode);
        validateCode.setTelNo(Tel);
        Date a =  cDate.getTime();
        validateCode.setExpireDate(sdf.format(cDate.getTime()));
        Long ret = prodectService.insertVCode(validateCode);
        if (ret>0){

            String  content = "您的验证码为:" + VCode+"，该码有效期为10分钟且只能使用一次！";
            //保存成功，发送短信验证码

            String jsonRet = Https.posts(smsUrl,"SmsGetBalance",corpId,LoginName,SecretKey,Tel,content,"");
            JSONObject jsonObject = JSON.parseObject(jsonRet);
            int Code = Integer.parseInt(jsonObject.getString("Code"));
            if (Code>0){
                //有余额，发短信。
                Https.posts(smsUrl,"SmsSend",corpId,LoginName,SecretKey,Tel,content,"");
                return  new JsonResult();
            }
            else{

                return  new JsonResult("没短信余额喽，请充值短信。");
            }
        }
        else {
            return  new JsonResult("验证码发送失败！");

        }
    }
}
