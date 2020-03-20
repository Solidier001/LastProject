package config;

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101800718419";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKtdBdbBdaJD8c+YwFN2AbqbnLKYA57HRuTWIWLxz2e2OKFihpcrlQX01t2ogcdSB0N4Api6Bpcv10IlikvEET5C55fnTFrvR8RmrCL4/p2cxVFlGjwWrN8lHAqmhVT/uWqLG/7mjREIDe1HGdoNGTiRkqB2l46hYKqVLFIuBUu3JwLdPjW2vk4yy3W7sDRP4ZsH5sAlTTAYY9IZ7mQNjcUFAzc3V56XHjfs5bZL2G+vcDTKQ4LX1ZNzspGwOxukWrR0cacQ27hUBYJpnLpqHWO+HsfDhz5IfRtdv8ZVxoAzFat0Emwug6CM8gvXceqkOj/5VS0OyqTotxsGcJtFFtAgMBAAECggEAdNtvSwVBNyPKN/LUT6CzSxrV955i9GmMPVDcDaxrTgvtc+vu58dK77caWC3+9VqHmOEZC+choTTK6gf1ffNLMCLQNHKgldorBnecgTPoni5qwlQnOzo7bH10+keV1imn3ukHtpZ3n4mzyAZaIV3WseCSXqIX7VHod9rmbEZRj4+DPZwIkeSVBCi4vzl2Ame0SqpxCh85ZqKwTqE/MtJwkacD84iETBU3x0MwqLfGNUz6Ez4gsZxiFHs31qgahvwxI58qoD4L7ToA8umYm+Om6nIjkM1dWNn3IZEuM+K9dkasiQYZGvkLOtz3A2YnBNM0yx4UR11zlZ2o1Agc134O0QKBgQD0PVD6WMI+3hbhiBNO/yLidh2LlElBEvuPK396MB0Tei9tXspq16ZhWzd8fvij5sKKCpDct2hvlH+1HC8Np2Q7yjSxxzF5uaVWemKDDFVfVYzYyEtJburBiOoteuDuE0Ne2Jg7G5gXcxBFygdHYawciatgsLo+Q/DSfwseSF4gowKBgQCRY6mTnIcUUaT4crobgW0/JRdpE0GxvaFfbM08yObBVvcciUQOOqcy5ap9Qr4LW8Sye81TJGu3Pn0LX7ukN3KU1PXOlOHJaBhXTlnkiNg2B5Gl/uOx/ZQoRMnA/fYUMH1pf5Fto/am6vNVrzrIvBLcpUk10D5Ihvcl3V5DzhoWrwKBgHGQpTuS09c5mzgI9rlvpr/YHgrNy3YHppK+rgqZnpt03+KiBcrCNSnlqRopS+GiJejb2tnWKihfyzXiQfq+T7wBXJkhPt8TtE5On8nBjVzDwbb3AxLFEUEmMYUuMrJgoJ3UZoxtWz3kEbfxpYrKXqXyWY6e/ip/mtW1nuzYx3OzAoGBAJC+91I1kSpCioqrteuydHBcXi1Hda0eTS3y5XYx0cME8OqhIndEH7gFfTJOI2StjWvg46914CPEe7u6KNc6OMJCLyLniM766tjo8YgcUZ2g9XmdBVXsTve0XGTesoKxL/q7fa75gZP28CILXewBionPqM6Br7CHZkF1XecFp2WTAoGAJyaaC6Ir56p/2Qt4BWEFb3RNvxoQ+ZDRR+2IEwko+0JhXdEf+SwyVPanPHdVqnXTPQh0QHIuWqYiNXb1QC1mPKLF3NmpblV4hv9hvTQ+VW5ILXhAVGzmJO6p0tyLBmkSt/ERYnsebjOFyt6xjOqiEcaAASY4Dil5ZRi35oJdgoI=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoYugJ3UV9HPN64ACg7cH64gXmOB47NeiuU05GVzr4s5gnvOM8Ko1Ez37pBxUryC/aRowpndC90sXvUALUfAE+UfDaIxFkfvKVbMv+c97S4rxDwiSURFHc/JqYsVl5sacWh1GFT2Q2pGVzIIoUibEWN7/1fNwqfYIJa+9lqyKRuW2knLq/noruCeqXUqDCXZf3eoOA5alLpS5b8I2EOyDs9lShJ9aPS4TccdlxvCLdlH6CAKg3OFZe+pa0HWU3NK/kDiwezgUjCry+tv+M0ge0pwXZkqBnv6yEes+Pghm1420/zeoH0OR/ex2izbt///Y+uW2uiQxYnR72E4+/odvAwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

}

