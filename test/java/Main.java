
import com.google.gson.Gson;
import daomain.Goods;
import org.junit.Test;
import pojo.BzContent;
import pojo.GoodsDetails;

public class Main {
    public static void main(String[] args) {
        Goods goods = new Goods();
        goods.setId("1331");
        goods.setName("klsdhcskdl ");
        goods.setTimes(231);
        goods.setPrice(50);
        BzContent content = new BzContent()
                .setOut_trade_no("20150320010101002")
                .setStore_id("1")
                .setSubject("god")
                .setTimeout_express("90m")
                .setTotal_amount(88.88F)
                .setGoods_detail(new GoodsDetails(goods));
        System.out.println(new Gson().toJson(content));
    }

}
