package daomain;

import java.util.Set;

public class User {

    private String name;
    private String password;
    private String id;
    private String nickname;
    private String sex;
    private Set<Goods> Goods;
    private String locations;
    private Set<daomain.Orders> Orders;

    public Set<daomain.Orders> getOrders() {
        return Orders;
    }

    public void setOrders(Set<daomain.Orders> orders) {
        Orders = orders;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public Set<Goods> getGoods() {
        return Goods;
    }

    public void setGoods(Set<Goods> goods) {
        Goods = goods;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
