package goods;

import java.util.ArrayList;
import java.util.Scanner;
//商品管理类
public class GoodsController {
    //列出所有商品的信息
    public static void getAllGoods(ArrayList<Goods> list){
        System.out.println("所有的商品信息如下：");
        System.out.println("商品编号--商品名称--生产厂家--生产日期--型号--进货价--零售价格--数量");
        for(Goods goods:list){
            System.out.println(goods.getNumber()+"--"+goods.getName()+"--"+goods.getFactory()+"--"+goods.getDate()
                    +"--"+goods.getType()+"--"+goods.getPurchase()+"--"+goods.getSale()+"--"+goods.getQuantity());
        }
    }

    //添加商品
    public static Goods addGoods(int number){
        Goods goods=new Goods();
        Scanner scanner = new Scanner(System.in);
        goods.setNumber(number);

        System.out.println("请输入商品名称：");
        goods.setName(scanner.nextLine());

        System.out.println("请输入生产厂家：");
        goods.setFactory(scanner.nextLine());

        System.out.println("请输入生产日期：");
        goods.setDate(scanner.nextInt());

        scanner.nextLine();
        System.out.println("请输入商品型号：");
        goods.setType(scanner.nextLine());


        System.out.println("请输入进货价：");
        goods.setPurchase(scanner.nextDouble());

        System.out.println("请输入零售价格：");
        goods.setSale(scanner.nextDouble());

        System.out.println("请输入数量：");
        goods.setQuantity(scanner.nextInt());

        System.out.println("添加的商品信息如下：");
        System.out.println("商品编号--商品名称--生产厂家--生产日期--型号--进货价--零售价格--数量");
        System.out.println(goods.getNumber()+"--"+goods.getName()+"--"+goods.getFactory()+"--"+goods.getDate()
                +"--"+goods.getType()+"--"+goods.getPurchase()+"--"+goods.getSale()+"--"+goods.getQuantity());
        System.out.println("商品添加成功！");

        return goods;
    }

    //修改商品的信息
    public static ArrayList<Goods> changeGoods(int number, ArrayList<Goods> list ){
        Scanner sc = new Scanner(System.in);
        boolean bool = true;
        for (Goods goods:list){
            if (number == goods.getNumber()){
                while (bool){
                    System.out.print("1.修改商品编号");
                    System.out.print("  2.修改商品名称");
                    System.out.print("  3.修改生产厂家");
                    System.out.print("  4.修改生产日期");
                    System.out.print("  5.修改商品型号");
                    System.out.print("  6.修改进货价");
                    System.out.print("  7.修改零售价格");
                    System.out.print("  8.修改数量");
                    System.out.println("  9.退出修改");
                    int select = sc.nextInt();
                    sc.nextLine();
                    switch (select){
                        case 1:
                            System.out.println("请输入新的商品编号：");
                            number = sc.nextInt();
                            goods.setNumber(number);
                            break;
                        case 2:
                            System.out.println("请输入新的商品名称：");
                            goods.setName(sc.nextLine());
                            break;
                        case 3:
                            System.out.println("请输入新的生产厂家：");
                            goods.setFactory(sc.nextLine());
                            break;
                        case 4:
                            System.out.println("请输入新的生产日期：");
                            goods.setDate(sc.nextInt());
                            break;
                        case 5:
                            System.out.println("请输入新的商品型号：");
                            goods.setType(sc.nextLine());
                            break;
                        case 6:
                            System.out.println("请输入新的进货价：");
                            goods.setPurchase(sc.nextDouble());
                            break;
                        case 7:
                            System.out.println("请输入新的零售价格：");
                            goods.setSale(sc.nextDouble());
                            break;
                        case 8:
                            System.out.println("请输入新的数量：");
                            goods.setQuantity(sc.nextInt());
                            break;
                        case 9:
                            System.out.println("修改后的商品信息如下：");
                            System.out.println("商品编号--商品名称--生产厂家--生产日期--型号--进货价--零售价格--数量");
                            System.out.println(goods.getNumber()+"--"+goods.getName()+"--"+goods.getFactory()+"--"+goods.getDate()
                                    +"--"+goods.getType()+"--"+goods.getPurchase()+"--"+goods.getSale()+"--"+goods.getQuantity());

                            bool = false;
                            break;
                        default:
                            System.out.println("输入错误，请输入1-9的数字");
                    }
                }

            }
        }
        return list;
    }

    //删除商品的信息
    public static ArrayList<Goods> delGoods(int number, ArrayList<Goods> list ){
        int index = -1;
        Scanner sc = new Scanner(System.in);
        for (Goods goods:list){
            if(number == goods.getNumber()){
                System.out.println("确定是否删除商品“"+goods.getName()+"”(Y/N)：");
                char input = sc.next().charAt(0);
                if(input == 'Y' || input == 'y'){
                    index = list.indexOf(goods);
                } else {
                    System.out.println("删除失败!");
                }
            }
        }
        if (index >= 0){
            list.remove(index);
            System.out.println("删除成功！");
        }
        return list;
    }

    //根据商品编号判断是否有此商品
    public static boolean exist(int number, ArrayList<Goods> list ){
        boolean bool = true;
        for (Goods goods:list){
            if (number == goods.getNumber()){
                bool = false;
            }
        }
        return bool;
    }

    //判断某一商品是否有足够的数量可以买
    public static boolean enoughQuantity(int number, int quantity, ArrayList<Goods> list ){
        boolean bool = false;
        for (Goods goods:list){
            if (number == goods.getNumber()){
                if (quantity > goods.getQuantity()){
                    bool = true;
                    System.out.println("商品编号为"+number+"的商品数量为"+goods.getQuantity()+",小于您的购买数量");
                }
            }
        }
        return bool;
    }
}
