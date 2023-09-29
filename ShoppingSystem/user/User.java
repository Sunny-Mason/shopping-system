package user;

import shop.Cart;
import shop.ShoppingHistory;

import java.time.LocalDate;
import java.util.ArrayList;
//客户类
public class User {
    //客户ID
    private String ID;
    //用户名
    private String account;
    //密码
    private String password;
    //用户级别:GoldCustomer(金牌客户)、SilverCustomer(银牌客户)、BronzeCustomer(铜牌客户)
    private String rank;
    //用户注册时间:年-月-日
    private LocalDate date;
    //客户累计消费总金额
    private double consume;
    //用户手机号
    private String phone;
    //用户邮箱
    private String mail;
    //购物车
    private ArrayList<Cart> cart;
    //购物历史
    private ArrayList<ShoppingHistory> history;

    public User(String ID, String account, String password, String rank, LocalDate date, double consume, String phone, String mail, ArrayList<ShoppingHistory> history) {
        this.ID = ID;
        this.account = account;
        this.password = password;
        this.rank = rank;
        this.date = date;
        this.consume = consume;
        this.phone = phone;
        this.mail = mail;
        this.cart = new ArrayList<>();
        this.history = history;
    }

    public User() {
        this.rank="BronzeCustomer";
        this.consume=0;
        this.cart=new ArrayList<>();
        this.history=new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getConsume() {
        return consume;
    }

    public void setConsume(double consume) {
        this.consume = consume;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Cart> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Cart> cart) {
        this.cart = cart;
    }

    public ArrayList<ShoppingHistory> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<ShoppingHistory> history) {
        this.history = history;
    }
}
