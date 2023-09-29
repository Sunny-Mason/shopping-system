package shop;
//购物车类
public class Cart {
    //购物车中商品的编号
    private int item;
    //购物车中该商品的购买数量
    private int quantity;

    public Cart(int item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Cart(){}

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
