package daomain;

import pojo.AlipayToken;

import java.util.List;
import java.util.Set;

public class User {


    private String id;
    private String imglocation;
    private String password;
    private String name;
    private String sex;
    private String nickname;
    private String locations;
    private String fscstr;
    private String faculty;
    private String specialty;
    private String team;
    private String stuid;
    private String email;
    private String QQ;
    private String phone;
    private String alipayid;
    private String refreshToken;
    private String authToken;
    private String reviewToUser;
    private Set<daomain.Goods> Goods;
    private Set<daomain.Orders> Orders;
    private Set<daomain.Review> Review;

    public User(String id, String imglocation, String sex, String nickname, String fscstr, String stuid, String QQ, String phone) {
        this.id = id;
        this.imglocation = imglocation;
        this.sex = sex;
        this.nickname = nickname;
        this.fscstr = fscstr;
        this.stuid = stuid;
        this.QQ = QQ;
        this.phone = phone;
    }

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public Set<daomain.Review> getReview() {
        return Review;
    }

    public void setReview(Set<daomain.Review> review) {
        Review = review;
    }

    public Set<daomain.Orders> getOrders() {
        return Orders;
    }

    public void setOrders(Set<daomain.Orders> orders) {
        Orders = orders;
    }

    public Set<daomain.Goods> getGoods() {
        return Goods;
    }

    public void setGoods(Set<daomain.Goods> goods) {
        Goods = goods;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAlipayid() {
        return alipayid;
    }

    public void setAlipayid(String alipayid) {
        this.alipayid = alipayid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getFscstr() {
        return fscstr;
    }

    public void setFscstr(String fscstr) {
        this.fscstr = fscstr;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImglocation() {
        return imglocation;
    }

    public void setImglocation(String imglocation) {
        this.imglocation = imglocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getReviewToUser() {
        return reviewToUser;
    }

    public void setReviewToUser(String reviewToUser) {
        this.reviewToUser = reviewToUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", Goods=" + Goods +
                ", locations='" + locations + '\'' +
                ", Orders=" + Orders +
                ", faculty='" + faculty + '\'' +
                ", specialty='" + specialty + '\'' +
                ", team='" + team + '\'' +
                ", imglocation='" + imglocation + '\'' +
                ", stuid='" + stuid + '\'' +
                ", email='" + email + '\'' +
                ", alipayid='" + alipayid + '\'' +
                ", fscstr='" + fscstr + '\'' +
                '}';
    }

}
