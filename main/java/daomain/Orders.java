package daomain;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable {
    private String id;
    private int price;
    private Date date;
    private Goods good;
    private int nunber;
    private User owner;
    private User buyr;
    private String paymethod;
    private String buyrname;
    private String ownername;
    private String name;
    private String statu;
    private String review;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Orders() {
    }

    public Orders(String id, int price, Date date, Goods good, int nunber, User buyr, String paymethod) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.good = good;
        this.nunber = nunber;
        this.buyr = buyr;
        this.paymethod = paymethod;
    }

    public String getBuyrname() {
        return buyrname;
    }

    public void setBuyrname(String buyrname) {
        this.buyrname = buyrname;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getBuyr() {
        return buyr;
    }

    public void setBuyr(User buyr) {
        this.buyr = buyr;
    }

    public int getNunber() {
        return nunber;
    }

    public void setNunber(int nunber) {
        this.nunber = nunber;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
