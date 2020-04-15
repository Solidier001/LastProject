package pojo;

public class BzContent {
    private String out_trade_no;
    private float total_amount;
    private String subject;
    private String store_id;
    private String timeout_express;
    private String seller_id;
    private GoodsDetails goods_detail;

    public GoodsDetails getGoods_detail() {
        return goods_detail;
    }

    public BzContent setGoods_detail(GoodsDetails goods_detail) {
        this.goods_detail = goods_detail;
        return this;
    }
//    private String ;
//    private String ;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public BzContent setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public float getTotal_amount() {
        return total_amount;
    }

    public BzContent setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public BzContent setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getStore_id() {
        return store_id;
    }

    public BzContent setStore_id(String store_id) {
        this.store_id = store_id;
        return this;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public BzContent setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
        return this;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public BzContent setSeller_id(String seller_id) {
        this.seller_id = seller_id;
        return this;
    }
}
