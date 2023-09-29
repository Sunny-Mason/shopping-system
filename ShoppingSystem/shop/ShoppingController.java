package shop;

import goods.Goods;
import user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
//购物管理类
public class ShoppingController {
    //列出购物车中的商品以及数量
    public static void viewCart(User user, ArrayList<Goods> list){
        System.out.println("购物车：");
        System.out.println("商品编号----名称----数量----单价");
        for (Cart item: user.getCart()){
            for (Goods goods: list){
                if (item.getItem() == goods.getNumber()){
                    System.out.println(item.getItem()+"----"+goods.getName()+"----"+item.getQuantity()+"----"+goods.getSale());
                    break;
                }
            }
        }
    }

    //根据商品编号判断某商品是否在购物车中
    public static boolean inCart(int number, User user){
        boolean bool = false;
        for (Cart item: user.getCart()){
            if (number == item.getItem()){
                bool = true;
            }
        }
        return bool;
    }

    //输入商品编号和数量将商品加入到购物车
    public static void addCart(int number, int quantity, User user ){
        user.getCart().add(new Cart(number,quantity));
        System.out.println(quantity+"个商品编号为"+number+"的商品已被加入客户"+user.getAccount()+"的购物车中！");
    }

    //输入商品编号和数量将商品从购物车移除
    public static void delCart(int number, User user){
        int index = -1;
        Scanner sc = new Scanner(System.in);
        for (Cart item: user.getCart()){
            if (number == item.getItem()){
                System.out.println("确定是否从购物车移除此商品(Y/N)：");
                char input = sc.next().charAt(0);
                if(input == 'Y' || input == 'y'){
                    index = user.getCart().indexOf(item);
                }else {
                    System.out.println("此商品移除失败!");
                }
            }
        }
        if (index >= 0){
            user.getCart().remove(index);
            System.out.println("此商品已从购物车中移除！");
        }
    }

    //修改购物车中某种商品的购买数量
    public static void changeCart(int number, int changeQuantity, User user ){
        if (changeQuantity <= 0){
            delCart(number, user);
            System.out.println("您需要的数量小于或等于0，该商品已从购物车中移除！");
        }else {
            for (Cart item: user.getCart()){
                if (number == item.getItem()){
                    item.setQuantity(changeQuantity);
                    System.out.println("您需要的数量变为"+changeQuantity);
                }
            }
        }

    }

    //商品支付
    public static void payment(User user, ArrayList<Goods> list ){
        //每种商品的金额
        double money = 0;
        //所有商品的金额
        double sumMoney = 0;
        System.out.println("商品编号----名称----数量----单价----金额");
        for (Cart item: user.getCart()){
            for (Goods goods: list){
                if (item.getItem() == goods.getNumber()){
                    money = goods.getSale() * item.getQuantity();
                    sumMoney += money;
                    goods.setQuantity(goods.getQuantity() - item.getQuantity());
                    System.out.println(item.getItem()+"----"+goods.getName()+"----"+item.getQuantity()+"----"+goods.getSale()+"----"+money);
                    break;
                }
            }
            money = 0;
        }
        System.out.println("总价为"+sumMoney);
        System.out.println("支付成功！");

        //计入客户累计消费总金额
        user.setConsume(user.getConsume()+sumMoney);
        System.out.println();
        System.out.println("您累计消费总金额为："+user.getConsume());
        if (user.getConsume() < 100){
            user.setRank("BronzeCustomer");
            System.out.println("，小于100，为铜牌客户(BronzeCustomer)");
        }else if (user.getConsume() >= 100 && user.getConsume() <200){
            user.setRank("SilverCustomer");
            System.out.println("，大于等于100，且小于200，为银牌客户(SilverCustomer)");
        }else {
            user.setRank("GoldCustomer");
            System.out.println("，大于等于200，为金牌客户(GoldCustomer)");
        }
        //计入客户购物历史
        record(user, purchaseTime());
        //清空购物车
        user.getCart().clear();
    }

    //计入购物历史
    public static void record(User user, String purchaseTime){
        ArrayList<Cart> copyCart=new ArrayList<>();
        for(Cart item:user.getCart()){
            copyCart.add(item);
        }
        user.getHistory().add(new ShoppingHistory(copyCart, purchaseTime));
    }

    //查看购物历史
    public static void viewHistory(User user){
        for (ShoppingHistory history: user.getHistory()){
            System.out.println("时间："+history.getPurchaseTime());
            System.out.println("商品编号----数量");
            for (Cart item: history.getCart()){
                System.out.println(item.getItem() +"----"+ item.getQuantity());
            }
            System.out.println("--------------");
        }
    }

    //获取购物时间
    public static String purchaseTime(){
        // 获取当前日期和时间
        LocalDateTime currentTime = LocalDateTime.now();
        // 将日期和时间格式化为字符串
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return formattedTime;
    }
}
