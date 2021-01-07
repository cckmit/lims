package com.adc.da.login.service;

import com.adc.da.util.utils.Encodes;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.DependsOn;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class AreaLoginService {


    public static Boolean dflqUserValidateService(String url,String userName,String password,String adname,String adpassword,String AreaServiceUrl)throws Exception{

            Boolean result = false;
            String adpasswordChange = new String(Encodes.decodeBase64(adpassword), StandardCharsets.UTF_8);  //转码

            StringBuffer sb = new StringBuffer("");
            sb.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:tem='http://tempuri.org/'>");
            sb.append("<soapenv:Header/>");
            sb.append("<soapenv:Body>");
            sb.append("<tem:userValidate>");
            sb.append("<tem:url>" +
                            url+
                     "</tem:url>");
            sb.append("<tem:userName>" +
                            userName+
                      "</tem:userName>");
            sb.append("<tem:password>" +
                            password+
                      "</tem:password>");
            sb.append("<tem:adname>" +
                             adname+
                      "</tem:adname>");
            sb.append("<tem:adpassword>" +
                            adpasswordChange+
                      "</tem:adpassword>");
            sb.append("</tem:userValidate>");
            sb.append("</soapenv:Body>");
            sb.append("</soapenv:Envelope>");

           // HttpClient发送SOAP请求
           System.out.println("HttpClient 发送SOAP请求");
           System.out.println("请求");
           System.out.println(sb.toString());
           HttpClient client = new HttpClient();
           PostMethod postMethod = null;
           postMethod = new PostMethod(AreaServiceUrl);
           client.getHttpConnectionManager().getParams().setConnectionTimeout(100000);
           client.getHttpConnectionManager().getParams().setSoTimeout(100000);
           RequestEntity requestEntity = new StringRequestEntity(sb.toString(), "text/xml", "UTF-8");
           postMethod.setRequestHeader("soapAction", "http://tempuri.org/userValidate");
           postMethod.setRequestEntity(requestEntity);
           int status = client.executeMethod(postMethod);
           System.out.println("status:" + status);

           String resultSoap = postMethod.getResponseBodyAsString();
           System.out.println("返回报文" + resultSoap);

           JSONObject json = JSONObject.fromObject(resultSoap);
           result = (Boolean)json.get("data");
           System.out.println("最终结果"+result);

      return result;
    }
}
