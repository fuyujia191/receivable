package cn.jwinni.springboot.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;


/**
 * https 方式发送短信
 */
public class Https { // HTTPS的请求发送方式

    public static String posts(String url, String type, String CorpID, String LoginName, String SecretKey, String PhoneNumbers, String Content, String AddNum) {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");//日期格式化
        String timeStamp = dateFormat.format(now);
        String strPwsd = MD5.md5(CorpID + SecretKey + timeStamp);//MD5加密方式
        //System.out.println("strPwsd="+strPwsd);
        String contexts = null;//转码
        try {
            contexts = URLEncoder.encode(Content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Methods = SmsSend为发送短信，SmsGetRecv取回复短信，SmsGetReport取短信状态，SmsGetBalance取账号剩余短信量,SmsGetSign取短信签名。
        String urls = url + ":843/SDK3/Sms?Methods=" + type + "&CorpID=" + CorpID + "&LoginName=" + LoginName + "&TimeStamp=" + timeStamp + "&SecretKey=" + strPwsd + "&PhoneNumbers=" + PhoneNumbers + "&Content=" + contexts + "&AddNum=" + AddNum;

        String httpOrgCreateTest = urls;
        String httpOrgCreateTestRtn = doPost(httpOrgCreateTest, "utf-8");
        return httpOrgCreateTestRtn;

    }

    public static String doPost(String url, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}