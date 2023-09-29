package user;
//管理员类
public class Admin {
    private String account;
    private String password;
    public Admin(String account,String password){
        this.account=account;
        this.password=password;
    }

    public Admin(){
        this.account="admin";
        this.password="ynuadmin";
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
}
