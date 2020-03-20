package pojo;

public class AlipayTradePagePayResponseBody {

    private AlipayTradePagePayResponse alipay_trade_page_pay_response;
    private String sign;

    public AlipayTradePagePayResponse getAlipay_trade_page_pay_response() {
        return alipay_trade_page_pay_response;
    }

    public void setAlipay_trade_page_pay_response(AlipayTradePagePayResponse alipay_trade_page_pay_response) {
        this.alipay_trade_page_pay_response = alipay_trade_page_pay_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
