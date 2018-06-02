package vue.web.mvc.db.user;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/20 18:49
 * Description:
 */
public class UserWithCriticalInfo extends UserInfo{
    private static final long serialVersionUID = -4223042233914791514L;
    String password;
    String salt;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
