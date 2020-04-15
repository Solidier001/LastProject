package pojo;

import daomain.Goods;
import java.util.ArrayList;

public class GoodsDetails extends ArrayList<GoodsDetail> {
    public boolean add(Goods goods) {
        return super.add(new GoodsDetail(goods));
    }

    public GoodsDetails(Goods goods) {
        super();
        this.add(goods);
    }
}
