package service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import daomain.User;
import pojo.AlipayToken;
import pojo.BzContent;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class AlipayService {
    @Resource
    AlipayClient alipayClient;
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    @Resource(name = "UserService")
    UserService userService;
    private AlipayTradePrecreateResponse sendrequest(BzContent content) {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent(gson.toJson(content));
//        request.setNotifyUrl();
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            return response;
        } catch (AlipayApiException e) {
            String message = e.getMessage();
            return null;
        }
    }

    private AlipayTradePrecreateResponse sendrequest(BzContent content, String AuthToken) {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent(gson.toJson(content));
//        request.setNotifyUrl();
        request.putOtherTextParam("app_auth_token", AuthToken);
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            return response;
        } catch (AlipayApiException e) {
            String message = e.getMessage();
            return null;
        }
    }

    public String ForMeQrCode(BzContent content) {
        AlipayTradePrecreateResponse responseEntiy = sendrequest(content);
        return responseEntiy.getQrCode();
    }

    public String ForUserQrCode(BzContent content,String AuthToken) {
        AlipayTradePrecreateResponse responseEntity = sendrequest(content, AuthToken);
        return responseEntity.getQrCode();
    }

    public void QREncode(String str, OutputStream stream) throws WriterException, IOException {
        String content = str;//二维码内容
        int width = 200; // 图像宽度
        int height = 200; // 图像高度
        String format = "gif";// 图像类型
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
    }

    public void grantTypeAuthToken(String grantType,String app_auth_code, User user) throws AlipayApiException {
        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        request.setBizContent(
                "{" + "\"grant_type\":\""+grantType+"\"," +
                        " \"code\":\"" + app_auth_code + "\"" +
                        " }");
        AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
        String AuthToken = response.getAppAuthToken();
        String RefreshToken = response.getAppRefreshToken();
        userService.UpdateAuthToken(new AlipayToken(AuthToken,RefreshToken),user);
    }
}
