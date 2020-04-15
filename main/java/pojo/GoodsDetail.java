package pojo;

import daomain.Goods;

public class GoodsDetail {
    private String goods_id;
    private String goods_name;
    private int quantity;
    private String price;
    private String goods_category;
    private String categories_tree;
    private String body;
    private String show_url;

    public String getGoods_id() {
        return goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public GoodsDetail(Goods goods) {
        this.goods_id=goods.getId();
        this.goods_name=goods.getName();
        this.quantity=goods.getTimes();
        this.price=String.valueOf(goods.getPrice());
    }

    public String getGoods_category() {
        return goods_category;
    }

    public GoodsDetail setGoods_category(String goods_category) {
        this.goods_category = goods_category;
        return this;
    }

    public String getCategories_tree() {
        return categories_tree;
    }

    public GoodsDetail setCategories_tree(String categories_tree) {
        this.categories_tree = categories_tree;
        return this;
    }

    public String getBody() {
        return body;
    }

    public GoodsDetail setBody(String body) {
        this.body = body;
        return this;
    }

    public String getShow_url() {
        return show_url;
    }

    public GoodsDetail setShow_url(String show_url) {
        this.show_url = show_url;
        return this;
    }
}
