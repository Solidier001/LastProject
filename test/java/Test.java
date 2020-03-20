import com.google.gson.Gson;
import daomain.Goods;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import util.OrmService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.UUID;

public class Test {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrmService ormService = ((OrmService) context.getBean("OrmService"));
        String baseurl = "https://img13.360buyimg.com/n0/";
        Document document = Jsoup.parse(new File("C:\\Users\\user\\Desktop\\新建文本文档 (2).txt"), "utf-8");
        Elements uls = document.getElementsByTag("ul");
        int n = 0;
        for (Element ul : uls) {
            Elements lis = ul.getElementsByTag("li");

//            Elements lis=uls.first().getElementsByTag("li");
            for (Element li : lis) {
                String packagename = "E:/LastProject/src/main/webapp/img/good/" + UUID.randomUUID().toString().replace("-", "") + "/";
//                Element li=lis.first();
                n++;
                Element img = li.getElementsByClass("p-img").first();
                Element name = li.getElementsByClass("p-name").first();
                Element price = li.getElementsByClass("p-price").first();
                Element a = img.getElementsByTag("a").first();
                String link = a.attr("href");
                String imglink = a.getElementsByTag("img").first().attr("src");
                if (imglink.equals("") || imglink == null)
                    imglink = a.getElementsByTag("img").first().attr("data-lazy-img");
                String pricestring = price.getElementsByTag("strong")
                        .first().getElementsByTag("i").first().html();
                int Price = Integer.valueOf(pricestring.substring(0, pricestring.indexOf(".")));
                String Name = name.getElementsByTag("a")
                        .first().getElementsByTag("em")
                        .first().html();
                CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet("https:" + link);
                CloseableHttpResponse response1 = httpclient.execute(httpGet);
                HttpEntity entity1 = response1.getEntity();
                String html = EntityUtils.toString(entity1, "utf-8");
                response1.close();
                Document document1 = Jsoup.parse(html);
                String script = document1.getElementsByTag("script")
                        .attr("charset", "gbk")
                        .first().html();
                script = script.substring(script.lastIndexOf("imageList"));
                script = script.substring(0, script.indexOf("]") + 1).
                        replace("imageList: ", "");
                Gson gson = new Gson();
                ArrayList<String> urls = gson.fromJson(script, ArrayList.class);
                File main = new File(packagename + "main.jpg");
                FileUtils.copyURLToFile(new URL("https:"+imglink), main);
                for (String url : urls) {
                    String imgurl = baseurl + url;
                    String imgname = UUID.randomUUID().toString().replace("-", "") + ".jpg";
                    File file = new File(packagename + imgname);
                    FileUtils.copyURLToFile(new URL(imgurl), file);
                }
                Goods goods = new Goods();
                goods.setId(UUID.randomUUID().toString());
                goods.setPictures(packagename);
                goods.setPrice(Price);
                goods.setDetail("sdfgdsfgsdfgsdfgsdfgergvsejkl;CLQM");
                goods.setName(Name);
                ormService.save(goods);
            }
        }
    }
}
