package daomain;

import java.util.Set;

public class Goods {
    private String pictures;
    private String detail;
    private String name;
    private int price;
    private String id;
    private int times;
    private User user;
    private Set<daomain.Orders> Orders;

    public Set<daomain.Orders> getOrders() {
        return Orders;
    }

    public void setOrders(Set<daomain.Orders> orders) {
        Orders = orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "pictures='" + pictures + '\'' +
                ", detail='" + detail + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }
}
