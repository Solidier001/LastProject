import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static com.alipay.api.AlipayConstants.APP_ID;
import static com.alipay.api.AlipayConstants.CHARSET;

public class AlipayTest {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AlipayClient alipayClient = (AlipayClient) context.getBean("client");
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的request类
        request.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101002\"," +//商户订单号
                "    \"total_amount\":\"88.88\"," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"store_id\":\"NJ_001\"," +
                "    \"timeout_express\":\"90m\"}");//订单允许的最晚付款时间
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        System.out.print("\n"+response.getBody());
    }
}
