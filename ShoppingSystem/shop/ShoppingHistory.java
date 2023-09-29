package shop;

import java.util.ArrayList;
//购物历史类
public class ShoppingHistory {
    // 购买商品
    private ArrayList<Cart> cart;
    // 购买时间
    private String purchaseTime;

    public ShoppingHistory(ArrayList<Cart> cart, String purchaseTime) {
        this.cart = cart;
        this.purchaseTime = purchaseTime;
    }

    public ShoppingHistory(){}

    public ArrayList<Cart> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Cart> cart) {
        this.cart = cart;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}
