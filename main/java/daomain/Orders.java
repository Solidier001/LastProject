package daomain;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable {
    private String id;
    private int price;
    private Date date;
    private Goods good;
    private int nunber;
    private User user;
    private String paymethod;

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
