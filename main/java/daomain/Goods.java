package daomain;

import java.util.Set;
import daomain.Orders;

public class Goods {
    private String pictures;
    private String detail;
    private String name;
    private int price;
    private String id;
    private int times;
    private User user;
    private Set<Orders> Orders;
    private String reviewgood;
    private String statu;

    public Goods(String pictures, String detail, String name, int price, int times) {
        this.pictures = pictures;
        this.detail = detail;
        this.name = name;
        this.price = price;
        this.id = null;
        this.times = times;
        this.user = null;
        this.reviewgood = null;
    }
/*pList.add(Projections.property("pictures").as("pictures"));
        pList.add(Projections.property("price").as("price"));
        pList.add(Projections.property("detail").as("detail"));
        pList.add(Projections.property("id").as("id"));
        pList.add(Projections.property("name").as("name"));*/
    public Goods(String pictures, String detail, String name, int price, String id) {
        this.pictures = pictures;
        this.detail = detail;
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public Goods(String pictures, String detail, String name, int price, String id, int times) {
        this.pictures = pictures;
        this.detail = detail;
        this.name = name;
        this.price = price;
        this.id = id;
        this.times = times;
    }

    public Goods() {
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getReviewgood() {
        return reviewgood;
    }

    public void setReviewgood(String reviewgood) {
        this.reviewgood = reviewgood;
    }

    public Set<Orders> getOrders() {
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
                ", times=" + times +
                ", user=" + user +
                ", Orders=" + Orders +
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
