package service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.FileItem;
import com.alipay.api.request.AlipayOpenAgentMobilepaySignRequest;
import com.alipay.api.response.AlipayOpenAgentMobilepaySignResponse;

public class AliRegistTest {
    public static void main(String[] args) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                "2016101800718419",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKtdBdbBdaJD8c",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoYugJ3UV9HPN64ACg7cH64gXmOB47NeiuU05GVzr4s5gnvOM8Ko1Ez37pBxUryC",
                "RSA2");
        AlipayOpenAgentMobilepaySignRequest request = new AlipayOpenAgentMobilepaySignRequest();
        request.setBatchNo("2017110616474516400082883");
        request.setMccCode("A_A03_4582");
        FileItem SpecialLicensePic = new FileItem("C:/Downloads/ooopic_963991_7eea1f5426105f9e6069/16365_1271139700.jpg");
        request.setSpecialLicensePic(SpecialLicensePic);
        request.setBusinessLicenseNo("1532501100006302");
        FileItem BusinessLicensePic = new FileItem("C:/Downloads/ooopic_963991_7eea1f5426105f9e6069/16365_1271139700.jpg");
        request.setBusinessLicensePic(BusinessLicensePic);
        FileItem BusinessLicenseAuthPic = new FileItem("C:/Downloads/ooopic_963991_7eea1f5426105f9e6069/16365_1271139700.jpg");
        request.setBusinessLicenseAuthPic(BusinessLicenseAuthPic);
        request.setLongTerm(true);
        request.setDateLimitation("2017-11-11");
        request.setAppName("应用名称");
        FileItem AppDemo = new FileItem("C:/Downloads/ooopic_963991_7eea1f5426105f9e6069/16365_1271139700.jpg");
        request.setAppDemo(AppDemo);
        AlipayOpenAgentMobilepaySignResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
