package vue.web.mvc.db.user;

import vue.web.mvc.db.CommonTableItem;

import java.io.Serializable;
import java.util.Map;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/10 9:49
 * Description:
 */
public class UserInfo extends CommonTableItem implements Serializable {
    private static final long serialVersionUID = -6791545046738334178L;
    String userId;
    String userName;
    String avatar;
    String nickName;


    Map<String,Boolean> roles;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }




    public Map<String, Boolean> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, Boolean> roles) {
        this.roles = roles;
    }
}
