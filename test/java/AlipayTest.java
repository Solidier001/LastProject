import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;

public class AlipayTest {
    public static void main(String[] args) {
        AlipayClient alipayClient = new
                DefaultAlipayClient ("https://openapi.alipaydev.com/gateway.do",
                "2016101800718419",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKtdBdbBdaJD8c+YwFN2AbqbnLKYA57HRuTWIWLxz2e2OKFihpcrlQX01t2ogcdSB0N4Api6Bpcv10IlikvEET5C55fnTFrvR8RmrCL4/p2cxVFlGjwWrN8lHAqmhVT/uWqLG/7mjREIDe1HGdoNGTiRkqB2l46hYKqVLFIuBUu3JwLdPjW2vk4yy3W7sDRP4ZsH5sAlTTAYY9IZ7mQNjcUFAzc3V56XHjfs5bZL2G+vcDTKQ4LX1ZNzspGwOxukWrR0cacQ27hUBYJpnLpqHWO+HsfDhz5IfRtdv8ZVxoAzFat0Emwug6CM8gvXceqkOj/5VS0OyqTotxsGcJtFFtAgMBAAECggEAdNtvSwVBNyPKN/LUT6CzSxrV955i9GmMPVDcDaxrTgvtc+vu58dK77caWC3+9VqHmOEZC+choTTK6gf1ffNLMCLQNHKgldorBnecgTPoni5qwlQnOzo7bH10+keV1imn3ukHtpZ3n4mzyAZaIV3WseCSXqIX7VHod9rmbEZRj4+DPZwIkeSVBCi4vzl2Ame0SqpxCh85ZqKwTqE/MtJwkacD84iETBU3x0MwqLfGNUz6Ez4gsZxiFHs31qgahvwxI58qoD4L7ToA8umYm+Om6nIjkM1dWNn3IZEuM+K9dkasiQYZGvkLOtz3A2YnBNM0yx4UR11zlZ2o1Agc134O0QKBgQD0PVD6WMI+3hbhiBNO/yLidh2LlElBEvuPK396MB0Tei9tXspq16ZhWzd8fvij5sKKCpDct2hvlH+1HC8Np2Q7yjSxxzF5uaVWemKDDFVfVYzYyEtJburBiOoteuDuE0Ne2Jg7G5gXcxBFygdHYawciatgsLo+Q/DSfwseSF4gowKBgQCRY6mTnIcUUaT4crobgW0/JRdpE0GxvaFfbM08yObBVvcciUQOOqcy5ap9Qr4LW8Sye81TJGu3Pn0LX7ukN3KU1PXOlOHJaBhXTlnkiNg2B5Gl/uOx/ZQoRMnA/fYUMH1pf5Fto/am6vNVrzrIvBLcpUk10D5Ihvcl3V5DzhoWrwKBgHGQpTuS09c5mzgI9rlvpr/YHgrNy3YHppK+rgqZnpt03+KiBcrCNSnlqRopS+GiJejb2tnWKihfyzXiQfq+T7wBXJkhPt8TtE5On8nBjVzDwbb3AxLFEUEmMYUuMrJgoJ3UZoxtWz3kEbfxpYrKXqXyWY6e/ip/mtW1nuzYx3OzAoGBAJC+91I1kSpCioqrteuydHBcXi1Hda0eTS3y5XYx0cME8OqhIndEH7gFfTJOI2StjWvg46914CPEe7u6KNc6OMJCLyLniM766tjo8YgcUZ2g9XmdBVXsTve0XGTesoKxL/q7fa75gZP28CILXewBionPqM6Br7CHZkF1XecFp2WTAoGAJyaaC6Ir56p/2Qt4BWEFb3RNvxoQ+ZDRR+2IEwko+0JhXdEf+SwyVPanPHdVqnXTPQh0QHIuWqYiNXb1QC1mPKLF3NmpblV4hv9hvTQ+VW5ILXhAVGzmJO6p0tyLBmkSt/ERYnsebjOFyt6xjOqiEcaAASY4Dil5ZRi35oJdgoI=",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoYugJ3UV9HPN64ACg7cH64gXmOB47NeiuU05GVzr4s5gnvOM8Ko1Ez37pBxUryC/aRowpndC90sXvUALUfAE+UfDaIxFkfvKVbMv+c97S4rxDwiSURFHc/JqYsVl5sacWh1GFT2Q2pGVzIIoUibEWN7/1fNwqfYIJa+9lqyKRuW2knLq/noruCeqXUqDCXZf3eoOA5alLpS5b8I2EOyDs9lShJ9aPS4TccdlxvCLdlH6CAKg3OFZe+pa0HWU3NK/kDiwezgUjCry+tv+M0ge0pwXZkqBnv6yEes+Pghm1420/zeoH0OR/ex2izbt///Y+uW2uiQxYnR72E4+/odvAwIDAQAB",
                "RSA2" );
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();
        request.setBizModel(model);

        model.setOutTradeNo(System.currentTimeMillis()+"");
        model.setSubject("Iphone6 16G");
        model.setTotalAmount("0.01");
        model.setAuthCode("281529796853152225");//沙箱钱包中的付款码
        model.setScene("bar_code");

        AlipayTradePayResponse response = null;
        try {
            response = alipayClient.execute(request);
            System.out.println(response.getBody());
            System.out.println(response.getTradeNo());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
