package goods;
//商品类
public class Goods {
    //商品编号
    private int number;
    //商品名称
    private String name;
    //生产厂家
    private String factory;
    //生产日期，例如20230820表示2023年8月20日
    private int date;
    //型号
    private String type;
    //进货价
    private double purchase;
    //零售价格
    private double sale;
    //数量
    private int quantity;

    public Goods(int number, String name, String factory, int date,
                 String type, double purchase, double sale, int quantity) {
        this.number = number;
        this.name = name;
        this.factory = factory;
        this.date = date;
        this.type = type;
        this.purchase = purchase;
        this.sale = sale;
        this.quantity = quantity;
    }

    public Goods() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPurchase() {
        return purchase;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
