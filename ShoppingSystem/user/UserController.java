package user;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
//用户管理类
public class UserController {
    //管理员登录
    public static Boolean AdminlogIn(Admin admin ,String account,String password){
        if (account.equals(admin.getAccount())){
            if (password.equals(admin.getPassword())){
                return false;
            }else {
                return true;
            }
        }
        return true;
    }

    //用户修改密码
    public static ArrayList<User> changePassword(String account,ArrayList<User> list){
        for (User user:list) {
            if (account.equals(user.getAccount())){
                Scanner sc = new Scanner(System.in);
                System.out.println("请输入新的密码");
                String password = sc.nextLine();
                // 正则表达式，匹配密码格式，长度大于8个字符，必须以大小写字母、数字和标点符号的组合
                Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={}\\[\\]\\|;:'\",<.>/?])(?=\\S+$).{9,}$");
                while(!patternPassword.matcher(password).matches()){
                    System.out.println("密码需长度大于8个字符，必须是以大小写字母、数字和标点符号的组合，请重新输入：");
                    password=sc.nextLine();
                }
                user.setPassword(password);
                System.out.println("修改成功！");
            }
        }
        return list;
    }

    //重置用户密码为：YNU123456!
    public static ArrayList<User> passwordResetByAdmin(String account,ArrayList<User> list){
        for (User user:list) {
            if (account.equals(user.getAccount())){
                user.setPassword("YNU123456!");
                System.out.println(user.getAccount()+"的密码重置为：YNU123456!");
            }
        }
        return list;
    }

    //客户忘记密码时自行重置密码，由系统随机生成
    public static ArrayList<User> passwordResetByUser(String account, ArrayList<User> list){
        for (User user:list) {
            if (account.equals(user.getAccount())){
                String newPassword = generateRandomPassword();
                user.setPassword(newPassword);
                System.out.println("你的邮箱"+user.getMail()+"收到一条消息！是否查看(Y/N)：");
                Scanner sc = new Scanner(System.in);
                char input = sc.next().charAt(0);
                if(input == 'Y' || input == 'y'){
                    System.out.println("----------------------");
                    System.out.println("java购物系统已为你重置密码\n\t发件人：java购物系统\n\t收件人："+user.getMail()+"\n\n密码重置为："+newPassword);
                    System.out.println("----------------------");
                }else {
                    System.out.println("java购物系统已将为你重置的密码发到你的邮箱，请注意查收！");
                }
            }
        }
        return list;
    }
    //函数生成随机密码，密码符合要求，且将密码长度固定为10
    private static String generateRandomPassword() {
        final int PASSWORD_LENGTH = 10;
        final String CHARACTER_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=[]{}|;:,.<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // 确保密码至少包含一个字母、一个数字和一个标点符号
        password.append(getRandomCharFromSet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"));
        password.append(getRandomCharFromSet("0123456789"));
        password.append(getRandomCharFromSet("!@#$%^&*()_-+=[]{}|;:,.<>?"));

        // 从字符集中生成剩余的密码字符
        for (int i = password.length(); i < PASSWORD_LENGTH; i++) {
            password.append(getRandomCharFromSet(CHARACTER_SET));
        }

        // 随机打乱密码字符的顺序
        for (int i = password.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(j));
            password.setCharAt(j, temp);
        }

        return password.toString();
    }
    //从提供的字符集中随机选择一个字符，用于确保随机生成的密码至少包含一个字母、一个数字和一个标点符号
    private static char getRandomCharFromSet(String set) {
        Random random = new Random();
        int index = random.nextInt(set.length());
        return set.charAt(index);
    }

    //列出所有客户信息
    public static void getAllUser(ArrayList<User> list){
        System.out.println("所有客户信息如下：");
        System.out.println("客户ID--用户名--用户级别--用户注册时间--客户累计消费总金额--用户手机号--用户邮箱");
        for (User user:list) {
            System.out.println(user.getID()+"--"+user.getAccount()+"--"+user.getRank()+"--"+
                    user.getDate()+"--"+user.getConsume()+"--"+user.getPhone()+"--"+user.getMail());
        }
    }

    //删除客户信息
    public static ArrayList<User> delUser(String account,ArrayList<User> list ){
        int index = -1;
        Scanner sc = new Scanner(System.in);
        for (User user:list){
            if (account.equals(user.getAccount())){
                System.out.println("确定是否删除客户“"+account+"”(Y/N)：");
                char input = sc.next().charAt(0);
                if(input == 'Y' || input == 'y'){
                    index = list.indexOf(user);
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

    //根据客户ID查询客户信息
    public static void  getUserByID(String ID,ArrayList<User> list){
        int i = 1;
        for (User user:list){
            if (ID.equals(user.getID())){
                System.out.println("客户ID为”"+ID+"“的客户信息为：");
                System.out.println("客户ID--用户名--用户级别--用户注册时间--客户累计消费总金额--用户手机号--用户邮箱");
                System.out.println(user.getID()+"--"+user.getAccount()+"--"+user.getRank()+"--"+
                        user.getDate()+"--"+user.getConsume()+"--"+user.getPhone()+"--"+user.getMail());
                i++;
            }
        }
        if (i == 1){
            System.out.println("未录入客户ID为”"+ID+"“的客户信息!");
        }
    }

    //根据客户用户名查询客户信息
    public static void  getUserByAccount(String account,ArrayList<User> list){
        int i = 1;
        for (User user:list){
            if (account.equals(user.getAccount())){
                System.out.println("用户名为”"+account+"“的客户信息为：");
                System.out.println("客户ID--用户名--用户级别--用户注册时间--客户累计消费总金额--用户手机号--用户邮箱");
                System.out.println(user.getID()+"--"+user.getAccount()+"--"+user.getRank()+"--"+
                        user.getDate()+"--"+user.getConsume()+"--"+user.getPhone()+"--"+user.getMail());
                i++;
            }
        }
        if (i == 1){
            System.out.println("未录入用户名为”"+account+"“的客户信息!");
        }
    }

    // 注册用户
    public static User addUser(String account){
        User user = new User();
        Scanner scanner = new Scanner(System.in);

        user.setAccount(account);

        System.out.println("请输入密码");
        String password=scanner.nextLine();
        // 正则表达式，匹配密码格式，长度大于8个字符，必须以大小写字母、数字和标点符号的组合
        Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={}\\[\\]\\|;:'\",<.>/?])(?=\\S+$).{9,}$");
        while(!patternPassword.matcher(password).matches()){
            System.out.println("密码需长度大于8个字符，必须是以大小写字母、数字和标点符号的组合，请重新输入：");
            password=scanner.nextLine();
        }
        user.setPassword(password);

        System.out.println("请输入手机号码");
        String phone=scanner.nextLine();
        // 正则表达式，匹配手机号格式
        Pattern patternPhone = Pattern.compile("^1[3-9]\\d{9}$");
        while(!patternPhone.matcher(phone).matches()){
            System.out.println("输入的手机号码格式错误，请重新输入：");
            phone=scanner.nextLine();
        }
        user.setPhone(phone);

        System.out.println("请输入邮箱");
        String mail=scanner.nextLine();
        //正则表达式，匹配邮箱格式
        Pattern patternMail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        while(!patternMail.matcher(mail).matches()){
            System.out.println("输入的邮箱格式错误，请重新输入：");
            mail=scanner.nextLine();
        }
        user.setMail(mail);

        //用户注册时间(年-月-日)
        LocalDate date = LocalDate.now();
        user.setDate(date);

        //用户ID，根据注册的时间生产，例如2023年7月14日10时21分22秒注册的用户，其ID为20230714102122
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = currentDateTime.format(formatter);
        user.setID(formattedDateTime);

        System.out.println("注册成功！");

        return user;
    }

    //根据用户名判断是否有此客户
    public static boolean existAccount(String account, ArrayList<User> list ){
        boolean bool = true;
        for (User user: list){
            if (account.equals(user.getAccount())){
                bool = false;
            }
        }
        return bool;
    }

    //根据客户ID判断是否有此客户
    public static boolean existID(String ID, ArrayList<User> list ){
        boolean bool = true;
        for (User user: list){
            if (ID.equals(user.getID())){
                bool = false;
            }
        }
        return bool;
    }

    //查找某一客户的索引
    public static int index(String account, ArrayList<User> list){
        int index = -1;
        for (User user: list){
            if (user.getAccount().equals(account)){
                index = list.indexOf(user);
            }
        }
        return index;
    }
}
