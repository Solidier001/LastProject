package daomain;

import pojo.AlipayToken;

import java.util.Set;

public class User {

    private String name;
    private String password;
    private String id;
    private String nickname;
    private String sex;
    private String locations;
    private String reviewuser;
    private String faculty;
    private String specialty;
    private String team;
    private String imglocation;
    private String stuid;
    private String email;
    private String fscstr;
    private String AuthToken;
    private String RefreshToken;
    private String alipayid;
    private String QQ;
    private String phone;
    private Set<daomain.Goods> Goods;
    private Set<daomain.Orders> Orders;

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

    public String getAlipayid() {
        return alipayid;
    }

    public void setAlipayid(String alipayid) {
        this.alipayid = alipayid;
    }

    public String getFscstr() {
        return fscstr;
    }

    public void setFscstr(String fscstr) {
        this.fscstr = fscstr;
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

    public String getImglocation() {
        return imglocation;
    }

    public void setImglocation(String imglocation) {
        this.imglocation = imglocation;
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


    public String getReviewuser() {
        return reviewuser;
    }

    public void setReviewuser(String reviewuser) {
        this.reviewuser = reviewuser;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
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

    public String getAuthToken() {
        return AuthToken;
    }

    public void setAuthToken(String authToken) {
        AuthToken = authToken;
    }

    public void setAlipayToken(AlipayToken token){
        this.AuthToken=token.getAuthToken();
        this.RefreshToken=token.getRefreshToken();
    }

    public void setRefreshToken(String refreshToken) {
        RefreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return RefreshToken;
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
                ", reviewuser='" + reviewuser + '\'' +
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
