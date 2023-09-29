import goods.Goods;
import goods.GoodsController;
import shop.ShoppingController;
import user.Admin;
import user.User;
import user.UserController;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
//主类
public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        ArrayList<User> userList = new ArrayList<>();
        //客户实例化
        Demo.userDemo(userList);
        ArrayList<Goods> goodsList = new ArrayList<>();
        //商品实例化
        Demo.goodsDemo(goodsList);

        //登陆判断，true需要登陆，false不需要登陆
        boolean login = true;
        int choice = 0;
        int aChoice = 0;
        int uChoice = 0;
        String userAccount = "";
        int index = -1;//确定客户的索引

        Scanner sc = new Scanner(System.in);

        while (true){
            //登录部分
            while (login) {
                System.out.println("-------------欢迎来到购物系统---------------");
                System.out.println("请您选择登录");
                System.out.println("1.管理员   2.客户   3.退出系统(请输入数字)");
                choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("请您输入用户名：");
                    String account = sc.next();
                    System.out.println("请您输入密码：");
                    String password = sc.next();
                    login = UserController.AdminlogIn(admin, account, password);
                    if (login) {
                        System.out.println("账号或密码输入错误");
                    }
                } else if (choice == 2) {
                    System.out.println("1.注册   2.登录");
                    int select = sc.nextInt();
                    sc.nextLine();
                    switch (select){
                        case 1:
                            System.out.println("请输入用户名");
                            String ac= sc.nextLine();
                            // 正则表达式，匹配手用户名格式，长度不小于5个字符
                            Pattern patternAccount = Pattern.compile("^.{5,}$");
                            while(!UserController.existAccount(ac, userList)||!patternAccount.matcher(ac).matches()){
                                if(!patternAccount.matcher(ac).matches()){
                                    System.out.println("用户名需长度不小于5个字符！请重新输入：");
                                    ac=sc.nextLine();
                                }else if (!UserController.existAccount(ac, userList)){
                                    System.out.println("用户名已存在！请重新输入：");
                                    ac=sc.nextLine();
                                }
                            }
                            userList.add(UserController.addUser(ac));
                            break;
                        case 2:
                            boolean bool = true;
                            System.out.println("请您输入用户名：");
                            while (bool){
                                String account = sc.next();
                                index = UserController.index(account, userList);
                                if (index == -1){
                                    System.out.println("输入的用户名不存在！请重新输入：");
                                }else {
                                    userAccount = account;
                                    bool = false;
                                }
                            }
                            System.out.println("请您输入密码：");
                            //客户密码连续输入错误5次锁定账户，退出登录
                            int i = 0;
                            while (i < 5) {
                                String password = sc.next();
                                if (password.equals(userList.get(index).getPassword())) {
                                    System.out.println("登录成功！");
                                    login = false;
                                    break;
                                } else {
                                    i++;
                                    if (i == 5) {
                                        System.out.println("连续5次输入错误，退出登录！");
                                        break;
                                    } else {
                                        System.out.println("密码错误!请重新输入(剩余尝试次数：" + (5 - i)+")：");
                                    }
                                }
                            }
                            break;
                        default:
                            System.out.println("输入错误！");
                    }
                } else if (choice == 3) {
                    System.out.println("退出成功！");
                    System.exit(0);
                } else {
                    System.out.println("请输入正确的序号(1,2,3)");
                }
            }

            if (choice == 1){
                System.out.println();
                System.out.println("请选择您要执行的操作");
                System.out.println("1.密码管理");
                System.out.println("2.客户管理");
                System.out.println("3.商品管理");
                System.out.println("4.退出登录");
                aChoice = sc.nextInt();
                if (aChoice == 1){
                    System.out.println("1.修改自身密码");
                    System.out.println("2.重置客户密码");
                    int select = sc.nextInt();
                    sc.nextLine();
                    switch (select){
                        case 1:
                            System.out.println("请输入您的新密码：");
                            String newPassword = sc.next();
                            admin.setPassword(newPassword);
                            System.out.println("密码修改成功！");
                            break;
                        case 2:
                            System.out.println("请输入指定客户的用户名：");
                            String designee = sc.nextLine();
                            while (UserController.existAccount(designee, userList)){
                                System.out.println("输入的用户名不存在！");
                                System.out.println("请重新输入用户名：");
                                designee = sc.nextLine();
                            }
                            UserController.passwordResetByAdmin(designee, userList);
                            break;
                    }
                }else if (aChoice == 2){
                    System.out.println("1.列出所有客户信息");
                    System.out.println("2.删除客户信息");
                    System.out.println("3.查询客户信息");
                    int select = sc.nextInt();
                    sc.nextLine();
                    switch (select){
                        case 1:
                            UserController.getAllUser(userList);
                            break;
                        case 2:
                            System.out.println("请输入要删除客户的用户名：");
                            String designee = sc.nextLine();
                            while (UserController.existAccount(designee, userList)){
                                System.out.println("输入的用户名不存在！");
                                System.out.println("请重新输入用户名：");
                                designee = sc.nextLine();
                                sc.nextLine();
                            }
                            UserController.delUser(designee, userList);
                            break;
                        case 3:
                            System.out.println("请选择查询方式");
                            System.out.println("1.根据客户ID查询  2.根据客户的用户名查询  3.查询所有客户的信息");
                            int i = sc.nextInt();
                            sc.nextLine();
                            String s = "";
                            if (i == 1){
                                System.out.println("请输入客户ID：");
                                s = sc.nextLine();
                                while (UserController.existID(s, userList)){
                                    System.out.println("输入的客户ID不存在！");
                                    System.out.println("请重新输入客户ID：");
                                    s = sc.nextLine();
                                }
                                UserController.getUserByID(s, userList);
                            }else if (i == 2){
                                System.out.println("请输入客户的用户名：");
                                s = sc.nextLine();
                                while (UserController.existAccount(s, userList)){
                                    System.out.println("输入的用户名不存在！");
                                    System.out.println("请重新输入用户名：");
                                    s = sc.nextLine();
                                }
                                UserController.getUserByAccount(s, userList);
                            }else if (i == 3){
                                UserController.getAllUser(userList);
                            }
                            break;
                    }

                }else if (aChoice == 3){
                    System.out.println("1.列出所有商品的信息");
                    System.out.println("2.添加商品的信息");
                    System.out.println("3.修改商品的信息");
                    System.out.println("4.删除商品的信息");
                    int select = sc.nextInt();
                    switch (select){
                        case 1:
                            GoodsController.getAllGoods(goodsList);
                            break;
                        case 2:
                            System.out.println("请输入商品编号：");
                            int number = sc.nextInt();
                            while (!GoodsController.exist(number,goodsList)){
                                System.out.println("商品编号已存在！请重新输入商品编号：");
                                number = sc.nextInt();
                            }
                            goodsList.add(GoodsController.addGoods(number));
                            break;
                        case 3:
                            System.out.println("请输入要修改商品的商品编号：");
                            int number1 = sc.nextInt();;
                            while (GoodsController.exist(number1, goodsList)){
                                System.out.println("输入的商品编号不存在！");
                                System.out.println("请重新输入商品编号：");
                                number1 = sc.nextInt();
                            }
                            GoodsController.changeGoods(number1, goodsList);
                            break;
                        case 4:
                            System.out.println("请输入要删除商品的商品编号：");
                            int number2 = sc.nextInt();
                            while (GoodsController.exist(number2, goodsList)){
                                System.out.println("输入的商品编号不存在！");
                                System.out.println("请重新输入商品编号：");
                                number2 = sc.nextInt();
                            }
                            GoodsController.delGoods(number2, goodsList);
                            break;
                    }
                }else if (aChoice == 4){
                    login = true;
                    System.out.println("退出成功");
                }else {
                    System.out.println("请输入正确的序号");
                }

            }else if (choice == 2){
                System.out.println();
                System.out.println("请选择您要执行的操作");
                System.out.println("1.密码管理");
                System.out.println("2.购物管理");
                System.out.println("3.退出登录");
                index = UserController.index(userAccount, userList);
                uChoice = sc.nextInt();
                if (uChoice == 1){
                    System.out.println("1.修改自身密码");
                    System.out.println("2.忘记密码");
                    int select = sc.nextInt();
                    switch (select){
                        case 1:
                            UserController.changePassword(userList.get(index).getAccount(), userList);
                            break;
                        case 2:
                            UserController.passwordResetByUser(userList.get(index).getAccount(), userList);
                            break;
                    }
                }else if (uChoice == 2){
                    System.out.print("1.添加商品到购物车");
                    System.out.print("  2.将商品移除购物车");
                    System.out.print("  3.修改商品的购买数量");
                    System.out.print("  4.商品的付款");
                    System.out.print("  5.查看购物历史");
                    System.out.print("  6.查看所有商品");
                    System.out.println("  7.查看购物车");
                    int select = sc.nextInt();
                    switch (select){
                        case 1:
                            System.out.println("请输入商品编号：");
                            int number1 = sc.nextInt();
                            while (GoodsController.exist(number1, goodsList)||ShoppingController.inCart(number1, userList.get(index))){
                                if (ShoppingController.inCart(number1, userList.get(index))){
                                    System.out.println("该商品已在购物车中！");
                                    System.out.println("请重新输入商品编号：");
                                    number1 = sc.nextInt();
                                }else if (GoodsController.exist(number1, goodsList)){
                                    System.out.println("输入的商品编号不存在！");
                                    System.out.println("请重新输入商品编号：");
                                    number1 = sc.nextInt();
                                }
                            }

                            System.out.println("请输入数量:");
                            int quantity = sc.nextInt();
                            while (GoodsController.enoughQuantity(number1, quantity, goodsList)){
                                System.out.println("请重新输入数量");
                                quantity = sc.nextInt();
                            }

                            ShoppingController.addCart(number1, quantity, userList.get(index));
                            break;
                        case 2:
                            System.out.println("请输入商品编号:");
                            int number2 = sc.nextInt();
                            while (!ShoppingController.inCart(number2, userList.get(index))){
                                System.out.println("输入的商品编号不存在购物车中！");
                                System.out.println("请重新输入商品编号：");
                                number2 = sc.nextInt();
                            }
                            ShoppingController.delCart(number2, userList.get(index));
                            break;
                        case 3:
                            System.out.println("请输入商品编号:");
                            int number3 = sc.nextInt();
                            while (!ShoppingController.inCart(number3, userList.get(index))){
                                System.out.println("输入的商品编号不存在购物车中！");
                                System.out.println("请重新输入商品编号：");
                                number3 = sc.nextInt();
                            }
                            System.out.println("请输入改变后的数量:");
                            int changeQuantity = sc.nextInt();
                            while (GoodsController.enoughQuantity(number3,changeQuantity, goodsList)){
                                System.out.println("请重新输入数量");
                                changeQuantity = sc.nextInt();
                            }
                            ShoppingController.changeCart(number3, changeQuantity, userList.get(index));
                            break;
                        case 4:
                            ShoppingController.payment(userList.get(index), goodsList);
                            break;
                        case 5:
                            ShoppingController.viewHistory(userList.get(index));
                            break;
                        case 6:
                            GoodsController.getAllGoods(goodsList);
                            break;
                        case 7:
                            ShoppingController.viewCart(userList.get(index),goodsList);
                            break;
                    }
                }else if (uChoice == 3){
                    login = true;
                    System.out.println("退出成功");
                }else {
                    System.out.println("请输入正确的序号");
                }
            }

        }
    }

}
