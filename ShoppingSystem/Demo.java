import goods.Goods;
import shop.Cart;
import shop.ShoppingHistory;
import user.User;

import java.time.LocalDate;
import java.util.ArrayList;
//实例类
public class Demo {
    //客户实例
    public static void userDemo(ArrayList<User> list){
        //客户小明的购物车
        String time11 = "2022-06-06 09:16:33";//yyyy-MM-dd HH:mm:ss
        String time12 = "2022-10-01 15:11:25";
        String time13 = "2022-12-04 10:34:01";

        ArrayList<Cart> c11 = new ArrayList<>();
        ArrayList<Cart> c12 = new ArrayList<>();
        ArrayList<Cart> c13 = new ArrayList<>();
        //   商品编号----数量
        c11.add(new Cart(1,5));
        c11.add(new Cart(2,4));
        c11.add(new Cart(4,1));
        c12.add(new Cart(2,5));
        c12.add(new Cart(3,1));
        c13.add(new Cart(4,1));

        //客户小军的购物车
        String time21 = "2022-09-06 08:23:43";//yyyy-MM-dd HH:mm:ss
        String time22 = "2023-01-14 18:13:10";
        String time23 = "2023-04-07 11:31:21";

        ArrayList<Cart> c21 = new ArrayList<>();
        ArrayList<Cart> c22 = new ArrayList<>();
        ArrayList<Cart> c23 = new ArrayList<>();
        //   商品编号----数量
        c21.add(new Cart(3,4));
        c21.add(new Cart(2,3));
        c22.add(new Cart(1,3));
        c22.add(new Cart(2,1));
        c23.add(new Cart(3,3));
        c23.add(new Cart(1,2));

        //客户小红的购物车
        String time31 = "2023-03-07 11:19:37";//yyyy-MM-dd HH:mm:ss
        String time32 = "2023-05-03 13:40:24";

        ArrayList<Cart> c31 = new ArrayList<>();
        ArrayList<Cart> c32 = new ArrayList<>();
        //   商品编号----数量
        c31.add(new Cart(1,3));
        c32.add(new Cart(3,2));
        c32.add(new Cart(2,1));

        //购物历史
        ArrayList<ShoppingHistory> h1 = new ArrayList<>();//小明
        h1.add(new ShoppingHistory(c11, time11));
        h1.add(new ShoppingHistory(c12, time12));
        h1.add(new ShoppingHistory(c13, time13));
        ArrayList<ShoppingHistory> h2 = new ArrayList<>();//小军
        h2.add(new ShoppingHistory(c21, time21));
        h2.add(new ShoppingHistory(c22, time22));
        h2.add(new ShoppingHistory(c23, time23));
        ArrayList<ShoppingHistory> h3 = new ArrayList<>();//小红
        h3.add(new ShoppingHistory(c31, time31));
        h3.add(new ShoppingHistory(c32, time32));

        //客户ID--用户名--密码--用户级别--用户注册时间--客户累计消费总金额--用户手机号--用户邮箱--购物历史
        list.add(new User("20220606092232","小明","Xiaoming111.","GoldCustomer" ,LocalDate.of(2022, 6, 6),233,"13575785675","3524352324@qq.com",h1));
        list.add(new User("20220906082011","小军","Xiaojun222.","GoldCustomer" ,LocalDate.of(2022, 9, 6),208,"13425563453","3234643523@qq.com",h2));
        list.add(new User("20230307111542","小红","Xiaohong333.","SilverCustomer",LocalDate.of(2023, 3, 7),133,"13555688776","2453556345@qq.com",h3));
    }

    //商品实例
    public static void goodsDemo(ArrayList<Goods> list){
        //商品编号--商品名称--生产厂家--生产日期--型号--进货价--零售价格--数量
        list.add(new Goods(1, "牛奶", "伊利", 20230820, "a2023", 10, 13, 30));
        list.add(new Goods(2, "面包", "巴黎贝甜", 20230901, "b2023", 12, 18, 20));
        list.add(new Goods(3, "咖啡", "雀巢", 20230816, "c2023", 20, 33, 20));
        list.add(new Goods(4, "薯片", "乐事", 20230820, "d2023", 10, 16, 30));
        list.add(new Goods(5, "水杯", "富光", 20230211, "e2023", 15, 25, 15));
        list.add(new Goods(6, "牙膏", "云南白药", 20230516, "f2023", 17, 23,18));
    }
}
